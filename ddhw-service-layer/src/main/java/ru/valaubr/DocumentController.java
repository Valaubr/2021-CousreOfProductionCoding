package ru.valaubr;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.services.DocumentService;

import javax.servlet.http.HttpServlet;

@RestController
public class DocumentController {
    @Autowired
    private DocumentService documentService;
    private final Gson gson = new Gson();

    @GetMapping("/api/doc")
    public DocumentDto getById(@RequestParam Long id) {
        return documentService.getDoc(id);
    }

    @PostMapping("/api/doc")
    public void createDoc() {

    }

    @PutMapping("/api/doc")
    public void updateDoc() {

    }
}
