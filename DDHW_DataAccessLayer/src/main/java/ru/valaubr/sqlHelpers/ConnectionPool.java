package ru.valaubr.services.sqlHelpers;

import lombok.extern.slf4j.Slf4j;
import org.h2.jdbcx.JdbcConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class ConnectionPool {
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static ConnectionPool instance;
    private final JdbcConnectionPool jdbcConnectionPool;

    private ConnectionPool() {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        jdbcConnectionPool = JdbcConnectionPool.create(DB_CONNECTION, DB_USER, DB_PASSWORD);
    }

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
