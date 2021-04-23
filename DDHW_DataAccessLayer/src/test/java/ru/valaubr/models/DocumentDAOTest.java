package ru.valaubr.models;

import org.junit.jupiter.api.Test;
import ru.valaubr.DAO.DocumentDAO;
import ru.valaubr.enums.Importance;

import static org.junit.jupiter.api.Assertions.*;

class DocumentDAOTest {
    DocumentDAO documentDAO = new DocumentDAO();

    @Test
    void getDoc() {
        //если через этот метод попробовать получить каталог то
        //отвалится с исключением NPE
        assertNotNull(documentDAO.getDoc(3L));
    }

    @Test
    void createDoc() {
        assertTrue(documentDAO.createDoc(null, "nmae", new User(), "/path/", "descr", Importance.LOW));
    }

    @Test
    void updateDoc() {
        assertTrue(documentDAO.updateDoc(4l, "Normal_name", "/normal_link", "This is a documentDAO", Importance.LOW));
    }
}