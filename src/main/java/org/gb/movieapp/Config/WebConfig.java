package org.gb.movieapp.Config;

import lombok.RequiredArgsConstructor;
import org.gb.movieapp.Interceptor.AuthenticationInterceptor;
import org.gb.movieapp.Interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AuthenticationInterceptor authenticationInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/reviews/**");
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/favourites/**","/favourite/**");
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/api/user/**","/user-info/**");
        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns("/admin/**", "/api/admin/**");
    }


}


