package knowmap.top.serviceCode;

public enum  UserAccountCode {
    OK(100, "业务正常"),

    UsernameInvalid(101, "用户名无效"),

    UsernameExists(102, "用户名已经存在"),

    UserSaveError(103, "用户保存失败"),

    ParamsInvalid(104, "参数校验失败"),

    PasswordError(106, "密码错误");


    private Integer code;

    private String message;

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    UserAccountCode(Integer code, String message) {
        this.code = code;
        this.message=message;
    }
}
