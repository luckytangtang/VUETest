package knowmap.top.managers.task.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.plugin.redis.Cache;
import com.jfinal.plugin.redis.Redis;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.managers.task.entity.Task;
import knowmap.top.managers.task.queryParam.TaskQuery;
import knowmap.top.managers.task.service.TaskService;
import knowmap.top.managers.task.service.TaskServiceImpl;

import java.util.List;

/**
 * task 后端管理接口
 */
public class TaskController extends Controller {

    @Inject(TaskServiceImpl.class)
    private TaskService taskService;


    @ActionKey("/task/ta")
    public void tasks() {
        ResponseObj responseObj = new ResponseObj();
        try {
            Cache blobCache = Redis.use("blob");
            blobCache.set("e", 1233);
            TaskQuery query = new TaskQuery();
            query.setPageIndex(getInt("pageIndex"));
            query.setPageSize(getInt("pageSize"));
            Integer status;
            List<Task> list;
            if ((status = getInt("taskStatus")) != null) {
                query.setTaskStatus(status);
                list = taskService.queryByStatus(query);
            } else {
                list = taskService.query(query);
            }

            responseObj.setCode(ResponseStatus.Ok.getCode()).
                    setData(list).
                    setMessage("response ok");
        } catch (Exception ex) {
            ex.printStackTrace();
            responseObj.setCode(ResponseStatus.Error.getCode()).
                    setData(ex.getCause()).
                    setMessage(ex.getMessage());
        }

        renderJson(responseObj);
    }



}
