package knowmap.top.managers.userAccount.controller;

import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.config.Interceptors.TokenInterceptor;
import knowmap.top.managers.userAccount.entity.UserAccount;
import knowmap.top.managers.userAccount.service.UserService;
import knowmap.top.managers.userAccount.service.UserServiceImpl;
import knowmap.top.serviceCode.UserAccountCode;
import knowmap.top.utils.HashUtils;
import knowmap.top.utils.JwtUtils;

import java.util.HashMap;
import java.util.Map;


@Clear(TokenInterceptor.class)
public class LoginController extends Controller {
    @Inject(UserServiceImpl.class)
    UserService userService;

    static Log log = Log.getLog(LoginController.class);
    /**
     * 登陆（username + password)
     */
    public void login001() {
        ResponseObj responseObj = new ResponseObj();
        try {
            String username = get("username");
            String password = get("password");
            UserAccount userAccount = userService.queryByName(username);
            if (userAccount == null) {
                responseObj.setCode(ResponseStatus.Ok.getCode()).
                        setServiceCode(UserAccountCode.UsernameInvalid.getCode()).
                        setServiceMessage(UserAccountCode.UsernameInvalid.getMessage());

            } else if (userAccount.getPassword().equals(HashUtils.hash(password, userAccount.getSalt()))) {
                Map<String, Object> map = new HashMap<>();
                String secret = JwtUtils.createSecret();
                System.out.println(secret);
                userAccount.setSecret(secret);
                userService.updateSecret(secret, userAccount.getId());
                map.put("token", JwtUtils.createJWT(username, secret));
                map.put("username", userAccount.getUsername());
                responseObj.setCode(ResponseStatus.Ok.getCode()).
                        setData(map).
                        setServiceCode(UserAccountCode.OK.getCode()).
                        setServiceMessage(UserAccountCode.OK.getMessage());

            } else {
                responseObj.setCode(ResponseStatus.Ok.getCode()).
                        setServiceCode(UserAccountCode.PasswordError.getCode()).
                        setServiceMessage(UserAccountCode.PasswordError.getMessage());

            }
        } catch (Exception ex) {
            log.error("LoginController#login001", ex);
            responseObj.setCode(ResponseStatus.Error.getCode()).setMessage("服务器异常");
        }


        renderJson(responseObj);
    }

    /**
     * 登陆（email + password)
     */
    public void login002() {
        String email = get("email");
        String password = get("password");

        renderJson("status", "接口待完善");
    }

    /**
     * 登陆（phone + password)
     */
    public void long003() {
        String phone = get("phone");
        String password = get("password");

        renderJson("status", "接口待完善");
    }
}
