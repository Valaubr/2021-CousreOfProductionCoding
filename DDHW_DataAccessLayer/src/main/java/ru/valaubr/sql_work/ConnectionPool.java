package ru.valaubr.sql_work;

import org.slf4j.LoggerFactory;
import ru.valaubr.DAO.DocumentDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//TODO бодключить либу или написать по человечески...
public class ConnectionPool {
    private static Connection connection;

    static {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DBConnectionInfo.URL, DBConnectionInfo.DB_USER, DBConnectionInfo.DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            LoggerFactory.getLogger(DocumentDAO.class).error(e.getMessage(), e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
