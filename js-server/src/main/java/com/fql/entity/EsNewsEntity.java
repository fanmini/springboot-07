package com.fql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.io.Serializable;

/**
 * @author Qian
 */
@Data
@Document(indexName = "news")
@Setting

public class EsNewsEntity implements Serializable {
    @Id
    private Integer index ;
    @Field(type = FieldType.Text,name = "img_href")
    private String imgHref ;
    @Field(type = FieldType.Text,name = "date")
    private String date ;
    @Field(type = FieldType.Text,name = "title")
    private String title ;
    @Field(analyzer = "ik_max_word" ,type = FieldType.Text,name = "content")
    private String content ;
    @Field(type = FieldType.Integer,name = "nav_id")
    private Integer navId ;
    @Field(type = FieldType.Integer,name = "enable")
    private Integer enable ;

    public EsNewsEntity(Integer index) {
        this.index = index;
    }

    public EsNewsEntity() {
    }

    @Override
    public String toString() {
        return "EsNewsEntity{" +
                "index=" + index +
                ", imgHref='" + imgHref + '\'' +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", navId=" + navId +
                ", enable=" + enable +
                '}';
    }
}
