package ru.valaubr.models;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Permissions;

@Getter
@Setter
public class CatalogWhiteList {
    private Long id;
    private User user;
    private DataStorage catalog;
    private Permissions permissions;
}
