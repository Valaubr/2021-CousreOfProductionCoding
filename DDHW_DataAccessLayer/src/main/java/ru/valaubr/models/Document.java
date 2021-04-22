package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import ru.valaubr.DAO.DocumentDAO;

@Getter
@Setter
@Slf4j
public class Document extends DataStorage implements DocumentDAO {
//    private List<File> files;
//    private String description;
//    private Importance importance;
//    private Integer version;

static {
    try {
        Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
        LoggerFactory.getLogger(Document.class).error(e.getMessage(), e);
    }
}

    @Override
    public void createDoc(Long parentID, String name, User author, String linkOnDisk) {

    }

    @Override
    public void updateDoc(Long id, String name, String linkOnDisk) {

    }
}
