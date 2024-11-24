package pe.cibertec.barber_shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configuración específica para "/login"
                registry.addMapping("/login")
                        .allowedOrigins("http://localhost:4200/")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                // Configuración específica para "/api/**"
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:4200/")
                        .allowedMethods("*");

                // Configuración global para todos los endpoints
                registry.addMapping("/**")
                        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
            }
        };
    }
}