package ru.valaubr.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.valaubr.dto.CatalogDto;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.services.security.MyUserDetailsService;

@Configuration
@ComponentScan(basePackages = "ru.valaubr")
@EnableWebMvc
public class BusinessLogicSpringConfig {
    @Bean
    public UserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }
}
