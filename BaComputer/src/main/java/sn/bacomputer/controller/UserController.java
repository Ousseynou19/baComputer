package sn.bacomputer.controller;



import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sn.bacomputer.DTO.UpdateUserStatusRequest;
import sn.bacomputer.DTO.UserDTO;
import sn.bacomputer.repository.UserRepository;
import sn.bacomputer.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
//        AuthResponse jwtAuthResponse = userService.login(authRequest);
//        return ResponseEntity.ok(jwtAuthResponse);
//    }
//    @PostMapping("/refresh")
//    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
//        AuthResponse response = userService.refreshToken(request);
//        return ResponseEntity.ok(response);
//    }


    @PostMapping()
    public ResponseEntity<UserDTO> createUsers(@RequestBody UserDTO usersDetailsDto) {
        return ResponseEntity.ok(userService.createUsers(usersDetailsDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUsersById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO usersDetailsDto) {
        return ResponseEntity.ok(userService.updateUser(id, usersDetailsDto));
    }


//    @PostMapping("/change-password")
//    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
//        Users user = usersRepository.findByUsername(request.getUsername())
//                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
//
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//    //        user.setFirstConnect(false); // L'utilisateur a changé son mot de passe
//        usersRepository.save(user);
//
//        return ResponseEntity.ok("Mot de passe mis à jour avec succès !");
//    }

//    @PutMapping("/{id}/change-password")
//    public ResponseEntity<String> changePassword(
//            @PathVariable Long id,
//            @RequestBody ChangePasswordRequest request,
//            Authentication authentication) {
//
//        // Vérifier que l’utilisateur connecté change son propre mot de passe
//        String currentUsername = authentication.getName();
//        boolean isAdmin = auth.getAuthorities().stream()
//                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
//
//        if (!userService.isOwner(id, currentUsername) && !userService.isAdmin(currentUsername)) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                    .body("Vous n'êtes pas autorisé à modifier ce mot de passe");
//        }
//
//        userService.changePassword(id, request);
//        return ResponseEntity.ok("Mot de passe changé avec succès");
//    }


//    @PostMapping("/change-password")
//    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {
//        userService.changePassword(request.getEmail(), request.getNewPassword());
//        return ResponseEntity.ok("Mot de passe mis à jour avec succès !");
//    }

//    @Override
//    public void resetPassword(Long userId) {
//        Users user = usersRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
//
//        // Générer un mot de passe aléatoire
//        String randomPassword = PasswordGenerator.generatePassword();
//
//        // Encoder le mot de passe
//        String encodedPassword = passwordEncoder.encode(randomPassword);
//        user.setPassword(encodedPassword);
//
//        // Forcer l’utilisateur à changer de mot de passe à sa prochaine connexion
//        user.setFirstConnect(true);
//
//        usersRepository.save(user);
//
//        // Optionnel : envoyer le mot de passe par email
//        sendTemporaryPassword(user.getUsername(), randomPassword);
//    }

    //    @PutMapping("/{id}/reset-password")
//    public ResponseEntity<String> resetPassword(@PathVariable Long id) {
//        userService.resetPassword(id);
//        return ResponseEntity.ok("Mot de passe réinitialisé avec succès. Vérifiez vos emails !");
//    }
//
//
//    // ✅ Désactiver un compte
//    @PutMapping("/{id}/disable")
//    public ResponseEntity<String> disableUser(@PathVariable Long id) {
//        userService.disableUser(id);
//        return ResponseEntity.ok("Utilisateur désactivé avec succès ✅");
//    }
//
//    // ✅ Réactiver un compte
//    @PutMapping("/{id}/enable")
//    public ResponseEntity<String> enableUser(@PathVariable Long id) {
//        userService.enableUser(id);
//        return ResponseEntity.ok("Utilisateur réactivé avec succès ✅");
//    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateUserStatus(
            @PathVariable Long id,
            @RequestBody UpdateUserStatusRequest request) {

        userService.updateStatus(id, request.getStatutCompte());
        return ResponseEntity.ok("Statut de l'utilisateur mis à jour : " + request.getStatutCompte());
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        userService.deleteUsers(id);
        return ResponseEntity.noContent().build();
    }
}


