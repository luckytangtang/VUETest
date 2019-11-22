package knowmap.top.managers.document.queryParam;

import knowmap.top.common.BaseQuery;
import lombok.Data;

@Data
public class DocumentQuery extends BaseQuery {
    private Long userId;

    private Integer pageStart;

    public Integer getPageStart () {
        return (getPageIndex() - 1) * getPageSize();
    }
}
