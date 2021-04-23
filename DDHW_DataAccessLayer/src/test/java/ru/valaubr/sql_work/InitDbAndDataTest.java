package ru.valaubr.sql_work;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class InitDbAndDataTest {
    InitDbAndData initDbAndData = new InitDbAndData();
    @BeforeEach
    public void createDB() {
        initDbAndData.createBase();
    }

    @Test
    public void createDataOnDB() {
        initDbAndData.createData();
    }
}