package knowmap.top.managers.pdfBlob.queryParam;

import knowmap.top.common.BaseQuery;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PdfBlobQuery extends BaseQuery {
    private Long id;

    private String checksum;
}
