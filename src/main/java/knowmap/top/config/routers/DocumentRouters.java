package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.managers.document.controller.DocumentController;

public class DocumentRouters extends Routes {
    @Override
    public void config() {
        super.add("/document", DocumentController.class);
    }
}
