package knowmap.top.managers.pdfBlob.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class PdfBlob {
    private Long id;

    private String checksum;

    private Integer status;

    private Timestamp createTime;

    private Long userId;

    private Long fileSize;
}
