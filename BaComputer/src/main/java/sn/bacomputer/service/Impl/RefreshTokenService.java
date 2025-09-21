//package sn.bacomputer.services.Impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import sn.bacomputer.repositories.RefreshTokenRepository;
//
//
//@Service
//@RequiredArgsConstructor
//public class RefreshTokenService {
//
//    private final RefreshTokenRepository refreshTokenRepository;
//
//    public void revokeToken(String token) {
//        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
//                .orElseThrow(() -> new RuntimeException("Token introuvable"));
//        refreshToken.setRevoked(true);
//        refreshTokenRepository.save(refreshToken);
//    }
//}

