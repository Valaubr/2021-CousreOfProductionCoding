package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.dao.impl.DocumentDaoImpl;

import java.util.List;

@Getter
@Setter
public class ModerationQueue {
    private Long id;
    private DataStorage catalog;
    private List<DocumentDaoImpl> documentDaoImpl;
}
