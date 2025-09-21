package sn.bacomputer.dto;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import sn.bacomputer.dto.OrderDTO;
import jakarta.validation.constraints.*;
import sn.bacomputer.enumerations.Role;
import java.util.Set;

/**
 * A DTO for the {@link sn.bacomputer.entity.Users} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Role role;

    private java.util.Set<OrderDTO> orders;



    // Lombok génère automatiquement les getters, setters, equals, hashCode et toString
}
