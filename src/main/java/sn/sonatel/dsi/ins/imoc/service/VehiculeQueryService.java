package sn.sonatel.dsi.ins.imoc.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.sonatel.dsi.ins.imoc.domain.Vehicule;
import sn.sonatel.dsi.ins.imoc.repository.VehiculeRepository;
import sn.sonatel.dsi.ins.imoc.service.criteria.VehiculeCriteria;
import sn.sonatel.dsi.ins.imoc.service.dto.VehiculeDTO;
import sn.sonatel.dsi.ins.imoc.service.mapper.VehiculeMapper;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Vehicule} entities in the database.
 * The main input is a {@link VehiculeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link Page} of {@link VehiculeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class VehiculeQueryService extends QueryService<Vehicule> {

    private final Logger log = LoggerFactory.getLogger(VehiculeQueryService.class);

    private final VehiculeRepository vehiculeRepository;

    private final VehiculeMapper vehiculeMapper;

    public VehiculeQueryService(VehiculeRepository vehiculeRepository, VehiculeMapper vehiculeMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    /**
     * Return a {@link Page} of {@link VehiculeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<VehiculeDTO> findByCriteria(VehiculeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Vehicule> specification = createSpecification(criteria);
        return vehiculeRepository.findAll(specification, page).map(vehiculeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(VehiculeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Vehicule> specification = createSpecification(criteria);
        return vehiculeRepository.count(specification);
    }

    /**
     * Function to convert {@link VehiculeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Vehicule> createSpecification(VehiculeCriteria criteria) {
        Specification<Vehicule> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            //            if (criteria.getId() != null) {
            //                specification = specification.and(buildRangeSpecification(criteria.getId(), Vehicule_.id));
            //            }
            //            if (criteria.getMarque() != null) {
            //                specification = specification.and(buildStringSpecification(criteria.getMarque(), Vehicule_.marque));
            //            }
            //            if (criteria.getModele() != null) {
            //                specification = specification.and(buildStringSpecification(criteria.getModele(), Vehicule_.modele));
            //            }
            //            if (criteria.getCouleur() != null) {
            //                specification = specification.and(buildStringSpecification(criteria.getCouleur(), Vehicule_.couleur));
            //            }
            //            if (criteria.getMatricule() != null) {
            //                specification = specification.and(buildStringSpecification(criteria.getMatricule(), Vehicule_.matricule));
            //            }
            //            if (criteria.getPlace() != null) {
            //                specification = specification.and(buildRangeSpecification(criteria.getPlace(), Vehicule_.place));
            //            }
            //            if (criteria.getUtilisateurId() != null) {
            //                specification = specification.and(
            //                    buildSpecification(
            //                        criteria.getUtilisateurId(),
            //                        root -> root.join(Vehicule_.utilisateur, JoinType.LEFT).get(Utilisateur_.id)
            //                    )
            //                );
            //            }
        }
        return specification;
    }
}
