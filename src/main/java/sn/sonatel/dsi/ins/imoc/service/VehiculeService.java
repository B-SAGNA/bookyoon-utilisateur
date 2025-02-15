package sn.sonatel.dsi.ins.imoc.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.sonatel.dsi.ins.imoc.domain.Vehicule;
import sn.sonatel.dsi.ins.imoc.repository.VehiculeRepository;
import sn.sonatel.dsi.ins.imoc.service.dto.VehiculeDTO;
import sn.sonatel.dsi.ins.imoc.service.mapper.VehiculeMapper;

/**
 * Service Implementation for managing {@link sn.sonatel.dsi.ins.imoc.service.VehiculeService}.
 */
@Service
@Transactional
public class VehiculeService {

    private final Logger log = LoggerFactory.getLogger(VehiculeService.class);

    private final VehiculeRepository vehiculeRepository;

    private final VehiculeMapper vehiculeMapper;

    public VehiculeService(VehiculeRepository vehiculeRepository, VehiculeMapper vehiculeMapper) {
        this.vehiculeRepository = vehiculeRepository;
        this.vehiculeMapper = vehiculeMapper;
    }

    /**
     * Save a vehicule.
     *
     * @param vehiculeDTO the entity to save.
     * @return the persisted entity.
     */
    public VehiculeDTO save(VehiculeDTO vehiculeDTO) {
        log.debug("Request to save Vehicule : {}", vehiculeDTO);
        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeDTO);
        vehicule = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(vehicule);
    }

    /**
     * Update a vehicule.
     *
     * @param vehiculeDTO the entity to save.
     * @return the persisted entity.
     */
    public VehiculeDTO update(VehiculeDTO vehiculeDTO) {
        log.debug("Request to update Vehicule : {}", vehiculeDTO);
        Vehicule vehicule = vehiculeMapper.toEntity(vehiculeDTO);
        vehicule = vehiculeRepository.save(vehicule);
        return vehiculeMapper.toDto(vehicule);
    }

    /**
     * Partially update a vehicule.
     *
     * @param vehiculeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<VehiculeDTO> partialUpdate(VehiculeDTO vehiculeDTO) {
        log.debug("Request to partially update Vehicule : {}", vehiculeDTO);

        return vehiculeRepository
            .findById(vehiculeDTO.getId())
            .map(existingVehicule -> {
                vehiculeMapper.partialUpdate(existingVehicule, vehiculeDTO);

                return existingVehicule;
            })
            .map(vehiculeRepository::save)
            .map(vehiculeMapper::toDto);
    }

    /**
     *  Get all the vehicules where Utilisateur is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<VehiculeDTO> findAllWhereUtilisateurIsNull() {
        log.debug("Request to get all vehicules where Utilisateur is null");
        return StreamSupport.stream(vehiculeRepository.findAll().spliterator(), false)
            .filter(vehicule -> vehicule.getUser() == null)
            .map(vehiculeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one vehicule by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<VehiculeDTO> findOne(Long id) {
        log.debug("Request to get Vehicule : {}", id);
        return vehiculeRepository.findById(id).map(vehiculeMapper::toDto);
    }

    /**
     * Delete the vehicule by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Vehicule : {}", id);
        vehiculeRepository.deleteById(id);
    }
}
