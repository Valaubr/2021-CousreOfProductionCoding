package ru.valaubr.servicelayer.sqlHelpers;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class InitDbAndData {
    public void createBase() {
        useSql(new File("src/main/resources/schema.sql"));

    }

    public void createData() {
        useSql(new File("src/main/resources/data.sql"));
    }

    private void useSql(File s) {
        try {
            Connection connection = ConnectionPool.getPool().getConnection();
            Statement statement = connection.createStatement();
            BufferedReader br = new BufferedReader(new FileReader(s));
            String line = br.readLine();
            while (line != null){
                statement.executeUpdate(line);
                line = br.readLine();
            }
        } catch (SQLException e) {
            log.error("Don`t set connection", e);
        } catch (IOException e) {
            log.error("No schema file", e);
        }
    }
}
