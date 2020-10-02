package io.github.yuegod.zhishang.component.feign.configuration;

/**
 * @author ：quziwei
 * @date ：Created in 2020/6/1 6:18 下午
 * @description：返回值Model
 */
public class Result<T> {
    private String code = Code.SUCCESS.getValue();
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public enum Code {

        SUCCESS("000"), FAIL("999"), NO_LOGIN("100"), ERROR("500");

        private String value;

        public String getValue() {
            return value;
        }

        private Code(String value) {
            this.value = value;
        }
    }
}
