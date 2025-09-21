package sn.bacomputer.service.Impl;



import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sn.bacomputer.DTO.AuthRequest;
import sn.bacomputer.DTO.AuthResponse;
import sn.bacomputer.DTO.RefreshTokenRequest;
import sn.bacomputer.DTO.UserDTO;
import sn.bacomputer.entity.Role;
import sn.bacomputer.entity.Users;
import sn.bacomputer.enumerations.AccountStatus;
import sn.bacomputer.mapper.UserMapper;
import sn.bacomputer.repository.RoleRepository;
import sn.bacomputer.repository.UserRepository;
import sn.bacomputer.security.CustomUserDetailsService;
import sn.bacomputer.security.JwtTokenProvider;
import sn.bacomputer.security.PasswordGenerator;
import sn.bacomputer.security.UserDetailsImpl;
import sn.bacomputer.service.UserService;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Transactional
@Service
@RequiredArgsConstructor
public class  UsersServiceImpl implements UserService {

    private final UserRepository usersRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
//    private final JavaMailSender mailSender;
//    private final EmployeRepository employeRepository;
    private final CustomUserDetailsService userDetailsService;

    private final AuthenticationManager authenticationManager;
    private  final JwtTokenProvider jwtTokenProvider;

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Générer les tokens
        String token = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        // Charger l’utilisateur depuis la BDD
        Users user = usersRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        // Récupérer le rôle
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        // Retourner la réponse complète
        return new AuthResponse(
                token,
                refreshToken,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                role
        );
    }


    @Override
    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.getRefreshToken();

        if (jwtTokenProvider.validateToken(refreshToken)) {
            String username = jwtTokenProvider.getUsername(refreshToken);

            // ⚡ On recharge l'utilisateur depuis la base
            UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );

            String newAccessToken = jwtTokenProvider.generateAccessToken(authentication);

            AuthResponse response = new AuthResponse();
            response.setAccessToken(newAccessToken);
            response.setRefreshToken(refreshToken); // on renvoie le même refresh token
            response.setRole(userDetails.getAuthorities().iterator().next().getAuthority()); // récupérer un rôle

            return response;
        } else {
            throw new RuntimeException("Refresh token invalide ou expiré");
        }
    }



    @Override
    public Users findByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found with username " + username));
    }

    @Override
    public Users getCurrentUser() {
        // Utiliser Spring Security pour obtenir l'email de l'utilisateur connecté
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Employe not found with email " + username));
    }






    @Override
    public UserDTO createUsers(UserDTO usersDetailsDto) {
        Users user = userMapper.toEntity(usersDetailsDto);
//    System.out.println("Rolename reçu: " + usersDetailsDto.getRolename()); // Debug

        Role role = roleRepository.findByRolename(usersDetailsDto.getRolename())
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
//        Employe employee = employeRepository.findById(usersDetailsDto.getEmployeId())
//                .orElseThrow(() -> new RuntimeException("Employe non trouvé"));
//        user.setEmployee(employee);


//    usersDetailsDto.setPassword(passwordEncoder.encode(usersDetailsDto.getPassword()));
//        Users user = userMapper.toEntity(usersDetailsDto);


//     Génération d'un mot de passe aléatoire
        String randomPassword = PasswordGenerator.generatePassword();
//    System.out.println("Mot de passe généré : " + randomPassword);

//     Encodage du mot de passe
        String encodedPassword = passwordEncoder.encode(randomPassword);
        user.setPassword(encodedPassword); // On stocke l'encodé

        // Ajouter le rôle à l'utilisateur
        user.getRoles().add(role);
//    // Associer le rôle à l'utilisateur
//    user.setRoles(Collections.singleton(role));
//    user.setFirstConnect(true); // Ajout du flag première connexion

        Users savedUsers = usersRepository.save(user);
        // Envoyer le mot de passe par email (optionnel)
//        sendTemporaryPassword(usersDetailsDto.getUsername(), randomPassword);

        return userMapper.toDto(savedUsers);
    }





