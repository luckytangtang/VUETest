package knowmap.top.managers.document.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Accessors(chain = true)
@Data
public class Document {
    private Long id;

    private Timestamp createTime;

    private Long userId;

    private Integer status;

    private String fileName;

    private String author;

    private String title;

    private Long blobId;

    private Integer areaVersion;
}
