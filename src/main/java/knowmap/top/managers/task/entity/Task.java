package knowmap.top.managers.task.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Task {
    private Long id;

    private String errorInfo;

    private Integer status;

    private Long docId;

    // todo 待同步mysql
    private Integer retryTimes;
}
