package ru.valaubr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.valaubr.Helper.RequestGetParamHelper;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.enums.FileTypesWhiteList;
import ru.valaubr.services.DocumentService;


@RestController
@RequestMapping("/api/doc")
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    private final Gson gson = new Gson();
    private DocumentDto cd;
    private String auth;

    @GetMapping
    public ResponseEntity getById(@RequestParam Long id) {
        DocumentDto out = documentService.getDoc(id);
        if (out != null) {
            return ResponseEntity.ok().body(out);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping
    public ResponseEntity<Object> createDoc(@RequestBody String metaData,
                                            @RequestHeader HttpHeaders headers) {
        initDataToThisResponse(metaData, headers);;
        return documentService.createDoc(cd, auth);
    }

    @PutMapping
    public ResponseEntity updateDoc(@RequestBody String metaData, @RequestHeader HttpHeaders headers) {
        initDataToThisResponse(metaData, headers);
        return documentService.updateDoc(cd, auth);
    }

    private void initDataToThisResponse(String metaData, HttpHeaders headers) {
        cd = gson.fromJson(metaData, DocumentDto.class);
        auth = RequestGetParamHelper.getAuthToken(headers);
    }
}
