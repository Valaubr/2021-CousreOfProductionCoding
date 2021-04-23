package ru.valaubr.sql_work;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class InitDbAndData {
    public void createBase() {
        try {
            Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("create table role (name varchar(255) PRIMARY KEY);\n" +
                    "create table importance (name varchar(255) PRIMARY KEY);\n" +
                    "create table permissions (name varchar(255) PRIMARY KEY);\n" +
                    "create table user (email varchar(255) primary key, password varchar(255), first_name varchar(255), last_name varchar(255), role varchar(255), foreign key (role) references role(name));\n" +
                    "CREATE TABLE data_storage(ID INT PRIMARY KEY auto_increment , parent_id int null,\n" +
                    "   NAME VARCHAR(255), creation_date date, link_on_disk text, author varchar(255), folder boolean, foreign key (author) references user(email), foreign key (parent_id) references data_storage(id));\n" +
                    "\n" +
                    "CREATE TABLE document(data_storage int primary key, description text, importance varchar(255), version int, old_version int null,foreign key (data_storage) references data_storage(id), foreign key (importance) references importance(name), foreign key (old_version) references document(data_storage));\n");
        } catch (SQLException e) {
            log.error("Don`t set connection", e);
        }
    }

    public void createData() {
        try {
            Connection connection = ConnectionPool.getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into role values ('ADMINISTRATOR');\n" +
                    "insert into role values ('USER');\n" +
                    "insert into permissions values('READ');\n" +
                    "insert into permissions values('WRITE');\n" +
                    "insert into permissions values('MODERATOR');\n" +
                    "insert into importance values('LOW');\n" +
                    "insert into importance values('NORMAL');\n" +
                    "insert into importance values('HIGH');\n" +
                    "insert into user values('nmail', 'pass', 'Ilya', 'pa pa pa', 'ADMINISTRATOR');" +
                    "insert into data_storage (parent_id, name, creation_date, link_on_disk, author, folder) values(null, 'First catalog', '2021-04-22', '~/', 'nmail', true);\n" +
                    "insert into data_storage (parent_id, name, creation_date, link_on_disk, author, folder) values(null, 'nmae', '2021-04-13', '~/', 'nmail', true);" +
                    "insert into data_storage (parent_id, name, creation_date, link_on_disk, author, folder) values(1, 'First document', '2021-04-13', '~/First catalog/', 'nmail', false);\n" +
                    "insert into data_storage (parent_id, name, creation_date, link_on_disk, author, folder) values(1, 'nmae', '2021-04-22', '~/First catalog/', 'nmail', false);" +
                    "insert into document values(3, 'first document. it`s cool but why?', 'LOW', '1', null);\n" +
                    "insert into document values(4, 'it`s nmae document, why i did it? id dont understend...', 'HIGH', '1', null);");
        } catch (SQLException e) {
            log.error("Don`t set connection", e);
        }
    }
}
//insert into role values('admin');
//insert into user values('nmail', 'pass', 'Ilya', 'pa pa pa', 'admin');
//insert into data_storage values(1, null, 'nmae', '2021-04-22', '~/', 'nmail');
//insert into data_storage values(2, 1, 'nmae', '2021-04-22', '~/', 'nmail');