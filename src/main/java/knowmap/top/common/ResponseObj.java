package knowmap.top.common;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 响应客户端的包装类
 */

@Data
@Accessors(chain = true)
public class ResponseObj extends BaseEntity {
    // 服务器响应信息
    private String message;

    // 服务器响应码
    private Integer code;

    // 数据体
    private Object data;

    // 业务码
    private Integer serviceCode;

    // 业务码对应信息
    private String serviceMessage;
}
