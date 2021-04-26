package ru.valaubr.servicelayer;

import org.junit.jupiter.api.Test;
import ru.valaubr.servicelayer.services.CatalogService;

class CatalogServiceTest {
    CatalogService catalogService = new CatalogService();
    @Test
    public void test() {
        catalogService.getCatalogData(1L).forEach(System.out::println);
    }
}