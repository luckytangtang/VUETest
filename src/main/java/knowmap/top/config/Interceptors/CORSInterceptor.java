package knowmap.top.config.Interceptors;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 解决跨域
 */
public class CORSInterceptor implements Interceptor {
    @Override
    public void intercept(Invocation inv) {
        inv.getController().getResponse().addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS,DELETE,PUT");
        inv.getController().getResponse().addHeader("Access-Control-Allow-Headers", "Content-Type, x-requested-with, X-Custom-Header, authorization, token");
        // 测试环境为 * ，生产环境需要写实际 IP
        inv.getController().getResponse().addHeader("Access-Control-Allow-Origin", "*");
        inv.getController().getResponse().addHeader("Access-Control-Request-Headers", "Content-Type, x-requested-with, X-Custom-Header, authorization, token");
        if (!"OPTIONS".equals(inv.getController().getRequest().getMethod())) {
            inv.invoke();
        } else {
            inv.getController().renderNull();
        }
    }
}
