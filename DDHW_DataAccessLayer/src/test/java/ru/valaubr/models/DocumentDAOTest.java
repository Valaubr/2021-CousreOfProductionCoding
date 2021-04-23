package ru.valaubr.models;

import org.junit.jupiter.api.Test;
import ru.valaubr.enums.Importance;

import static org.junit.jupiter.api.Assertions.*;

class DocumentTest {
    Document document = new Document();

    @Test
    void getDoc() {
        assertNotNull(document.getDoc(4L));
    }

    @Test
    void createDoc() {
        document.createDoc(null, "nmae", new User(), "/path/", "descr", Importance.LOW);
    }

    @Test
    void updateDoc() {
        document.updateDoc(4l, "Normal_name", "/normal_link", "This is a document", Importance.LOW);
    }
}