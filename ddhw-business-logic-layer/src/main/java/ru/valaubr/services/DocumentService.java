package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.dto.DocumentDto;
import ru.valaubr.jpa.DocumentRepo;

import java.io.BufferedReader;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepo documentRepo;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public DocumentDto getDoc(Long id) {
        return new DocumentDto(documentRepo.findById(id));
    }

    public boolean updateDoc(BufferedReader br) {
        return false;
    }

    public void createDoc(BufferedReader br) {
    }

    public void CopyDoc() {
    }

    private void SendMessage() {
    }

    private void checkAuthor() {
    }

    private void upgradeVersion() {
    }
}
