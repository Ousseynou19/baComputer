package sn.bacomputer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import sn.bacomputer.DTO.UserDTO;
import sn.bacomputer.entity.Role;
import sn.bacomputer.entity.Users;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    default String mapRolesToRolename(Set<Role> roles) {
        return roles.isEmpty() ? null : roles.iterator().next().getRolename();
    }

    @Mapping(target = "rolename", expression = "java(mapRolesToRolename(user.getRoles()))")

//    @Mapping(source = "employee.id", target = "employeId")

    UserDTO toDto(Users user);
//    @Mapping(source = "employeId", target = "employee.id")
    Users toEntity(UserDTO usersDetailsDto);
}
