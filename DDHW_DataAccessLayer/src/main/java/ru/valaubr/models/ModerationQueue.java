package ru.valaubr.services.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.services.dao.Impl.CatalogDaoImpl;
import ru.valaubr.services.dao.Impl.DocumentDaoImpl;

import java.util.List;

@Getter
@Setter
public class ModerationQueue {
    private Long id;
    private CatalogDaoImpl catalogDaoImpl;
    private List<DocumentDaoImpl> documentDaoImpl;
}
