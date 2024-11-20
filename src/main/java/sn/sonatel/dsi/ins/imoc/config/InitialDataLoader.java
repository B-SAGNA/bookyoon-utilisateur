package sn.sonatel.dsi.ins.imoc.config;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sn.sonatel.dsi.ins.imoc.domain.Authority;
import sn.sonatel.dsi.ins.imoc.domain.User;
import sn.sonatel.dsi.ins.imoc.repository.AuthorityRepository;
import sn.sonatel.dsi.ins.imoc.repository.UserRepository;
import sn.sonatel.dsi.ins.imoc.security.AuthoritiesConstants;
import sn.sonatel.dsi.ins.imoc.service.MailService;
import tech.jhipster.security.RandomUtil;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(InitialDataLoader.class);

    private final ApplicationProperties applicationProperties;

    private final UserRepository userRepository;

    private final MailService emailService;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    private static final List<String> ALL_AUTHORITIES = Arrays.asList(
        AuthoritiesConstants.ADMIN,
        AuthoritiesConstants.CONDUCTEUR,
        AuthoritiesConstants.PASSAGER
    );

    public InitialDataLoader(
        ApplicationProperties applicationProperties,
        UserRepository userRepository,
        MailService emailService,
        AuthorityRepository authorityRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.applicationProperties = applicationProperties;
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("Initialisation des données");

        var authorities = authorityRepository.findAll();

        for (String aut : ALL_AUTHORITIES) {
            if (authorities.stream().noneMatch(a -> a.getName().equals(aut))) {
                var authority = authorityRepository.save(new Authority(aut));
                authorities.add(authority);
            }
        }

        applicationProperties
            .getAdmins()
            .forEach(u -> {
                if (userRepository.findOneByLoginIgnoreCase(u.getUsername()).isEmpty()) {
                    User user = new User();
                    user.setLogin(u.getUsername().toLowerCase());
                    user.setFirstName(u.getFirstName());
                    user.setLastName(u.getLastName());
                    if (u.getEmail() != null) {
                        user.setEmail(u.getEmail().toLowerCase());
                    }
                    user.setLangKey(Constants.DEFAULT_LANGUAGE);

                    String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
                    user.setPassword(encryptedPassword);
                    user.setResetKey(RandomUtil.generateResetKey());
                    user.setResetDate(Instant.now());
                    user.setActivated(true);
                    user.setCreatedBy(Constants.SYSTEM);
                    Set<Authority> auths = new HashSet<>();
                    authorityRepository.findById(AuthoritiesConstants.ADMIN).ifPresent(auths::add);
                    user.setAuthorities(auths);
                    userRepository.save(user);
                    emailService.sendCreationEmail(user);
                }
            });
    }
}
