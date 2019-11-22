package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.managers.userAccount.controller.UserController;

public class UserAccountRouters extends Routes {
    @Override
    public void config() {
        super.add("/userAccounts", UserController.class);
    }
}
