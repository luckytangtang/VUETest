package knowmap.top.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询条件基类
 */
@Data
@Accessors(chain = true)
public class BaseQuery {
    // 查询页面大小
    private Integer pageSize;

    // 查询页面索引
    private Integer pageIndex;
}
