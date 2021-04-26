package ru.valaubr.servicelayer.dao.Impl;

import lombok.extern.slf4j.Slf4j;
import ru.valaubr.servicelayer.dao.CatalogDao;
import ru.valaubr.servicelayer.models.Document;
import ru.valaubr.servicelayer.models.User;
import ru.valaubr.servicelayer.sqlHelpers.ConnectionPool;
import ru.valaubr.servicelayer.sqlHelpers.SQLQueries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CatalogDaoImpl implements CatalogDao {
    private static final String CLOSE_ERROR = "Don`t close connection";
    private final ConnectionPool connectionPool = ConnectionPool.getPool();

    @Override
    public List<Document> getAll(Long parentID) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement;
        ResultSet resultSet;
        ResultSet userSet;
        try {
            if (parentID != null) {
                statement = connection.prepareStatement(SQLQueries.SELECT_FROM_DS_BY_PID);
                statement.setLong(1, parentID);
            } else {
                statement = connection.prepareStatement(SQLQueries.SELECT_FROM_DS_BY_PID_ISNULL);
            }
            resultSet = statement.executeQuery();
            List<Document> arr = new ArrayList<>();
            while (resultSet.next()) {
                statement = connection.prepareStatement(SQLQueries.SELECT_USER);
                statement.setString(1, resultSet.getString("author"));
                userSet = statement.executeQuery();
                userSet.next();
                User user = new User();
                if (userSet.wasNull()) {
                    user.setEmail(userSet.getString("EMAIL"));
                }
                Document document = new Document();
                document.setId(resultSet.getLong(1));
                document.setName(resultSet.getString(3));
                document.setDateOfCreation(resultSet.getDate(4));
                document.setPathOnDisk(resultSet.getString(5));
                document.setAuthor(user);
                arr.add(document);
            }
            return arr;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(CLOSE_ERROR, e);
            }
        }
        return null;
    }

    @Override
    public void createCatalog(Long parentID, String name, User author) {
        Connection connection = connectionPool.getConnection();
        PreparedStatement statement;
        ResultSet rs;
        try {
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(SQLQueries.SELECT_FROM_DS);
            if (parentID != null) {
                statement.setLong(1, parentID);
            } else {
                statement.setNull(1, Types.NULL);
            }
            rs = statement.executeQuery();
            connection.commit();
            rs.next();
            statement = connection.prepareStatement(SQLQueries.INSERT_DATA_STORAGE);
            if (!rs.wasNull()) {
                statement.setNull(1, Types.NULL);
                statement.setString(4, "/");
            } else {
                statement.setLong(1, parentID);
                statement.setString(4, rs.getString("LINK_ON_DISK") + "/");
            }
            statement.setString(2, name);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setString(5, author.getEmail());
            statement.setBoolean(6, true);
            statement.execute();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(CLOSE_ERROR, e);
            }
        }
    }

    @Override
    public void updateCatalog(Long id, String name, String linkOnDisk) {
        Connection connection = connectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQLQueries.UPDATE_DS);
            statement.setString(1, name);
            statement.setString(2, linkOnDisk);
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(CLOSE_ERROR, e);
            }
        }
    }
}