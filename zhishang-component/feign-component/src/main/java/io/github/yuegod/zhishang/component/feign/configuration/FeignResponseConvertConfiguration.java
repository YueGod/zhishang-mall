package io.github.yuegod.zhishang.component.feign.configuration;

import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;

/**
 * @author quziwei
 * @date 2020/10/01
 * @description 响应数据格式转换
 **/
public class FeignResponseConvertConfiguration {

    @Bean
    public Decoder decoder() {
        return new FeignResultDecoder();
    }
}
