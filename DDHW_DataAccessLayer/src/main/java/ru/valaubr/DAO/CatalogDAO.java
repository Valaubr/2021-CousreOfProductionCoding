package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.sql_work.ConnectionPool;
import ru.valaubr.sql_work.DBConnectionInfo;
import ru.valaubr.sql_work.SQLQueries;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Slf4j
public class Catalog extends DataStorage implements CatalogDAO {
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            LoggerFactory.getLogger(Catalog.class).error(e.getMessage(), e);
        }
    }

    @Override
    public List<Document> getAll(Long parentID) {
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement;
            if (parentID != null) {
                statement = connection.prepareStatement("select * from data_storage where parent_id = ? order by creation_date");
                statement.setLong(1, parentID);
            } else {
                statement = connection.prepareStatement("select * from data_storage where parent_id = null order by creation_date");
            }
            ResultSet resultSet = statement.executeQuery();
            ResultSet userSet;
            List<Document> arr = new ArrayList<>();
            while (resultSet.next()) {
//                statement = connection.prepareStatement("select * from user where user = ?");
//                statement.setString(1, resultSet.getString(3));
//                userSet = statement.executeQuery();
                User user = new User();
                //Todo write user

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
        }
        return null;
    }

    @Override
    public void createCatalog(Long parentID, String name, User author) {
        try {
            Connection connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement("select * from data_storage where id = ?");
            if (parentID != null) {
                statement.setLong(1, parentID);
            } else {
                statement.setNull(1, Types.NULL);
            }
            ResultSet rs = statement.executeQuery();
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
        }
    }

    @Override
    public void updateCatalog(Long id, String name, String linkOnDisk) {
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE data_storage SET NAME = ?, link_on_disk = ? where id = ? ");
            statement.setString(1, name);
            statement.setString(2, linkOnDisk);
            statement.setLong(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }
}