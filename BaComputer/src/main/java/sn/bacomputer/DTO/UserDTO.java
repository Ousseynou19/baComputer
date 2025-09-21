package sn.bacomputer.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sn.bacomputer.enumerations.AccountStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
    private Long user_id;

    private String username;
    //    private String password;
//    private boolean active;
//    private boolean firstConnect = true; // Par défaut, il est à true
    private String rolename;
    private Long employeId;
    private AccountStatus statutCompte = AccountStatus.ACTIVE;






//    private Set<Role> roles = new HashSet<>();
}
