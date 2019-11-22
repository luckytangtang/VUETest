package knowmap.top.managers.pdfBlock.queryParam;

import knowmap.top.common.BaseQuery;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BlockQuery extends BaseQuery {
    private Long blobId;

    private Integer areaVersion;

    private Integer pageStart;

    public Integer getPageStart () {
        return (getPageIndex() - 1) * getPageSize();
    }
}
