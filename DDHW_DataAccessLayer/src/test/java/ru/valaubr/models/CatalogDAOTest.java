package ru.valaubr.models;

import org.junit.jupiter.api.Test;
import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.DAO.DocumentDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogDAOTest {
    CatalogDAO catalogDAO = new CatalogDAO();

    @Test
    public void getAllTest() {
        List<Document> list = catalogDAO.getAll(1L);
        assertNotNull(list);
        list.forEach(o -> {
            System.out.println(o.getName() + " " + o.getDateOfCreation() + " " + o.getPathOnDisk());
        });
    }

    @Test
    public void createCatalogTest() {
        catalogDAO.createCatalog(null, "wabudiDabuday", new User());
    }

    @Test
    public void updateCatalogTest() {
        catalogDAO.updateCatalog(1L, "Now i`m going to CHANGES CHAGEEEEES", "/");
    }
}