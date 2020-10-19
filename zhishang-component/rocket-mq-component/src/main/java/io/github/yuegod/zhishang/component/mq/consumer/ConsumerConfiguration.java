package io.github.yuegod.zhishang.component.mq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MQConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author quziwei
 * @date 2020/10/19
 * @description
 **/
@Configuration
public class ConsumerConfiguration {
    @Bean
    public MQConsumer mqConsumer(){
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr("");
        return consumer;
    }
}
