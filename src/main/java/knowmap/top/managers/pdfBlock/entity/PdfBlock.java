package knowmap.top.managers.pdfBlock.entity;

import lombok.Data;

import java.sql.Blob;

@Data
public class PdfBlock {
    private Long id;

    private Integer blockOrder;

    private Integer type;

    private String checksum;

    private String textBody;

    private String fontFamily;

    private Integer fontSize;

    private  Float x1;

    private Float y1;

    private  Float x2;

    private  Float y2;

    private Long pageId;


}
