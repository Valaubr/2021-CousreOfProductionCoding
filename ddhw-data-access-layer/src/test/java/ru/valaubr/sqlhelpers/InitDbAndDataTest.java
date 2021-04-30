package ru.valaubr.sqlhelpers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class InitDbAndDataTest {
    @Autowired
    InitDbAndData initDbAndData;

    @BeforeEach
    public void createDB() {
        initDbAndData.createBase();
    }

    @Test
    public void createDataOnDB() {
        initDbAndData.createData();
    }
}