package ru.valaubr.servicelayer.sqlHelpers;

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