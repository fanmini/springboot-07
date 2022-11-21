package com.fql.service.imp;

import com.fql.common.Rediskey;
import com.fql.entity.CustomerModel;
import com.fql.entity.Mail;
import com.fql.entity.ResultModel;
import com.fql.mapper.CustomerMapper;
import com.fql.repository.jpa.CustomerRepository;
import com.fql.util.ProductionMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CustomerServiceImpl extends BaseServiceImpl<CustomerModel,Integer, CustomerRepository> {
    public CustomerServiceImpl(CustomerRepository repository) {
        super(repository, Rediskey.CUSTOMER_KEY.getKey());
    }
    @Resource
    private CustomerMapper mapper ;



    @Override
    public ResultModel findAllByLike(CustomerModel entity) {
        return ResultModel.getResultModel(mapper.findAll(entity));
    }

    @Resource
    private ProductionMessage pm ;

    // 消息推送
    public ResultModel messagePush(Mail mail){
        // 基本条件判断
        List<String> customer = mail.getCustomer();
        if (null==customer || customer.size()<1){
            return ResultModel.getResultModel("您没有选中要推荐的客户",null);
        }
        // uuid
        mail.setMesId(UUID.randomUUID().toString());
        // 消息计数存入数据库
        String msgCount = "";
        for (String s : customer) {
            msgCount+=(s+",");
        }
        mapper.setMsgCount(msgCount);
        // 缓存
        redisUtil.setCacheList(mail.getMesId(), mail.getCustomer());
        // 生产消息
        boolean mes = pm.createMes(mail);

        return ResultModel.getResultModel("邮箱消息创建成功",null);
    }

}
