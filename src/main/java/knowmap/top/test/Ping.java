package knowmap.top.test;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;

public class Ping extends Controller {
    private static Log log = Log.getLog(Ping.class);

    // 访问 http://127.0.0.1:8080/ping
    @ActionKey("/ping")
    public void ping() {
        log.info("======== ping =======");
        // 测试部分



        // 测试结束
        renderJson("status", "ok");
    }
}
