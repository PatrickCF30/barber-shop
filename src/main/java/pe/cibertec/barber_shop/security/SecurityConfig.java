package pe.cibertec.barber_shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {

        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/login");

        return http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/usuario/register").permitAll() // Permite el acceso público al registro
                        .requestMatchers(HttpMethod.POST, "/api/password/**").permitAll() // Permite el acceso público a restablecer contraseña
                        .requestMatchers(HttpMethod.GET, "/api/servicios").permitAll() // Permite el acceso público a lista de servicios
                        .requestMatchers(HttpMethod.GET, "/api/employees/enable").permitAll() // Permite el acceso público a lista de empleados activos
                        .requestMatchers(HttpMethod.GET, "/admin/**").hasAuthority("Admin") // Solo accesible para admin
                        .requestMatchers(HttpMethod.GET, "/customer/**").hasAuthority("Customer") // Solo accesible para clientes
                        .requestMatchers(HttpMethod.GET, "/api/employees/{employeeId}/services").hasAuthority("Customer") // Solo accesible para Customer
                        .anyRequest().authenticated() // Requiere autenticación para otras rutas
                )
                .httpBasic()
                .and()
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
