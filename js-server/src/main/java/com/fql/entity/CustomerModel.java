package com.fql.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Qian
 */
@Data
@Entity(name = "t_customer")
public class CustomerModel extends BaseModel {
    private String name ;
    private Long phone ;
    private String email ;
    private String content ;
    /**
     * 0代表消息没有了
     * 控制消息的幂等性
      */
    @Column(insertable = false ,columnDefinition = "int DEFAULT 0")
    private Integer msgCount ;


}
