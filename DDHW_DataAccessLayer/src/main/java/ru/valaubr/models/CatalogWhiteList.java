package ru.valaubr.services.models;

import ru.valaubr.services.dao.Impl.CatalogDaoImpl;
import ru.valaubr.services.enums.Permissions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogWhiteList {
    private Long id;
    private User user;
    private CatalogDaoImpl catalogDaoImpl;
    private Permissions permissions;
}
