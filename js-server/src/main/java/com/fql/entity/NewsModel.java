package com.fql.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity(name = "t_news")
@ApiModel(value = "新闻",description = "新闻模型")

public class NewsModel extends BaseModel {
    private String imgHref;
    private String date;
    private String title ;
    private String content;
    private Integer navId ;
}
