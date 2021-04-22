package ru.valaubr.sql_work;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class JDBCWorkTest {
    JDBCWork jdbcWork = new JDBCWork();
    @Test
    public void testConnection() {
        assertNotNull(jdbcWork.connect());
    }
}