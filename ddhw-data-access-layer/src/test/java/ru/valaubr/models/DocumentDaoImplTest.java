package ru.valaubr.models;

import org.junit.jupiter.api.Test;
import ru.valaubr.dao.impl.DocumentDaoImpl;
import ru.valaubr.enums.Importance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DocumentDaoImplTest {
    DocumentDaoImpl documentDaoImpl = new DocumentDaoImpl();

    @Test
    void getDoc() {
        //если через этот метод попробовать получить каталог то
        //отвалится с исключением NPE
        assertNotNull(documentDaoImpl.getDoc(3L));
    }

    @Test
    void createDoc() {
        assertTrue(documentDaoImpl.createDoc(null, "nmae", new ServiceUser(), "/path/", "descr", Importance.LOW));
    }

    @Test
    void updateDoc() {
        assertTrue(documentDaoImpl.updateDoc(4l, "Normal_name", "/normal_link", "This is a documentDaoImpl", Importance.LOW));
    }
}