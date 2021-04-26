package ru.valaubr.servicelayer.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.servicelayer.dao.Impl.CatalogDaoImpl;
import ru.valaubr.servicelayer.dao.Impl.DocumentDaoImpl;

import java.util.List;

@Getter
@Setter
public class ModerationQueue {
    private Long id;
    private CatalogDaoImpl catalogDaoImpl;
    private List<DocumentDaoImpl> documentDaoImpl;
}
