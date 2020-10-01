package io.github.yuegod.zhishang.basecode.web.config;


import io.github.yuegod.zhishang.basecode.web.model.Result;
import io.github.yuegod.zhishang.basecode.web.util.ResultUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author 屈子威
 * 全局异常处理。
 */
@ControllerAdvice(annotations = RestController.class)
@ResponseBody
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus
    public Result exception(Exception e) {
        return ResultUtils.result(Result.Code.ERROR.getValue(), e.getMessage());
    }
}
