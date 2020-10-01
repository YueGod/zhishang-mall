package io.github.yuegod.zhishang.basecode.web.config;

import io.github.yuegod.zhishang.basecode.web.model.Result;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 屈子威
 * @date 2020/8/30 3:13
 * @description Web返回格式序列化
 */
@Configuration
public class WebMvcAdapter implements WebMvcRegistrations {
    @Override
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return new RequestMappingHandlerAdapter() {
            /**
             * 重写，将自定义的注解解析放在第一位
             */
            @Override
            public void afterPropertiesSet() {
                super.afterPropertiesSet();
                // 获取旧的列表
                List<HandlerMethodReturnValueHandler> olds = this.getReturnValueHandlers();
                // 创建一个新的
                List<HandlerMethodReturnValueHandler> handlers = new ArrayList<>();
                // 将自定义的放在第一位
                handlers.add(0, createResponseStringMethodProcessor(getMessageConverters()));

                for (HandlerMethodReturnValueHandler handler : olds) {
                    if (handler instanceof RequestResponseBodyMethodProcessor)
                        // 替换原来的 RequestResponseBodyMethodProcessor
                        handlers.add(createRequestResponseBodyMethodProcessor(getMessageConverters()));
                    else
                        handlers.add(handler);
                }
                setReturnValueHandlers(handlers);
            }
        };
    }

    /**
     * 创建自定义ResponseBody Processor
     *
     * @param converters
     * @return
     */

    private RequestResponseBodyMethodProcessor createRequestResponseBodyMethodProcessor(List<HttpMessageConverter<?>> converters) {
        return new RequestResponseBodyMethodProcessor(converters) {
            @Override
            public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
                // 将请求成功的数据进行格式化处理
                Result result = new Result();
                result.setCode(Result.Code.SUCCESS.getValue());
                result.setMsg(null);
                result.setData(returnValue);
                super.handleReturnValue(result, returnType, mavContainer, webRequest);
            }
        };
    }

    /**
     * 创建ResponseString Processor
     *
     * @param converters
     * @return
     */
    private RequestResponseBodyMethodProcessor createResponseStringMethodProcessor(List<HttpMessageConverter<?>> converters) {
        return new RequestResponseBodyMethodProcessor(converters) {
            @Override
            public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws IOException, HttpMediaTypeNotAcceptableException, HttpMessageNotWritableException {
                super.handleReturnValue(returnValue, returnType, mavContainer, webRequest);
            }

            @Override
            public boolean supportsReturnType(MethodParameter returnType) {
                return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResponseString.class) || returnType.hasMethodAnnotation(ResponseString.class);
            }
        };
    }
}
