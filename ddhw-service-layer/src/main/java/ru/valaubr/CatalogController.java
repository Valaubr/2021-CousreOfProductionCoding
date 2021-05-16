package ru.valaubr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.Helper.RequestGetParamHelper;
import ru.valaubr.dto.CatalogDto;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.models.DataStorage;
import ru.valaubr.services.CatalogService;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    @Autowired
    private CatalogService service;
    private final Gson gson = new Gson();
    CatalogDto dto;
    String auth;

    @GetMapping
    public List<CatalogDto> getChildrenById(@RequestParam Long id) {
        return service.getCatalogData(id);
    }

    @PostMapping
    public ResponseEntity createCatalog(@RequestHeader HttpHeaders headers, @RequestBody String catalogData) {
        initDataToThisResponse(catalogData, headers);
        return service.createCatalog(dto, auth);
    }

    @PutMapping
    public ResponseEntity updateCatalog(@RequestHeader HttpHeaders headers, @RequestBody String catalogData) {
        initDataToThisResponse(catalogData, headers);
        return service.updateCatalog(dto, auth);
    }

    private void initDataToThisResponse(String metaData, HttpHeaders headers) {
        dto = gson.fromJson(metaData, CatalogDto.class);
        auth = RequestGetParamHelper.getAuthToken(headers);
    }
}
