package br.com.alura.med.config.security;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import br.com.alura.med.infra.security.SecurityFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {"/swagger-ui/**", "/swagger-resources/**",
            "/v3/api-docs/**", "/webjars/**"};
    private final SecurityFilter securityFilter;

    /* Desabilita a seguranca do Spring */
    @Bean
    public SecurityFilterChain securityConfiguration(final HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and().authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers(AUTH_WHITELIST).permitAll()
                        .anyRequest().authenticated().and()
                        .addFilterBefore(securityFilter,
                                UsernamePasswordAuthenticationFilter.class))
                .build();
    }

    /* Bean configurado para conseguir fazer injecao de dependencia de
    AuthenticationManager */
    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /* Conseguir fazer o decrypt da senha */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
