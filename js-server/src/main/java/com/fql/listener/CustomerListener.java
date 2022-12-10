package com.fql.listener;

import com.fql.entity.Mail;
import com.fql.util.MailUtil;
import com.fql.util.RedisUtil;
import com.fql.util.SerializeUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * @author Qian
 * 消费队列信息
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class CustomerListener {
    @Autowired
    private MailUtil mailUtil;
    @Resource
    private RedisUtil redis;

    @RabbitListener(queues = {"queue_customer"})
    public void receive(Message message, Channel channel) throws Exception {
        Mail mail = SerializeUtil.jsonToObj(message, Mail.class);
        // 缓存查询消息
        assert mail != null;
        String mesId = mail.getMesId();
        Object email = redis.getCacheObject(mesId);
        // 判断是否为空
        if (null == email) {
            // 手动确认
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
            log.info("顾客消息重复发送,顾客邮箱：{}", mail.getTo());
            return;
        }

        // 获取当前队列的标签
        long tag = message.getMessageProperties().getDeliveryTag();
        // 发送消息
        if (mailUtil.send(mail)) {
            // 手动确认
            channel.basicAck(tag, true);
            // 删除缓存
            redis.deleteObject(mesId);
        } else {
            //  发送失败 重新回到队列
            channel.basicNack(tag, false, true);
        }
    }
}
