package ru.valaubr.DAO;

import lombok.extern.slf4j.Slf4j;
import ru.valaubr.DAOinterfaces.DocumentDAOInterface;
import ru.valaubr.enums.Importance;
import ru.valaubr.models.DataStorage;
import ru.valaubr.models.Document;
import ru.valaubr.models.User;
import ru.valaubr.sql_work.ConnectionPool;
import ru.valaubr.sql_work.SQLQueries;

import java.sql.*;
import java.time.LocalDate;

@Slf4j
public class DocumentDAO extends DataStorage implements DocumentDAOInterface {
    @Override
    public boolean createDoc(Long parentID, String name, User author, String linkOnDisk, String description, Importance importance) {
        try {
            Connection connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(SQLQueries.INSERT_DATA_STORAGE);
            if (parentID != null) {
                statement.setLong(1, parentID);
            } else {
                statement.setNull(1, Types.NULL);
            }
            statement.setString(2, name);
            statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.setString(4, linkOnDisk);
            statement.setString(5, author.getEmail());
            statement.setBoolean(6, false);
            statement.execute();
            statement = connection.prepareStatement("select * from data_storage where PARENT_ID is null " +
                    "and name = ? and folder = false or PARENT_ID = ? and name = ? and folder = false order by id ASC");
            if (parentID != null) {
                statement.setLong(2, parentID);
            } else {
                statement.setNull(2, Types.NULL);
            }
            statement.setString(1, name);
            statement.setString(3, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            statement = connection.prepareStatement("insert into document values(?,?,?,?,?)");
            statement.setLong(1, resultSet.getLong(1));
            statement.setString(2, description);
            statement.setString(3, importance.toString());
            statement.setInt(4, 1);
            statement.setNull(5, Types.NULL);
            statement.execute();
            resultSet.close();
            statement.close();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    @Override
    public boolean updateDoc(Long id, String name, String linkOnDisk, String description, Importance importance) {
        try {
            Connection connection = ConnectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM data_storage AS ds LEFT JOIN document AS doc ON ds.id = doc.data_storage where id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            statement = connection.prepareStatement(SQLQueries.INSERT_DATA_STORAGE);
            if (rs.getLong("parent_id") != 0) {
                statement.setLong(1, rs.getLong("parent_id"));
            } else {
                statement.setNull(1, Types.NULL);
            }
            statement.setString(2, name);
            statement.setDate(3, rs.getDate("creation_date"));
            statement.setString(4, linkOnDisk);
            if (rs.getString("author") != null) {
                statement.setString(5, rs.getString("author"));
            } else {
                statement.setNull(5, Types.NULL);
            }
            statement.setBoolean(6, false);

            statement.execute();

            statement = connection.prepareStatement("select * from data_storage where PARENT_ID is null " +
                    "and name = ? and folder = false or PARENT_ID = ? and name = ? and folder = false order by id asc");
            if (rs.getLong("parent_id") != 0) {
                statement.setLong(2, rs.getLong("parent_id"));
            } else {
                statement.setNull(2, Types.NULL);
            }
            statement.setString(1, name);
            statement.setString(3, name);
            ResultSet rs1 = statement.executeQuery();
            rs1.next();
            statement = connection.prepareStatement("insert into document values(?,?,?,?,?)");
            statement.setLong(1, rs1.getLong("id"));
            statement.setString(2, description);
            statement.setString(3, importance.toString());
            statement.setInt(4, rs.getInt("version") + 1);
            statement.setInt(5, rs.getInt("id"));
            statement.execute();
            rs.close();
            rs1.close();
            statement.close();
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    public Document getDoc(Long id) {
        Connection connection = ConnectionPool.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM data_storage AS ds LEFT JOIN document AS doc ON ds.id = doc.data_storage where id = ?");
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            rs.next();
            Document doc = new Document();
            doc.setId(rs.getLong(1));
            doc.setParentId(rs.getLong(2));
            doc.setName(rs.getString("name"));
            doc.setPathOnDisk(rs.getString("link_on_disk"));
            doc.setDateOfCreation(rs.getDate("creation_date"));
            doc.setDescription(rs.getString("description"));
            doc.setImportance(Importance.valueOf(rs.getString("importance")));
            doc.setVersion(rs.getInt("version"));
            rs.close();
            statement.close();
            return doc;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
