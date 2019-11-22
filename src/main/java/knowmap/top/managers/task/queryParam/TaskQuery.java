package knowmap.top.managers.task.queryParam;

import knowmap.top.common.BaseQuery;
import lombok.Data;

@Data
public class TaskQuery extends BaseQuery {
    private Integer taskStatus;
}
