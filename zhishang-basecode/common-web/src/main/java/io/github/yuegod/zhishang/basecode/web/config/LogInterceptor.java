package io.github.yuegod.zhishang.basecode.web.config;

import io.github.yuegod.zhishang.basecode.api.util.ThreadPoolUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.Executor;

/**
 * @author 屈子威
 * 请求日志拦截器，打印请求数据
 */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private static final Executor executor = ThreadPoolUtils.newFixedThreadPoolForDiscardPolicy(2);
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                logInfo(httpServletRequest,httpServletResponse,o);
            }
        });
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
    }

    public void logInfo(HttpServletRequest request, HttpServletResponse response, Object o) {
        String url = request.getRequestURI();
        String params = JSON.toJSONString(request.getParameterMap(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue);
        String method = request.getMethod();
        log.info("---------------------------------------------------------------------------------");
        log.info("Request Code " + url);
        log.info("Request Url " + url);
        log.info("Request Method " + method);
        log.info("Request Params " + params);
        log.info("Request Time " + new Date());
        log.info("---------------------------------------------------------------------------------");
    }
}
