package knowmap.top.managers.userAccount.entity;

import com.jfinal.plugin.activerecord.Model;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;

@Data
@Accessors(chain = true)
public class UserAccount {
    private Long id;

    private String username;

    private String password;

    private Integer gender;

    // 个人秘钥
    private String secret;

    private String phone;

    private Timestamp birthday;

    private Integer status;

    private String address;

    private String email;

    // 加密盐
    private String salt;

    private Timestamp createTime;
}
