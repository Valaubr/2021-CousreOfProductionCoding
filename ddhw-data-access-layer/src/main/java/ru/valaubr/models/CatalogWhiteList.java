package ru.valaubr.servicelayer.models;

import ru.valaubr.servicelayer.dao.Impl.CatalogDaoImpl;
import ru.valaubr.servicelayer.enums.Permissions;
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
