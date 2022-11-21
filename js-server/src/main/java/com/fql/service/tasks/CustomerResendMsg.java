package com.fql.service.tasks;

import com.fql.common.Rediskey;
import com.fql.entity.CustomerModel;
import com.fql.entity.Mail;
import com.fql.mapper.CustomerMapper;
import com.fql.util.ProductionMessage;
import com.fql.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Qian
 */
@Component
@Slf4j
public class CustomerResendMsg {

    @Resource
    private RedisUtil redis ;

    @Resource
    private CustomerMapper mapper;

    @Resource
    private ProductionMessage pm ;
    /**
     * 最大投递消息
     */
    private final Integer MAX_MSG = 3 ;

    @Scheduled(cron = "0 5 * * * * ?")
    public void  resendMsg(){
        log.info("消息重新投递");
        Mail mail = redis.getCacheObject(Rediskey.CUSTOMER_EMAIL.getKey());
        List<String> list = mail.getCustomer();
        if(null == list ||list.size() <=0){
            log.info("当前队列暂无失败消息！！！！");
            return ;
        }
        // 查询要投递的用户
        StringBuffer ids = new StringBuffer("0");
        for (int i = 0; i < list.size(); i++) {
                ids.append(","+list.get(i));
        }
        List<CustomerModel> all = mapper.findAllMegCountById(ids.toString());
        ids = null ;
        list = null ;
        for (int i = 0; i < all.size(); i++) {
            CustomerModel model = all.get(i);
            if(model.getMsgCount()>MAX_MSG){
                log.info("超过最大次数投递");
                continue;
            }
            // 投递次数记录
            all.get(i).setMsgCount(model.getMsgCount()+1);
            ids.append(","+model.getId());
            list.add(model.getId()+"");
        }
        pm.createMes(mail);
        log.info("定时重新投送消息成功！！！！");
    }

}
