package ru.valaubr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.dto.CatalogDto;
import ru.valaubr.services.CatalogService;

import java.util.List;

@RestController
public class CatalogController {
    @Autowired
    private CatalogService service;
    private final Gson gson = new Gson();

    @GetMapping("/api/catalog")
    public List<CatalogDto> getChildrenById(@RequestParam Long id) {
        return service.getCatalogData(id);
    }

    @PostMapping("/api/catalog")
    public void createCatalog(@RequestBody CatalogDto catalogData) {
        service.createCatalog(catalogData);
    }

    @PutMapping("/api/catalog")
    public void updateCatalog() {

    }
}
