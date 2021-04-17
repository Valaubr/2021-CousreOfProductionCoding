package DataClass;

import lombok.Data;

import java.util.List;

@Data
public class ModerationQueue {
    private Long id;
    private Catalog catalog;
    private List<Document> documents;
}
