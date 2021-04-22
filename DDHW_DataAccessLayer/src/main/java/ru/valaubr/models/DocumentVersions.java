package models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DocumentVersions {
    private Long id;
    private Document document;
    private Integer currentVersion;
    private List<Document> oldVersions;
}
