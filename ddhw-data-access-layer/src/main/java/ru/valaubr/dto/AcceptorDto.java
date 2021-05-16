package ru.valaubr.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AcceptorDto {
    private Long id;
    private List<Long> documents;
}
