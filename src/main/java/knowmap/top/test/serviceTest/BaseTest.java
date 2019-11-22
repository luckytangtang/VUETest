package knowmap.top.test.serviceTest;

import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import org.junit.After;
import org.junit.Before;

public class BaseTest  {
    static {
        PropKit.use("config.txt");
    }
    @Before
    public void setUp() throws Exception {
        DruidPlugin dp=new DruidPlugin(PropKit.get("db.url"),PropKit.get("db.username"),PropKit.get("db.password")
                ,PropKit.get("db.class"));
        ActiveRecordPlugin arp=new ActiveRecordPlugin(dp);
        arp.setBaseSqlTemplatePath("/sql");
        arp.addSqlTemplate("main.sql");
        dp.start();
        arp.start();

    }

    @After
    public void tearDown() throws Exception {
    }

}
