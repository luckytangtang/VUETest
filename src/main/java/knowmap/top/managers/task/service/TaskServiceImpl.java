package knowmap.top.managers.task.service;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.common.BaseQuery;
import knowmap.top.managers.task.entity.Task;
import knowmap.top.managers.task.queryParam.TaskQuery;
import knowmap.top.utils.RecordUtils;

import java.util.ArrayList;
import java.util.List;

public class TaskServiceImpl implements TaskService {
    /**
     *
     * @param task
     * @return -1 error or task's id
     */
    @Override
    public Long saveTask(Task task) {
        Record record =  RecordUtils.getInstance().objToRecord(task, Task.class);
        return Db.save("task", record)
                ? record.getLong("id") : -1L;
    }


    @Override
    public List<Task> query(BaseQuery query) {
//        return recordToObj(Db.template("task.fun002.02").
//                paginate(query.getPageIndex(), query.getPageSize()).
//                getList());
        List<Task> tasks = new ArrayList<>();
        Db.template("task.fun002.02").
                paginate(query.getPageIndex(), query.getPageSize()).
                getList().stream().forEach(
                        record -> tasks.add(RecordUtils.getInstance().recordToObj(record, Task.class))
        );

        return tasks;
    }

    @Override
    public List<Task> queryByStatus(TaskQuery query) {
        return convert(Db.template("task.fun002.01", query.getTaskStatus()).
                paginate(query.getPageIndex(), query.getPageSize()).
                getList());
    }

    // record 转化为 task
    public static List<Task> convert(List<Record> records) {
        List<Task> result = new ArrayList<>();
        records.stream().forEach(record -> {
            Task task = new Task();
            task.setId(record.getLong("id"));
            task.setDocId(record.getLong("docId"));
            task.setErrorInfo(record.getStr("errorInfo"));
            task.setStatus(record.getInt("status"));

            result.add(task);
        });

        return result;
    }
}
