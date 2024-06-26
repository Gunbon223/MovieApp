package org.gb.movieapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
     securedEnabled = true
    , jsr250Enabled = true
)
public class SecurityConfig {
    @Autowired
    CustomUserDetailService customUserDetailService;
    @Autowired
    PasswordEncoder passwordEncoder2;
    @Autowired
    CustomFilter customFilter;


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder2);
        provider.setUserDetailsService(customUserDetailService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        String[] paths = {"/phim-le", "/phim-bo", "/phim-chieu-rap", "/tin-tuc/**"};

        http.csrf(AbstractHttpConfigurer::disable); // Tắt chức năng bảo vệ CSRF


        // Cấu hình path phan quyền tap trung
        http.authorizeHttpRequests(auth -> {

            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/user-info","/favourite").hasAnyRole("USER", "ADMIN");

            auth.requestMatchers("/admin/**","/api/admin/**").hasRole("ADMIN");
            auth.requestMatchers("/api/user/**","/api/reviews","/api/favourites/**","/api/auth/**","api/register","api/reviews/**").permitAll();
            auth.requestMatchers("/css/**", "/js/**", "/image/**").permitAll();

            auth.anyRequest().permitAll();
        });

        // Cấu hình logout
        http.logout(logout -> {
            logout.logoutSuccessUrl("/"); // Nếu logout thành công thì chuyển hướng về trang chủ
            logout.deleteCookies("JSESSIONID"); // Xóa cookie
            logout.invalidateHttpSession(true); // Hủy session
            logout.clearAuthentication(true); // Xóa thông tin xác thực
            logout.permitAll(); // Tất cả đều được truy cập vào trang logout
        });

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
