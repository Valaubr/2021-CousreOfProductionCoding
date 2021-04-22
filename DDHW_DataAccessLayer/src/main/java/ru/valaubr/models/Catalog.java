package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.FileSystemPatchesConstant;
import ru.valaubr.sql_work.DBConnectionInfo;

import java.io.File;
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
        try (Connection connection = DriverManager.getConnection(DBConnectionInfo.URL, DBConnectionInfo.DB_USER, DBConnectionInfo.DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("select * from data_storage where parent_id = ? order by creation_date");
            statement.setLong(1, parentID);
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
    public void createCatalog(Long parentID, String name, User author, String linkOnDisk) {
        File file = null;
        try (Connection connection = DriverManager.getConnection(DBConnectionInfo.URL, DBConnectionInfo.DB_USER, DBConnectionInfo.DB_PASSWORD)) {
            file = makeDir(parentID, file, connection, name);
            PreparedStatement statement = connection.prepareStatement("insert into data_storage values(?,?,?,?,?,?)");
            statement.setLong(1, 8);
            statement.setLong(2, parentID);
            statement.setString(3, name);
            statement.setDate(4, Date.valueOf(LocalDate.now()));
            statement.setString(5, file.getPath());
            statement.setString(6, author.getEmail());
            statement.execute();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            rmDir(file);
        }
    }

    private File makeDir(Long parentID, File file, Connection connection, String name) throws SQLException {
        if (parentID == 0L) {
            file = new File(FileSystemPatchesConstant.ROOT_DIR + "/" + name);
        } else {
            PreparedStatement statement = connection.prepareStatement("select * from data_storage where id= ?");
            statement.setLong(1, parentID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            file = new File(rs.getString("LINK_ON_DISK") + "/" + name);
        }
        file.mkdir();
        return file;
    }

    private void rmDir(File file) {
        if (file.exists()) {
            file.delete();
        }
    }

    @Override
    public void updateCatalog(Long id, String name, String linkOnDisk) {
        File file = null;
        try (Connection connection = DriverManager.getConnection(DBConnectionInfo.URL, DBConnectionInfo.DB_USER, DBConnectionInfo.DB_PASSWORD)) {
            PreparedStatement statement = connection.prepareStatement("UPDATE data_storage SET NAME = ?, link_on_disk = ? where id = ? ");
            statement.setString(1, name);
            statement.setString(2, linkOnDisk);
            statement.setLong(3, id);
            statement.executeUpdate();
            updatecatalogOnDisk(file, connection, id, name, linkOnDisk);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void updatecatalogOnDisk(File file, Connection connection, Long id, String s, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from data_storage where id= ?");
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        file = new File(rs.getString("LINK_ON_DISK"));
        file.renameTo(new File(file.getParent() + "/" + name));
    }
}
