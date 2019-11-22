package knowmap.top.common;


/**
 * Task的状态码
 */

public enum TaskStatus {
    JsonUndo(0, "JsonUndo"),
    JsonDoing(1, "JsonDoing"),
    Error(4, "Error"),
    ContentUndo(2, "ContentUndo"),
    ContentDoing(5, "ContentDoing"),
    ContentDone(6, "ContentDone"),
    // 该文档已经被解析
    OthersHadDone(7, "OthersHadDone");

    private Integer value;

    private String message;


    TaskStatus(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Integer getValue() {
        return value;
    }
}
