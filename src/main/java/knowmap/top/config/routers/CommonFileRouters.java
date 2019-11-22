package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.managers.downloadFile.controller.CommonFileController;

public class CommonFileRouters extends Routes {
    @Override
    public void config() {
        super.add("/commonFile", CommonFileController.class);
    }
}
