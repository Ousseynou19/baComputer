package sn.bacomputer.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import sn.bacomputer.DTO.UserDTO;
import sn.bacomputer.entity.Users;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-17T18:46:02+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.2 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(Users user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setUsername( user.getUsername() );
        userDTO.setStatutCompte( user.getStatutCompte() );

        userDTO.setRolename( mapRolesToRolename(user.getRoles()) );

        return userDTO;
    }

    @Override
    public Users toEntity(UserDTO usersDetailsDto) {
        if ( usersDetailsDto == null ) {
            return null;
        }

        Users users = new Users();

        users.setUsername( usersDetailsDto.getUsername() );
        users.setStatutCompte( usersDetailsDto.getStatutCompte() );

        return users;
    }
}
