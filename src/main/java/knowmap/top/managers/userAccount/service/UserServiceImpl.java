package knowmap.top.managers.userAccount.service;


import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.SqlPara;
import knowmap.top.common.BaseQuery;
import knowmap.top.managers.userAccount.entity.UserAccount;
import knowmap.top.utils.RecordUtils;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public String getSecret(Long userId) {
        return Db.queryStr(Db.getSql("user.fun001.05"), userId);
    }

    @Override
    public String getSecret(String username) {
        return Db.queryStr(Db.getSql("user.fun001.06"), username);
    }

    @Override
    public Integer updateSecret(String secret, Long userId) {
        return Db.update(Db.getSql("user.fun002.02"), secret, userId);
    }

    @Override
    public Record getBaseMessage(String name) {
        return Db.findFirst(Db.getSql("user.fun001.04"), name);
    }

    /**
     *
     * @param user
     * @return  用户id or -1 error
     */
    @Override
    public Long saveUser(UserAccount user) {
        Record record= RecordUtils.getInstance().objToRecord(user,UserAccount.class);
        return Db.save("user_account",record)?record.getLong("id"):-1L;
    }


    @Override
    public UserAccount queryByName(String name) {
        Record record=Db.findFirst(Db.getSql("user.fun001.01"),name);
        return  RecordUtils.getInstance().recordToObj(record,UserAccount.class);
    }

    @Override
    public int updateUser(UserAccount user) {
        Record record = RecordUtils.getInstance().objToRecord(user, UserAccount.class);
        SqlPara sqlPara = Db.getSqlPara("user.fun002.01", record);

        return Db.update(sqlPara);
    }

    @Override
    public List<UserAccount> queryAllUsers() {
        List<UserAccount> users=new ArrayList<>();
        List<Record> recordList=Db.find(Db.getSql("user.fun001.02"));
        recordList.stream().forEach(
                record -> users.add(RecordUtils.getInstance().recordToObj(record, UserAccount.class)));
        return users;
    }

    @Override
    public List<UserAccount> queryPaginate(BaseQuery baseQuery) {
        List<UserAccount> userAccounts=new ArrayList<>();
        Db.template(Db.getSql("user.fun001.02")).paginate(baseQuery.getPageIndex(),baseQuery.getPageSize())
                .getList().stream().forEach(
                        record -> userAccounts.add(RecordUtils.getInstance().recordToObj(record,UserAccount.class))
        );
        return userAccounts;
    }

    @Override
    public UserAccount queryById(Long id) {
        Record record=Db.findFirst(Db.getSql("user.fun001.03"),id);
        return RecordUtils.getInstance().recordToObj(record,UserAccount.class);
    }

    /**
     * 成功则返回的是1,将status状态至为1
     * @param id
     * @return
     */
    @Override
    public int deleteById(Long id) {

        return Db.update(Db.getSql("user.fun003.01"),id);
    }

}
