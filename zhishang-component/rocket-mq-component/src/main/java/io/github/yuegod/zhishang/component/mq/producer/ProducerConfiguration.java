package io.github.yuegod.zhishang.component.mq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author quziwei
 * @date 2020/10/19
 * @description
 **/
@Configuration
public class ProducerConfiguration {
    @Bean
    public MQProducer mqProducer(){
        return new DefaultMQProducer();
    }
}
