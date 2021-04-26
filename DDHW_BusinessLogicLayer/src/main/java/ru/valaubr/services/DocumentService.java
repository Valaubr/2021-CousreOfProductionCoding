package ru.valaubr;

import ru.valaubr.dao.Impl.DocumentDaoImpl;
import ru.valaubr.models.Document;

public class DocumentService {
    private DocumentDaoImpl documentDaoImpl;

    public Document get(Long id) {
        return documentDaoImpl.getDoc(id);
    }

    public void update() {

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
