package sn.bacomputer.mapper;

import sn.bacomputer.entity.Service;
import sn.bacomputer.dto.ServiceDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

/**
 * Mapper for the entity {@link Service} and its DTO {@link ServiceDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServiceMapper {

    ServiceDTO toDto(Service entity);

    List<ServiceDTO> toDto(List<Service> entityList);

    Set<ServiceDTO> toDto(Set<Service> entityList);

    Service toEntity(ServiceDTO entityDTO);

    List<Service> toEntity(List<ServiceDTO> dtoList);

    Set<Service> toEntity(Set<ServiceDTO> dtoList);

    /**
     * Partial update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Service entity, ServiceDTO dto);
}
