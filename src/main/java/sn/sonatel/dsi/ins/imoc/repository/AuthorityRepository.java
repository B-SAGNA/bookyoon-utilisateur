package sn.sonatel.dsi.ins.imoc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import sn.sonatel.dsi.ins.imoc.domain.Authority;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
    Optional<Authority> findByNameIgnoreCase(String name);
}
