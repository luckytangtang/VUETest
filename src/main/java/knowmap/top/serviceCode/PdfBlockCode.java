package knowmap.top.serviceCode;

public enum  PdfBlockCode {
    OK(100, "业务正常"),

    BlockPermissionDenied(101, "没有权限");

    private Integer code;

    private String message;

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    PdfBlockCode(Integer code, String message) {
        this.code = code;
        this.message=message;
    }
}
