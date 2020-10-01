package io.github.yuegod.zhishang.basecode.web.config;


import io.github.yuegod.zhishang.basecode.api.base.BaseException;
import io.github.yuegod.zhishang.basecode.web.model.Result;
import io.github.yuegod.zhishang.basecode.web.util.ResultUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 屈子威
 * 全局异常处理。
 */
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class BaseExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public Result baseException(BaseException e) {
        return ResultUtils.result(Result.Code.FAIL.getValue(), e.getMessage());
    }
}
