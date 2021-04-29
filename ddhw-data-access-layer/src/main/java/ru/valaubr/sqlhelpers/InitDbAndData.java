package ru.valaubr.sqlhelpers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
@Component
public class InitDbAndData {
    public void createBase() {
        useSql(new File("src/main/resources/schema.sql"));
    }

    public void createData() {
        useSql(new File("src/main/resources/data.sql"));
    }

    @Autowired
    Connection connection;
    private void useSql(File s) {
        try {
            Statement statement = connection.createStatement();
            BufferedReader br = new BufferedReader(new FileReader(s));
            String line = br.readLine();
            while (line != null) {
                statement.executeUpdate(line);
                line = br.readLine();
            }
            br.close();
        } catch (SQLException e) {
            log.error("Don`t set connection", e);
        } catch (IOException e) {
            log.error("No schema file", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("connection don`t return in pool!" , e);
            }
        }
    }
}
