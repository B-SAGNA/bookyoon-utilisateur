package sn.sonatel.dsi.ins.imoc.service.mapper;

import org.mapstruct.Mapper;
import sn.sonatel.dsi.ins.imoc.domain.Vehicule;
import sn.sonatel.dsi.ins.imoc.service.dto.VehiculeDTO;

/**
 * Mapper for the entity {@link Vehicule} and its DTO {@link VehiculeDTO}.
 */
@Mapper(componentModel = "spring")
public interface VehiculeMapper extends EntityMapper<VehiculeDTO, Vehicule> {}
