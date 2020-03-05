package com.ly.messagemanage.Bean;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/
public class RabbitmqBean {

    @Scope("prototype")
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        /**
         * mandatory:交换器无法根据自身类型和路由键找到一个符合条件的队列时的处理方式
         *      true：RabbitMQ会调用Basic.Return命令将消息返回给生产者
         *      false：RabbitMQ会把消息直接丢弃
         */
        rabbitTemplate.setMandatory(true);
        /**
         * MessageConverter可以把java对象转换成Message对象，也可以把Message对象转换成java对象
         */
        rabbitTemplate.setMessageConverter(new SerializerMessageConverter());
        return rabbitTemplate;
    }
}
