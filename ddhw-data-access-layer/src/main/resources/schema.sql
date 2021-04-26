create table role (name varchar(255) PRIMARY KEY);
create table importance (name varchar(255) PRIMARY KEY);
create table permissions (name varchar(255) PRIMARY KEY);
create table user (email varchar(255) primary key, password varchar(255), first_name varchar(255), last_name varchar(255), role varchar(255), foreign key (role) references role(name));
CREATE TABLE data_storage(ID INT PRIMARY KEY auto_increment , parent_id int null, NAME VARCHAR(255), creation_date date, link_on_disk text, author varchar(255), folder boolean, foreign key (author) references user(email), foreign key (parent_id) references data_storage(id));
CREATE TABLE document(data_storage int primary key, description text, importance varchar(255), version int, old_version int null,foreign key (data_storage) references data_storage(id), foreign key (importance) references importance(name), foreign key (old_version) references document(data_storage));