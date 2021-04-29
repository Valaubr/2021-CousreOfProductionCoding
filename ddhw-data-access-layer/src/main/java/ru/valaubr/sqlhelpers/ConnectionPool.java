package ru.valaubr.sqlhelpers;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcConnectionPool;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
public class ConnectionPool {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static ConnectionPool instance;
    private final JdbcConnectionPool jdbcConnectionPool;

    public ConnectionPool() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        jdbcConnectionPool = JdbcConnectionPool.create(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }

    @Bean
    public static synchronized ConnectionPool getPool() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            return jdbcConnectionPool.getConnection();
        } catch (SQLException e) {
            log.error("Bad connection", e);
        }
        return null;
    }
}
