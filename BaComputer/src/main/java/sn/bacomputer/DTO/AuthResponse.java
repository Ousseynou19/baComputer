package sn.bacomputer.DTO;



import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private Long id;
    private String username;
    private String email;
    private String role;
}

