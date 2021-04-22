package ru.valaubr.sql_work;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class DDLScript {
    public void createBase() {
        Connection connection = null;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(DBConnectionInfo.URL, DBConnectionInfo.DB_USER, DBConnectionInfo.DB_PASSWORD);
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table role (name varchar(255) PRIMARY KEY);\n" +
                    "create table importance (name varchar(255) PRIMARY KEY);\n" +
                    "create table permissions (name varchar(255) PRIMARY KEY);\n" +
                    "create table user (email varchar(255) primary key, password varchar(255), first_name varchar(255), last_name varchar(255), role varchar(255), foreign key (role) references role(name));\n" +
                    "CREATE TABLE data_storage(ID INT PRIMARY KEY,\n" +
                    "   NAME VARCHAR(255), creation_date date, link_on_disk text, author varchar(255), foreign key (author) references user(email));\n" +
                    "CREATE TABLE document(data_storage int primary key, description text, importance varchar(255), version int, foreign key (data_storage) references data_storage(id), foreign key (importance) references importance(name));\n");
        } catch (ClassNotFoundException e) {
            log.error("Don`t find h2 driver ", e);
        } catch (SQLException e) {
            log.error("Don`t set connection", e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error("Connection don`t closed", e);
            }
        }
    }
}
//insert into role values('admin');
//insert into user values('nmail', 'pass', 'Ilya', 'pa pa pa', 'admin');
//insert into data_storage values(1, null, 'nmae', '2021-04-22', '~/', 'nmail');
//insert into data_storage values(2, 1, 'nmae', '2021-04-22', '~/', 'nmail');