package org.wdpt6.ticket_platform.ticket_platform.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    
    private SimpleUrlAuthenticationSuccessHandler adminSuccessHandler =
            new SimpleUrlAuthenticationSuccessHandler("/tickets/index");

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
                                            
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("OPERATOR"))) {
            
            Object principal = authentication.getPrincipal();

            Integer operatorId = null;

            if (principal instanceof UserDetails) {                
                
                
                if (principal instanceof DatabaseUserDetails) {
                    operatorId = ((DatabaseUserDetails) principal).getId();
                }
            }

            if (operatorId != null) {
                
                String targetUrl = "/operators/" + operatorId;
                response.sendRedirect(targetUrl); 
            } 

        }
        
        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
        }
        
        
    }
}