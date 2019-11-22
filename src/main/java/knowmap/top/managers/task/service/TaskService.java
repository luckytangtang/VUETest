package knowmap.top.managers.task.service;

import knowmap.top.common.BaseQuery;
import knowmap.top.managers.task.entity.Task;
import knowmap.top.managers.task.queryParam.TaskQuery;

import java.util.List;

public interface TaskService {
    Long saveTask(Task task);

    List<Task> query(BaseQuery query);

    List<Task> queryByStatus(TaskQuery query);
}
