package domain.company.project.module.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    // ROLE_USER
    // ROLE_ADMIN

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/**")
                .permitAll()
            //.anyRequest()
            //.authenticated()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .cors(
                cors -> cors.configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                    corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
                    corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
                    return corsConfiguration;
                })
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter())));

        return http.build();
    }

    private JWTConverter jwtConverter() {
        return new JWTConverter();
    }
}
