package knowmap.top.config.routers;

import com.jfinal.config.Routes;
import knowmap.top.test.Ping;

public class PingRouters extends Routes {
    @Override
    public void config() {
        super.add("/ping", Ping.class);
    }
}
