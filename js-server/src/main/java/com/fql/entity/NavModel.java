package com.fql.entity;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "t_nav")
public class NavModel extends BaseModel {
    private String navTitle;
    private Integer type ;
}
