package knowmap.top.test.serviceTest;

import com.jfinal.aop.Inject;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import knowmap.top.managers.userAccount.entity.UserAccount;
import knowmap.top.managers.userAccount.service.UserService;
import knowmap.top.managers.userAccount.service.UserServiceImpl;
import knowmap.top.utils.RecordUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class UserServiceImplTest extends BaseTest {
    @Inject(UserServiceImpl.class)
    UserService userService;

    @Test
    public void saveUser() {
        UserAccount user=new UserAccount();
        user.setAddress("Changsha");
        user.setUsername("Jack");
        user.setEmail("lrll@qq.com");
        user.setSalt("ced3c009-e613-4a34-ad06");
        Record record= RecordUtils.getInstance().objToRecord(user,UserAccount.class);
        Db.save("user_account",record);
    }

    @Test
    public void queryByName() {
        String name="llt";
        Record record=Db.findFirst(Db.getSql("user.fun001.01"),name);
        System.out.println(record);
    }

    @Test
    public void updateUser() {
       UserAccount userAccount=RecordUtils.getInstance().recordToObj(Db.findFirst(Db.getSql("user.fun001.03"),1L),UserAccount.class);
       System.out.println(userAccount);
       userAccount.setEmail("1661926957@qq.com");
       userAccount.setGender(1);
       userAccount.setPassword("123456789");
       userAccount.setAddress("CHINA GUANG DONG");
       userAccount.setPhone("18729330982");
        Db.update(Db.getSql("user.fun002.01"),
                userAccount.getUsername(), userAccount.getGender(), userAccount.getSalt(),
                userAccount.getPassword(),userAccount.getPhone(),userAccount.getEmail(),
                userAccount.getBirthday(), userAccount.getAddress(),userAccount.getStatus(),userAccount.getCreateTime(),userAccount.getId());
}

    @Test
    public void queryAllUsers() {
        List<UserAccount> users=new ArrayList<>();
        List<Record> recordList= Db.find(Db.getSql("user.fun001.02"));
        recordList.stream().forEach(
                record -> users.add(RecordUtils.getInstance().recordToObj(record, UserAccount.class)));
        System.out.println(users);
    }

    @Test
    public void queryById() {
       System.out.println(Db.findFirst(Db.getSql("user.fun001.03"),1L));
    }

    @Test
    public void deleteById() {

        System.out.println( Db.update(Db.getSql("user.fun003.01"),2L));

    }
}