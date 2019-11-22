package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.managers.task.controller.TaskController;

public class TaskRouters extends Routes {
    @Override
    public void config() {
        super.add("/task", TaskController.class);
    }
}
