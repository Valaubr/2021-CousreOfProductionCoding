package ru.valaubr.models;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.valaubr.dao.impl.CatalogDaoImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogDaoImplTest {
    @Autowired
    CatalogDaoImpl catalogDaoImpl;

    @Test
    public void getAllTest() {
        List<Document> list = catalogDaoImpl.getAll(null);
        assertNotNull(list);
        list.forEach(o -> {
            System.out.println(o.getName() + " " + o.getDateOfCreation() + " " + o.getPathOnDisk() + " " + o.getAuthor().getEmail());
        });
    }

    @Test
    public void createCatalogTest() {
        catalogDaoImpl.createCatalog(null, "wabudiDabuday", new User());
    }

    @Test
    public void updateCatalogTest() {
        catalogDaoImpl.updateCatalog(1L, "Now i`m going to CHANGES CHAGEEEEES", "/");
    }
}