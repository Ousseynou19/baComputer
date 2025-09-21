package sn.bacomputer.security;//package sn.systalink.security;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//import sn.systalink.entity.Users;
//import sn.systalink.enumeration.AccountStatus;
//import sn.systalink.service.UsersService;
//
//import java.io.IOException;
//
//@Component
//public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
//
//    @Autowired
//    private UsersService userService;
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest request,
//                                        HttpServletResponse response,
//                                        AuthenticationException exception) throws IOException, ServletException {
//        String username = request.getParameter("username");
//        Users user = userService.findByUsername(username);
//
//        if (user != null) {
//           AccountStatus statut =  user.setStatutCompte(AccountStatus.LOCKED)
//            if (!user.getStatutCompte()) {
//                userService.increaseFailedAttempts(user);
//            }
//        }
//
//        super.onAuthenticationFailure(request, response, exception);
//    }
//}
//
