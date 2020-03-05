package com.ly.messagemanage.Handler.Mail;

import com.ly.messagemanage.Entity.MailInfo;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @USER: lynn
 * @DATE: 2020/3/5
 **/

@Component
@RabbitListener(bindings = @QueueBinding(value =
    @Queue(value = "${messagemanage.fanout.mail.queue}", autoDelete = "true"),
        exchange = @Exchange(value = "${messagemanage.fanout.exchange}",
        type = ExchangeTypes.FANOUT),
        key = ""))
public class MailConsumer {

    /**
     *
     * @param channel
     * @param tag 当一个消费者向 RabbitMQ 注册后，会建立起一个 Channel ，
     *                  RabbitMQ 会用 basic.deliver 方法向消费者推送消息，这个方法携带了一个 delivery tag，
     *                  它代表了 RabbitMQ 向该 Channel 投递的这条消息的唯一标识 ID，是一个单调递增的正整数，
     *                  delivery tag 的范围仅限于 Channel
     *       (包含在Headers中)
     * @param mailInfo
     * @param map 用于获取头部信息
     *            map.get("error") 获取头部包含error的信息
     * @throws IOException
     */
    @RabbitHandler
    public void forwordMail(Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag, MailInfo mailInfo, @Headers Map<String,Object> map ) throws IOException {

        if (map.get("error")!=null){
            System.out.println("消息错误");
            //deliveryTag:该消息的index
            //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
            //requeue：被拒绝的是否重新入队列
            channel.basicNack((Long)map.get(AmqpHeaders.DELIVERY_TAG),false,true);
        }

        //  为了减少网络流量，手动确认可以被批处理，当该参数为 true 时，则可以一次性确认 delivery_tag 小于等于传入值的所有消息
        // 类似于Kafka中的offset
        channel.basicAck(tag,false);
        System.out.println("MailConsumer:"+mailInfo.toString());
    }
}
