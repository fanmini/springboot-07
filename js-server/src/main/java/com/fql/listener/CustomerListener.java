package com.fql.listener;

import com.fql.common.Rediskey;
import com.fql.entity.CustomerModel;
import com.fql.entity.Mail;
import com.fql.mapper.CustomerMapper;
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
import java.util.ArrayList;
import java.util.List;


/**
 * @author Qian
 * 接受消费队列fql_test1信息
 */
@Slf4j
@Component
@Transactional
public class CustomerListener {
    @Autowired
    private MailUtil mailUtil ;
    @Resource
    private RedisUtil redis ;
    @Resource
    private CustomerMapper mapper;

    @RabbitListener(queues = {"queue_customer"})
    public void receive(Message message, Channel channel) throws Exception {
        Mail mail = SerializeUtil.jsonToObj(message,Mail.class);

        String mesId = mail.getMesId();
        // 缓存查询消息
        List<String> arr = redis.getCacheList(mesId);
        // 判断是否为空
        if(null == arr || arr.size() == 0) {
            log.info("顾客消息重复发送,以返回消息：{}",mail.getContent());
            return ;
        }
        List<String> newArray = new ArrayList<>();

        // 对一个或多个用户发送邮件
        for (int i = 0; i < arr.size(); i++) {
            CustomerModel data = mapper.findMegCountById(Integer.valueOf(arr.get(i)));
            if(data.getMsgCount()==0){
                log.info("顾客消息重复发送,跳过当前顾客:{}  ",data.getName());
                continue ;
            }
            // 设置邮箱
            mail.setTo(data.getEmail());
            // 获取当前队列的标签
            long tag = message.getMessageProperties().getDeliveryTag();
            // 发送消息
            if(mailUtil.send(mail)){
                // TODO: 当前功能只能一条消息发送，无法一个用户多条发送
                // 消息发送成功以后 进行消息确认数据库
                mapper.successMes(Integer.valueOf(arr.get(i)));
                // 2 手动确认
                channel.basicAck(tag,true);
            }else{
                // 3 发送失败
                channel.basicNack(tag,false,true);
                // 4 记录失败
                newArray.add(arr.get(i));
                // 5 修改数据投放消息次数
                mapper.setMsgCount(arr.get(i));
            }
        }
        // 如果有失败发送消息，进行数据存储等待定时处理
        if (newArray.size()<=0){
            mail.setCustomer(newArray);
            // 存储失败顾客信息以及消息
            redis.setCacheObject(Rediskey.CUSTOMER_EMAIL.getKey(),mail);
        }
        // 删除缓存
            redis.deleteObject(mail.getMesId());
    }
}
