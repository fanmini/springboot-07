package com.fql.entity;

import lombok.Data;

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


}
