package sn.bacomputer.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.bacomputer.enumerations.AccountStatus;

@AllArgsConstructor @NoArgsConstructor
@Data
public class UpdateUserStatusRequest {
    private AccountStatus statutCompte; // ex: "ACTIVE", "DISABLED", "LOCKED"
    private String reason; // optionnel, utile si on veut savoir pourquoi on a changé le statut
}

