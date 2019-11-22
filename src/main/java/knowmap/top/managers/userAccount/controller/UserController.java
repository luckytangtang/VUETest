package knowmap.top.managers.userAccount.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.log.Log;
import com.jfinal.plugin.activerecord.Record;
import knowmap.top.common.ResponseObj;
import knowmap.top.common.ResponseStatus;
import knowmap.top.config.Interceptors.TokenInterceptor;
import knowmap.top.config.Interceptors.UserRegisterInterceptor;
import knowmap.top.managers.userAccount.entity.UserAccount;
import knowmap.top.managers.userAccount.service.UserService;
import knowmap.top.managers.userAccount.service.UserServiceImpl;
import knowmap.top.serviceCode.UserAccountCode;
import knowmap.top.utils.HashUtils;
import knowmap.top.utils.JwtUtils;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class UserController extends Controller {
    static Log log = Log.getLog(UserController.class);
    @Inject(UserServiceImpl.class)
    UserService userService;

    @Clear(TokenInterceptor.class)
    @Before(UserRegisterInterceptor.class)
    public void resgister() {
        ResponseObj responseObj = new ResponseObj();
        try {
            UserAccount userAccount = new UserAccount();
            userAccount.setUsername(get("username")).setPassword(get("password"));
            if (getLong("birthday") != null) {
                userAccount.setBirthday(new Timestamp(getLong("birthday")));
            }

            if (getInt("gender") != null) {
                userAccount.setGender(getInt("gender"));
            }

            if (get("phone") != null) {
                userAccount.setPhone(get("phone"));
            }

            if (get("address") != null) {
                userAccount.setAddress(get("address"));
            }

            if (get("email") !=  null) {
                userAccount.setEmail(get("email"));
            }


            log.info("UserController#resgister: " + userAccount.toString());

            userAccount.setSalt(JwtUtils.createSalt()).setPassword(
                    HashUtils.hash(userAccount.getPassword(), userAccount.getSalt())
            ).setCreateTime(new Timestamp(System.currentTimeMillis()));

            Long id;
            Map<String, Long> map = new HashMap<>();
            if (userService.queryByName(userAccount.getUsername()) == null) {
                id = userService.saveUser(userAccount);
                map.put("id", id);
                responseObj.setServiceCode(UserAccountCode.OK.getCode()).
                        setServiceMessage(UserAccountCode.OK.getMessage());
            } else {
                map.put("id", -1L);
                responseObj.setServiceMessage(UserAccountCode.UserSaveError.getMessage()).
                        setServiceCode(UserAccountCode.UserSaveError.getCode());
            }

            responseObj.setCode(ResponseStatus.Ok.getCode()).setData(map);
        } catch (Exception ex) {
            log.error("UserController#register", ex);
            responseObj.setCode(ResponseStatus.Error.getCode()).setMessage("服务器异常");
        }


        renderJson(responseObj);
    }

    @Clear(TokenInterceptor.class)
    public void findUserByName() {
        ResponseObj responseObj = new ResponseObj();

        try {
            String username = get("username");
            UserAccount userAccount = userService.queryByName(username);
            Map<String, Integer> map = new HashMap<>();
            if (userAccount == null) {
                map.put("result", 0);
            } else {
                map.put("result", 1);
            }

            responseObj.setData(map).
                    setCode(ResponseStatus.Ok.getCode()).
                    setServiceCode(UserAccountCode.OK.getCode()).
                    setServiceMessage(UserAccountCode.OK.getMessage());

        } catch (Exception ex) {
            log.error("UserController#findUserByName: ", ex);
            responseObj.setCode(ResponseStatus.Error.getCode()).setMessage("服务器异常");
        }

        renderJson(responseObj);
    }

    public void updateUserAccount() {
        UserAccount userAccount = new UserAccount();
        ResponseObj responseObj = new ResponseObj();

        try {
            String username = get("username");
            if (username != null && userService.queryByName(username) != null) {
                if (getLong("birthday") != null) {
                    userAccount.setBirthday(new Timestamp(getLong("birthday")));
                }

                if (get("address") != null) {
                    userAccount.setAddress(get("address"));
                }

                if (getInt("gender") != null) {
                    userAccount.setGender(getInt("gender"));
                }
                userService.updateUser(userAccount);
                responseObj.setServiceMessage(UserAccountCode.OK.getMessage()).setServiceCode(UserAccountCode.OK.getCode());
            } else {
                responseObj.setServiceCode(UserAccountCode.UsernameInvalid.
                        getCode()).setServiceMessage(UserAccountCode.UsernameInvalid.getMessage());
            }

            responseObj.setCode(ResponseStatus.Ok.getCode());
        } catch (Exception ex) {
            log.error("UserController#updateUserAccount: ", ex);
            responseObj.setCode(ResponseStatus.Error.getCode()).setMessage("服务器异常");
        }

        renderJson(responseObj);
    }

    public void getUserBaseMessage() {
        ResponseObj responseObj = new ResponseObj();

        try {
            String username = get("username");
            Record record = userService.getBaseMessage(username);
            if (record != null) {
                responseObj.setServiceCode(UserAccountCode.OK.getCode()).
                        setServiceMessage(UserAccountCode.OK.getMessage()).setData(record);
            } else {
                responseObj.setServiceCode(UserAccountCode.UsernameInvalid.getCode()).
                        setServiceMessage(UserAccountCode.UsernameInvalid.getMessage());
            }

            responseObj.setCode(ResponseStatus.Ok.getCode());
        } catch (Exception ex) {
            log.error("UserController#getUserByName: ", ex);
            responseObj.setCode(ResponseStatus.Error.getCode()).setMessage("服务器异常");
        }

        renderJson(responseObj);
    }

}
