package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.managers.userAccount.controller.LoginController;

public class LoginRouters extends Routes {
    @Override
    public void config() {
        super.add("/commonAccount", LoginController.class);
    }
}
