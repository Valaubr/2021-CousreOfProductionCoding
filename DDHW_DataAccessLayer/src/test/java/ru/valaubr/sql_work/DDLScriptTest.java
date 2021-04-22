package ru.valaubr.sql_work;

import org.junit.jupiter.api.Test;


class DDLScriptTest {
    DDLScript ddlScript = new DDLScript();
    @Test
    public void testConnection() {
        ddlScript.createBase();
    }
}