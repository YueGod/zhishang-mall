package io.github.yuegod.zhishang.component.feign.configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import io.github.yuegod.zhishang.basecode.api.util.SpringUtil;
import io.github.yuegod.zhishang.component.feign.component.FeignBeanComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description 将返回的信息解码
 **/
@Slf4j
public class FeignResultDecoder implements Decoder {


    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        FeignBeanComponent feignBeanComponent = (FeignBeanComponent) SpringUtil.getBean("feignBeanComponent");
        if (response.body() == null) {
            throw new DecodeException(response.status(), "没有返回有效的数据", response.request());
        }
        String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
        //对结果进行转换
        JSONObject jb = JSON.parseObject(bodyStr);
        //如果返回错误，且为内部错误，则直接抛出异常
        if (Result.Code.SUCCESS.getValue().equals(jb.getString("code"))) {
            if (!StringUtils.isEmpty(jb.getString("msg"))) {
                throw new DecodeException(response.status(), "接口返回错误：" + jb.getString("msg"), response.request());
            }
        }
        jb.getJSONObject("data").remove("create");
        Class clazz = feignBeanComponent.feignBeanCache.get(type.getTypeName());
        return jb.getObject("data", clazz);
    }

}
