package sn.bacomputer.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sn.bacomputer.DTO.ServiceDTO;
import sn.bacomputer.entity.ServiceEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T18:46:02+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public ServiceDTO toDTO(ServiceEntity entity) {
        if ( entity == null ) {
            return null;
        }

        ServiceDTO serviceDTO = new ServiceDTO();

        serviceDTO.setId( entity.getId() );
        serviceDTO.setName( entity.getName() );
        serviceDTO.setDescription( entity.getDescription() );
        serviceDTO.setPrice( entity.getPrice() );

        return serviceDTO;
    }

    @Override
    public ServiceEntity toEntity(ServiceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ServiceEntity serviceEntity = new ServiceEntity();

        serviceEntity.setId( dto.getId() );
        serviceEntity.setName( dto.getName() );
        serviceEntity.setDescription( dto.getDescription() );
        serviceEntity.setPrice( dto.getPrice() );

        return serviceEntity;
    }
}
