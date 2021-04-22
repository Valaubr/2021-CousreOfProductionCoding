package ru.valaubr.models;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {
    Catalog catalog = new Catalog();

    @Test
    public void getAllTest() {
        List<Document> list = catalog.getAll(1L);
        assertNotNull(list);
        list.forEach(o -> {
            System.out.println(o.getName() + " " + o.getDateOfCreation() + " " + o.getPathOnDisk());
        });
    }

    @Test
    public void createCatalogTest() {
        catalog.createCatalog(1L, "wabudiDabuday", new User(), "/");
    }

    @Test
    public void updateCatalogTest() {
        catalog.updateCatalog(1L, "Now i`m going to CHANGES CHAGEEEEES", "/");
    }
}