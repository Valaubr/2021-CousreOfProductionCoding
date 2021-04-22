package ru.valaubr.sql_work;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.sql.ConnectionPoolDataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class JDBCWork {
    public Statement connect() {
        Connection connection = null;
        final String URL = "jdbc:h2:~/test";
        final String DB_USER = "sa";
        final String DB_PASSWORD = "";
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            Statement statement = connection.createStatement();
            return statement;
        } catch (ClassNotFoundException e) {
            log.error("Don`t find h2 driver ", e);
        } catch (SQLException e) {
            log.error("Don`t set connection", e);
        }
        return null;
    }
}
