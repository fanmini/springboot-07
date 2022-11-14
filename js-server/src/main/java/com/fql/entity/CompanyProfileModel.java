package com.fql.entity;


import lombok.Data;

import javax.persistence.Entity;

/**
 * @author Qian
 */ /*
* 公司基本信息
* 1 简介
* 2 历史：时间 简介
* */
@Data
@Entity(name = "t_company_profile")
public class CompanyProfileModel extends BaseModel {
    private String imgHref;
    private String date ;
    private String content ;
}