//    private void sendTemporaryPassword(String email, String tempPassword) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject("Votre mot de passe ");
//        message.setText("Votre mot de passe  est : " + tempPassword );
//
//        mailSender.send(message);
//    }
//    @Override
//    public void changePassword(String username, String newPassword) {
//        Users user = usersRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
//
//        user.setPassword(passwordEncoder.encode(newPassword));
//        usersRepository.save(user);
//    }
//    @Override
//    public void changePassword(Long userId, ChangePasswordRequest request) {
//        Users user = usersRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
//
//        // Vérification ancien mot de passe
//        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
//            throw new RuntimeException("Ancien mot de passe incorrect");
//        }
//
//        // Vérification confirmation
//        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
//            throw new RuntimeException("Les mots de passe ne correspondent pas");
//        }
//
//        // Mise à jour avec encodage
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        usersRepository.save(user);
//    }

    @Override
    public UserDTO getUsersById(Long user_id) {
        Users user = usersRepository.findById(user_id).orElseThrow(() -> new RuntimeException("L'id de cet user n'existe pas :"+user_id));
        return userMapper.toDto(user);

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return usersRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }




    @Override
    public UserDTO updateUser(Long user_id, UserDTO usersDetailsDto) {
        Users existingUser = usersRepository.findById(user_id)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + user_id));

        // Mise à jour des champs simples
        if (usersDetailsDto.getUsername() != null) {
            existingUser.setUsername(usersDetailsDto.getUsername());
        }
//        existingUser.setActive(usersDetailsDto.isActive());

        // Mise à jour du rôle si présent
        if (usersDetailsDto.getRolename() != null && !usersDetailsDto.getRolename().isEmpty()) {
            Role role = roleRepository.findByRolename(usersDetailsDto.getRolename())
                    .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));
            existingUser.setRoles(Collections.singleton(role));
        }

//        // Mise à jour de l’employé si présent
//        if (usersDetailsDto.getEmployeId() != null) {
//            Employe employee = employeRepository.findById(usersDetailsDto.getEmployeId())
//                    .orElseThrow(() -> new RuntimeException("Employe non trouvé"));
//            existingUser.setEmployee(employee);
//        }

        // ⚠️ On ne touche pas au password ici
        Users savedUser = usersRepository.save(existingUser);
        return userMapper.toDto(savedUser);
    }

//    // Vérifie si l'utilisateur connecté est bien le propriétaire de l'ID
//    public boolean isOwner(Long id, String username) {
//        return usersRepository.findById(id)
//                .map(user -> user.getUsername().equals(username))
//                .orElse(false);
//    }
//
//    // Vérifie si l'utilisateur est admin
//    public boolean isAdmin(String username) {
//        return usersRepository.findByUsername(username)
//                .map(user -> user.getRoles().getRolename().equals("ROLE_ADMIN")) // adapte selon ton modèle
//                .orElse(false);
//    }

    @Override
    public void resetPassword(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Générer un mot de passe aléatoire
        String randomPassword = PasswordGenerator.generatePassword();

        // Encoder le mot de passe
        String encodedPassword = passwordEncoder.encode(randomPassword);
        user.setPassword(encodedPassword);

        // Forcer l’utilisateur à changer de mot de passe à sa prochaine connexion
//        user.setFirstConnect(true);

        usersRepository.save(user);

//        // Optionnel : envoyer le mot de passe par email
//        sendTemporaryPassword(user.getUsername(), randomPassword);
    }

    @Override
    public void disableUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        user.setStatutCompte(AccountStatus.DISABLED);
        usersRepository.save(user);
    }

    @Override
    public void enableUser(Long userId) {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
        user.setStatutCompte(AccountStatus.ACTIVE);
        usersRepository.save(user);
    }



    @Override
    public void updateStatus(Long id, AccountStatus newStatus) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        user.setStatutCompte(newStatus);
        usersRepository.save(user);
    }








    @Override
    public void deleteUsers(Long user_id) {
        Users user = usersRepository.findById(user_id).orElseThrow(() -> new RuntimeException("L'id de cet user n'existe pas :"+user_id));

        usersRepository.deleteById(user_id);
    }
}


