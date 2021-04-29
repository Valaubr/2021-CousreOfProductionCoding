package ru.valaubr.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.valaubr.sqlhelpers.ConnectionPool;

import java.sql.Connection;

@Configuration
@ComponentScan(basePackages = "ru.valaubr")
public class AccessLayerSpringConfig {
    @Bean
    public Connection getConntection() {
        return new ConnectionPool().getConnection();
    }
}
