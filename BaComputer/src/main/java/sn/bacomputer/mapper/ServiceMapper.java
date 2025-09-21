package sn.bacomputer.mapper;


import org.mapstruct.*;
import sn.bacomputer.DTO.ServiceDTO;
import sn.bacomputer.entity.ServiceEntity;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceDTO toDTO(ServiceEntity entity);
    ServiceEntity toEntity(ServiceDTO dto);
}

