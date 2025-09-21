package sn.bacomputer.service;

import sn.bacomputer.DTO.AuthRequest;
import sn.bacomputer.DTO.AuthResponse;
import sn.bacomputer.DTO.RefreshTokenRequest;
import sn.bacomputer.DTO.UserDTO;
import sn.bacomputer.entity.Users;
import sn.bacomputer.enumerations.AccountStatus;

import java.util.List;




    public interface UserService {
        AuthResponse login(AuthRequest authRequest);

//        AuthResponse refreshToken(RefreshTokenRequest request);


        AuthResponse refreshToken(RefreshTokenRequest request);

        Users findByUsername(String username);

        Users getCurrentUser();

        UserDTO createUsers(UserDTO usersDetailsDto);

//    void changePassword(String username, String newPassword);
//
//    void changePassword(Long userId, ChangePasswordRequest request);

        UserDTO getUsersById(Long user_id);

        List<UserDTO> getAllUsers();

        UserDTO updateUser(Long user_id, UserDTO usersDetailsDto);

        void resetPassword(Long userId);

        void disableUser(Long userId);

        void enableUser(Long userId);

        void updateStatus(Long id, AccountStatus newStatus);

        void deleteUsers(Long user_id);
    }


