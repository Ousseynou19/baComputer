package sn.bacomputer.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import sn.bacomputer.entity.Users;
import sn.bacomputer.service.UserService;


import java.io.IOException;

@Component
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserService userService;
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        Users user = userService.findByUsername(authentication.getName());

        if (user != null && user.getFailedLoginAttempts() > 0) {
            customUserDetailsService.resetFailedAttempts(user);
        }

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
