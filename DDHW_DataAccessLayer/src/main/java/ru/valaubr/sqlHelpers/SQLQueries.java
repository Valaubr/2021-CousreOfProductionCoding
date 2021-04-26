package ru.valaubr.services.sqlHelpers;

public class SQLQueries {
    public static final String INSERT_DATA_STORAGE = "insert into data_storage (PARENT_ID ,NAME ,CREATION_DATE ,LINK_ON_DISK ,AUTHOR ,FOLDER) values(?,?,?,?,?,?)";
    public static final String GET_DOC_BY_ID = "SELECT * FROM data_storage AS ds LEFT JOIN document AS doc ON ds.id = doc.data_storage where id = ?";
    public static final String INSERT_DOC = "insert into document values(?,?,?,?,?)";
    public static final String SELECT_FROM_DS = "select * from data_storage where id = ?";
    public static final String SELECT_FROM_DS_BY_PID = "select * from data_storage where parent_id = ?";
    public static final String SELECT_FROM_DS_BY_PID_ISNULL = "select * from data_storage where parent_id is null";
    public static final String UPDATE_DS = "UPDATE data_storage SET NAME = ?, link_on_disk = ? where id = ? ";
    public static final String SELECT_FROM_DS_BY_PID_NAME = "select * from data_storage where PARENT_ID = ? and name = ? and folder = false or PARENT_ID is null and name = ? and folder = false order by id";
    public static final String SELECT_USER = "select * from user where email = ?";
}
