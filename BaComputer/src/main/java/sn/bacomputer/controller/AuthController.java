package sn.bacomputer.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import sn.bacomputer.DTO.AuthRequest;
import sn.bacomputer.DTO.AuthResponse;
import sn.bacomputer.DTO.RefreshTokenRequest;
//import sn.bacomputer.services.Impl.RefreshTokenService;
import sn.bacomputer.entity.Users;
import sn.bacomputer.service.UserService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
//    private final RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        AuthResponse jwtAuthResponse = userService.login(authRequest);
        return ResponseEntity.ok(jwtAuthResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        AuthResponse response = userService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable Long id) {
        userService.resetPassword(id);
        return ResponseEntity.ok("Mot de passe réinitialisé avec succès. Vérifiez vos emails !");
    }


//    @PostMapping("/logout")
//        public ResponseEntity<String> logout(
//                @RequestHeader(value = "Authorization", required = false) String authHeader) {
//
//            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                return ResponseEntity.badRequest().body("Refresh token manquant ou invalide");
//            }
//
//            // Extraire le token du header
//            String refreshToken = authHeader.substring(7);
//
//            refreshTokenService.revokeToken(refreshToken);
//            return ResponseEntity.ok("Déconnexion réussie ✅ Refresh token révoqué");
//        }


    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        // En stateless, on ne stocke rien → donc on ne peut pas révoquer
        return ResponseEntity.ok("Déconnexion réussie ✅ (supprimez le token côté client)");
    }
}

