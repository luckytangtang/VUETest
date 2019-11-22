package knowmap.top.config.Interceptors;

import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.managers.userAccount.service.UserService;
import knowmap.top.managers.userAccount.service.UserServiceImpl;
import knowmap.top.utils.JwtUtils;

/**
 * token拦截器
 */
public class TokenInterceptor implements Interceptor {
    @Inject(UserServiceImpl.class)
    private UserService userService;

    @Override
    public void intercept(Invocation invocation) {
        Controller controller = invocation.getController();
        try {
            controller.getRequest().setAttribute("uploadFile", controller.getFile());
        } catch (Exception ex) {
            // 不处理
        }

        String token = controller.getRequest().getHeader("token");
        String username = controller.getPara("username");
        String secret;
        String subject;
        if (token == null ||
                username == null ||
                (secret = userService.getSecret(username)) == null ||
                (subject = JwtUtils.validateJWT(token, secret)) == null ||
                !subject.equals(username)) {

            ResponseObj responseObj = new ResponseObj();
            responseObj.setCode(ResponseStatus.TokenInvalid.getCode());
            responseObj.setMessage("token is null or invalid");
            controller.renderJson(responseObj);
            return;
        }

        controller.getResponse().setHeader("token", JwtUtils.createJWT(subject, secret));
        invocation.invoke();
    }
}
