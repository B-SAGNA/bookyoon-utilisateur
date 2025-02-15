package sn.sonatel.dsi.ins.imoc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import sn.sonatel.dsi.ins.imoc.domain.Vehicule;

/**
 * Spring Data JPA repository for the Vehicule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long>, JpaSpecificationExecutor<Vehicule> {
    Optional<Vehicule> findByMatricule(String matricule);
}
