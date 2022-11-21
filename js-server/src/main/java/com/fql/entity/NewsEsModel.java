package com.fql.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

/**
 *
 * ApiModel : swagger 注释文档
 * Entity ： jpa 对应数据库的表
 * Document: elasticsearch 的索引的实体类名
 * setting：
 * @author Qian
 */
@Data

@Document(indexName = "news")
@ToString
@Setting
public class NewsEsModel {
    @Id
    private Integer id ;
    @Field(type = FieldType.Text,name = "img_href")
    private String imgHref;
    @Field(type = FieldType.Text,name = "date")
    private String date;
    @Field(type = FieldType.Text,name = "title")
    private String title ;
    @Field(type = FieldType.Text,name = "content")
    private String content;
    @Field(type = FieldType.Integer,name = "nav_id")
    private Integer navId ;
}
