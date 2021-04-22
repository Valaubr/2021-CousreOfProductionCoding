package models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ModerationQueue {
    private Long id;
    private Catalog catalog;
    private List<Document> documents;
}
