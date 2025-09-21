package sn.bacomputer.security;//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//import sn.systalink.entity.Users;
//import sn.systalink.enumeration.AccountStatus;
//import sn.systalink.security.LoginAttemptService;
//
//@Component
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private UserDetails userDetailsService;
//
//    @Autowired
//    private LoginAttemptService loginAttemptService;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        Users user = userDetailsService.loadUserByUsername(email);
//
//        if (user.get() == AccountStatus.LOCKED) {
//            if (!loginAttemptService.unlockIfTimeExpired(user)) {
//                throw new LockedException("Votre compte est verrouillé. Réessayez plus tard.");
//            }
//        }
//
//        if (new BCryptPasswordEncoder().matches(password, user.getPassword())) {
//            loginAttemptService.resetFailedAttempts(user);
//            return new UsernamePasswordAuthenticationToken(user, password, user.getRoles());
//        } else {
//            loginAttemptService.increaseFailedAttempts(user);
//            throw new BadCredentialsException("Identifiants incorrects.");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
