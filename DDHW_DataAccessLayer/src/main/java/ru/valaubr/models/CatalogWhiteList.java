package ru.valaubr.models;

import ru.valaubr.DAO.CatalogDAO;
import ru.valaubr.enums.Permissions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogWhiteList {
    private Long id;
    private User user;
    private CatalogDAO catalogDAO;
    private Permissions permissions;
}
