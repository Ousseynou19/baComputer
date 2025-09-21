package sn.bacomputer.DTO;

import lombok.*;
import jakarta.validation.constraints.NotBlank;


@Getter @Setter
public class RegisterRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String email;
}
