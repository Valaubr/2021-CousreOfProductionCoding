package ru.valaubr.dto;

import lombok.Getter;
import lombok.Setter;
import ru.valaubr.enums.Permissions;

@Getter
@Setter
public class CatalogWhiteListDto {
    private String email;
    private Long catalog;
    private Permissions permissions;
}
