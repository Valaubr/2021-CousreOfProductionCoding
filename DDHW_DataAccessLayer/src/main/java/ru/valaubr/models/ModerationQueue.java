package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.DAO.DocumentDAO;

import java.util.List;

@Getter
@Setter
public class ModerationQueue {
    private Long id;
    private CatalogDAO catalogDAO;
    private List<DocumentDAO> documentDAOS;
}
