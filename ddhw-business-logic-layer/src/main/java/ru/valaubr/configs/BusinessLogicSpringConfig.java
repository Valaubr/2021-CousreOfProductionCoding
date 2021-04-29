package ru.valaubr.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.valaubr.dao.CatalogDao;
import ru.valaubr.dao.DocumentDao;
import ru.valaubr.dao.impl.CatalogDaoImpl;
import ru.valaubr.dao.impl.DocumentDaoImpl;

@Configuration
@ComponentScan(basePackages = "ru.valaubr")
public class BusinessLogicSpringConfig {
    @Bean
    public CatalogDao catalogDao() {
        return new CatalogDaoImpl();
    }

    @Bean
    public DocumentDao documentDao() {
        return new DocumentDaoImpl();
    }
}
