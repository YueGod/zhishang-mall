package io.github.yuegod.zhishang.basecode.web.util;


import io.github.yuegod.zhishang.basecode.web.model.Result;

/**
 * @author ：quziwei
 * @date ：Created in 2020/6/1 6:17 下午
 * @description：返回值工具类
 */
public class ResultUtils {
    public static Result result() {
        Result result = new Result();
        result.setCode(Result.Code.SUCCESS.getValue());
        return result;
    }

    public static Result result(Object data) {
        return result(data, null);
    }

    public static Result result(Object data, String msg) {
        Result result = new Result();
        result.setCode(Result.Code.SUCCESS.getValue());
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result result(String code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Result result(String msg) {
        return result(Result.Code.FAIL.getValue(), msg);
    }

    public static Result result(String code, String msg) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
