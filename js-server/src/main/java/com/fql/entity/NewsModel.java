package com.fql.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * ApiModel : swagger 注释文档
 * Entity ： jpa 对应数据库的表
 * Document: elasticsearch 的索引的实体类名
 * setting：
 */
@Data
@ApiModel(value = "新闻",description = "新闻模型")
@Entity(name = "t_news")

public class NewsModel extends BaseModel {
    private String imgHref;
    private String date;
    private String title ;

    @Column(columnDefinition="TEXT")
    private String content;
    private Integer navId ;
}
