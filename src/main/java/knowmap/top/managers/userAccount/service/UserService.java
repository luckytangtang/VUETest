package knowmap.top.managers.userAccount.service;

import com.jfinal.plugin.activerecord.Record;
import knowmap.top.common.BaseQuery;
import knowmap.top.managers.userAccount.entity.UserAccount;

import java.util.List;

public interface UserService {
    String getSecret(Long userId);

    String getSecret(String username);


    Integer updateSecret(String secret, Long userId);

    Long saveUser(UserAccount user);

    UserAccount queryByName(String name);

    Record getBaseMessage(String name);

    int  updateUser(UserAccount user);

    List<UserAccount> queryAllUsers();

    List<UserAccount> queryPaginate(BaseQuery baseQuery);

    UserAccount queryById(Long id);

    int deleteById(Long id);
}
