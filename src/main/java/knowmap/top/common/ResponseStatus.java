package knowmap.top.common;

/**
 * 请求状态码，响应客户端
 */

public enum ResponseStatus {
    Ok(200),

    Error(500),

    TokenInvalid(501);

    private Integer code;

    ResponseStatus(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

}
