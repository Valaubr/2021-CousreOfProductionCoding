package ru.valaubr.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.valaubr.dao.DocumentDao;
import ru.valaubr.models.Document;

import java.io.BufferedReader;

@Service
public class DocumentService {
    @Autowired
    private DocumentDao documentDao;
    private Document doc;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Document getDoc(Long id) {
        return documentDao.findById(id);
    }

    public Document updateDoc(BufferedReader br) {
        doc = gson.fromJson(br, Document.class);
        return documentDao.update(doc);
    }

    public void createDoc(BufferedReader br) {
        doc = gson.fromJson(br, Document.class);
        documentDao.create(doc);
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
