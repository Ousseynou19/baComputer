package sn.bacomputer.mapper;

import sn.bacomputer.entity.Users;
import sn.bacomputer.dto.UsersDTO;
import org.mapstruct.*;
import sn.bacomputer.dto.OrderDTO;
import java.util.List;
import java.util.Set;

/**
 * Mapper for the entity {@link Users} and its DTO {@link UsersDTO}.
 */
@Mapper(componentModel = "spring", uses = {OrderMapper.class})
public interface UsersMapper {

    @Mapping(target = "orders", source = "orders")
    UsersDTO toDto(Users entity);

    List<UsersDTO> toDto(List<Users> entityList);

    Set<UsersDTO> toDto(Set<Users> entityList);

    @Mapping(target = "orders", source = "orders")
    Users toEntity(UsersDTO entityDTO);

    List<Users> toEntity(List<UsersDTO> dtoList);

    Set<Users> toEntity(Set<UsersDTO> dtoList);

    /**
     * Partial update
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget Users entity, UsersDTO dto);
}
