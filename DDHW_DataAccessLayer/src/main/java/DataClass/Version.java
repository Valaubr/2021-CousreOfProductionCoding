package DataClass;

import lombok.Data;

import java.util.List;

@Data
public class Version {
    private Long id;
    private Document document;
    private Integer currentVersion;
    private List<Document> oldVersions;
}
