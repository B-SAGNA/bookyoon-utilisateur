package sn.sonatel.dsi.ins.imoc.web.rest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sn.sonatel.dsi.ins.imoc.repository.AuthorityRepository;
import sn.sonatel.dsi.ins.imoc.repository.UserRepository;
import sn.sonatel.dsi.ins.imoc.service.UserService;
import sn.sonatel.dsi.ins.imoc.service.dto.UserDTO;
import tech.jhipster.web.util.PaginationUtil;

@RestController
@RequestMapping("/api")
public class PublicUserResource {

    private static final List<String> ALLOWED_ORDERED_PROPERTIES = Collections.unmodifiableList(
        Arrays.asList("id", "login", "firstName", "lastName", "email", "activated", "langKey")
    );

    private final Logger log = LoggerFactory.getLogger(PublicUserResource.class);

    private final UserService userService;

    private final AuthorityRepository authorityRepository;

    private final UserRepository userRepository;

    public PublicUserResource(UserService userService, AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.userService = userService;
        this.authorityRepository = authorityRepository;
        this.userRepository = userRepository;
    }

    /**
     * {@code GET /users} : get all users with only the public informations - calling this are allowed for anyone.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllPublicUsers(Pageable pageable) {
        log.debug("REST request to get all public User names");
        if (!onlyContainsAllowedProperties(pageable)) {
            return ResponseEntity.badRequest().build();
        }

        final Page<UserDTO> page = userService.getAllPublicUsers(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    private boolean onlyContainsAllowedProperties(Pageable pageable) {
        return pageable.getSort().stream().map(Sort.Order::getProperty).allMatch(ALLOWED_ORDERED_PROPERTIES::contains);
    }

    /**
     * Gets a list of all roles.
     * @return a string list of all roles.
     */
    @GetMapping("/authorities")
    public List<String> getAuthorities() {
        return userService.getAuthorities();
    }

    @GetMapping("/admin-emails")
    public ResponseEntity<List<String>> getAdminEmails() {
        var adminAuth = authorityRepository.findByNameIgnoreCase("ROLE_ADMIN").orElseThrow();
        return ResponseEntity.ok(
            userRepository.findAllByAuthoritiesContaining(adminAuth).stream().map(u -> u.getEmail()).collect(Collectors.toList())
        );
    }
}
