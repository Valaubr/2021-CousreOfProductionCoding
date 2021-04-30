package ru.valaubr;

import org.junit.jupiter.api.Test;
import ru.valaubr.services.CatalogService;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CatalogServiceTest {
    CatalogService catalogService = new CatalogService();
    @Test
    public void test() {
        assertNotNull(catalogService.getCatalogData(1L));
    }
}