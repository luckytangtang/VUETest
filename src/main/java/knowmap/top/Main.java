package knowmap.top;

import com.jfinal.config.*;
import com.jfinal.core.Const;
import com.jfinal.kit.PropKit;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import knowmap.top.config.Interceptors.CORSInterceptor;
import knowmap.top.config.Interceptors.TokenInterceptor;
import knowmap.top.config.routers.*;
import knowmap.top.utils.FilePathUtils;

import java.io.File;
import java.nio.file.Path;

public class Main extends JFinalConfig {

    private static Log log = Log.getLog(JFinalConfig.class);;
    public static void main(String[] args) {
        UndertowServer.create(Main.class, "undertow.txt");
        UndertowServer.start(Main.class);
    }

    static {
        PropKit.use("config.txt");
    }

    // JFinal 会在系统启动完成之后回调
    public void onStart() {
        log.info("====== 项目启动成功 ======");
    }

    public void configConstant(Constants constants) {
        constants.setEncoding("utf-8");
        // 开启对 jfinal web 项目组件 Controller、Interceptors、Validator 的注入
        constants.setInjectDependency(true);

        // 开启对超类的注入。不开启时可以在超类中通过 Aop.get(...) 进行注入
        constants.setInjectSuperClass(true);
        constants.setDevMode(true);

        constants.setBaseUploadPath(FilePathUtils.getROOT() + "/tmpPdfFile");
        // 最大100M
        constants.setMaxPostSize(Const.DEFAULT_MAX_POST_SIZE * 10);


    }

    public void configRoute(Routes routes) {
        routes.add(new PdfFileRouters()).
                add(new TaskRouters()).
                add(new DocumentRouters()).
                add(new PingRouters()).
                add(new CommonFileRouters()).
                add(new UserAccountRouters()).
                add(new LoginRouters()).
                add(new PdfBlockRouters()
                );
    }

    public void configEngine(Engine engine) {

    }

    public void configPlugin(Plugins plugins) {
        DruidPlugin dp = new DruidPlugin(PropKit.get("db.url"), PropKit.get("db.username"),
                PropKit.get("db.password"), PropKit.get("db.class"));
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        arp.setBaseSqlTemplatePath("/sql");
        arp.addSqlTemplate("main.sql");
        // blob缓存
        RedisPlugin blobRedis = new RedisPlugin("blob", "148.70.210.242", 6379, "jp123");
        plugins.add(blobRedis);

        dp.start();
        arp.start();
    }

    public void configInterceptor(Interceptors interceptors) {
        // 支持跨域请求
        interceptors.
                add(new CORSInterceptor());
//                add(new TokenInterceptor());
    }

    public void configHandler(Handlers handlers) {

    }
}
