package ru.valaubr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.valaubr.Helper.RequestGetParamHelper;
import ru.valaubr.dto.DocumentDto;
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
    public ResponseEntity<Object> createDoc(@RequestParam("file") MultipartFile file,
                                            @RequestHeader HttpHeaders headers,
                                            @RequestParam Long parentId,
                                            @RequestParam String description,
                                            @RequestParam String importance) {
        auth = RequestGetParamHelper.getAuthToken(headers);
        return documentService.createDoc(file, auth, parentId, description, importance);
    }

    @PutMapping
    public ResponseEntity updateDoc(@RequestParam("file") MultipartFile file,
                                    @RequestHeader HttpHeaders headers,
                                    @RequestParam Long id,
                                    @RequestParam Long parentId,
                                    @RequestParam String description,
                                    @RequestParam String importance) {
        auth = RequestGetParamHelper.getAuthToken(headers);
        if (parentId == null) {
            return documentService.updateDoc(file, auth, id, description, importance);
        } else {
            return documentService.updateDocWithMove(file, auth, id, parentId, description, importance);
        }
    }

    private void initDataToThisResponse(String metaData, HttpHeaders headers) {
        cd = gson.fromJson(metaData, DocumentDto.class);
        auth = RequestGetParamHelper.getAuthToken(headers);
    }
}
