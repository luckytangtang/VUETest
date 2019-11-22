package knowmap.top.managers.pdfPage.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PdfPage {
    private Long id;

    private Integer pageIndex;

    private Float width;

    private Float height;

    private Float linesSpace;

    private Integer fontSize;

    private String fontFamily;

    private Float columnWidth;

    private Long blobId;
}
