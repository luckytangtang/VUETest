package knowmap.top.serviceCode;

public enum DocumentCode {
    OK(100, "业务正常");


    private Integer code;

    private String message;

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    DocumentCode(Integer code, String message) {
        this.code = code;
        this.message=message;
    }
}
