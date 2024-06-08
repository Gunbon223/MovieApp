package org.gb.movieapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomFilter extends OncePerRequestFilter {
    private final CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Lấy ra email trong session
        log.info("url = {}", request.getRequestURI());

        String email = (String) request.getSession().getAttribute("currentUser");
        log.info("email = {}", email);

        // Kiểm tra email và chưa có thông tin xác thực
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Lấy ra thông tin của user
            UserDetails userDetails = customUserDetailService.loadUserByUsername(email);

            // Tạo đối tượng phân quyền
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            // Lưu thông tin request (IP, session, ...) vào trong đối tượng phân quyền
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // Lưu thông tin đối tượng vào trong SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // Chuyển request và response cho filter tiếp theo
        filterChain.doFilter(request, response);

    }
}
