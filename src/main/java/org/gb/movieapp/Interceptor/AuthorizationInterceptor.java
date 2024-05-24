package org.gb.movieapp.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.gb.movieapp.Model.Enum.UserRole;
import org.gb.movieapp.entites.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("AuthenticationInterceptor");
        // Check if the user is logged in
        User user = (User) request.getSession().getAttribute("currentUser");
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }
        // Check if the user is an admin
        if (!user.getRole().equals(UserRole.ADMIN)) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }

    return true;
    }
}
