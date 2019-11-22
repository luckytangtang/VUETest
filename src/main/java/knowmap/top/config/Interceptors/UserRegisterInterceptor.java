package knowmap.top.config.Interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.log.Log;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.managers.userAccount.entity.UserAccount;
import knowmap.top.serviceCode.UserAccountCode;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.util.regex.Pattern;

public class UserRegisterInterceptor implements Interceptor {

    Log log = Log.getLog(UserRegisterInterceptor.class);
    @Override
    public void intercept(Invocation inv) {
//        UserAccount userAccount = inv.getController().getModel(UserAccount.class);
        String username = inv.getController().get("username");
        String password = inv.getController().get("password");
        String phone = inv.getController().get("phone");
        String email = inv.getController().get("email");
        Long birthday = inv.getController().getLong("birthday");
        String address = inv.getController().get("address");


        boolean flag = true;
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            flag = false;
        }

        if (!flag || !Pattern.matches("^(?!_)(?!.*?_$)[a-zA-Z0-9_\\u4e00-\\u9fa5]{5,10}$", username)) {
            flag = false;
        }


        if (!flag) {
            ResponseObj responseObj = new ResponseObj();
            responseObj.setCode(ResponseStatus.Ok.getCode()).
                    setServiceCode(UserAccountCode.ParamsInvalid.getCode()).
                    setServiceMessage(UserAccountCode.ParamsInvalid.getMessage());
            inv.getController().renderJson(responseObj);
            log.info("UserRegisterInterceptor#intercept#reject username: " + username);
            inv.getController().renderJson(responseObj);
            return;
        }


        log.info("UserRegisterInterceptor#intercept#accept username: " + username);
        inv.invoke();

    }
}
