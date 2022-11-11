package com.fql.entity;


import lombok.Data;
import lombok.ToString;
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

@ToString
@Document(indexName = "bank")
@Setting
public class BankEsEntity implements Serializable {

    @Id
    private Integer id ;

    @Field(type = FieldType.Long,name = "account_number")
    private Long accountNumber ;

    @Field(type = FieldType.Text,name = "address")
    private String address ;

    @Field(type = FieldType.Long,name = "age")
    private Long age ;

    @Field(type = FieldType.Long,name = "balance")
    private Long balance ;

    @Field(type = FieldType.Text,name = "city")
    private String city ;

    @Field(analyzer = "ik_max_word",type = FieldType.Text,name = "email")
    private String email ;

    @Field(type = FieldType.Text,name = "employer")
    private  String employer ;

    @Field(type = FieldType.Text,name = "firstname")
    private String firstname ;

    @Field(type = FieldType.Text,name = "lastname")
    private String lastname ;

    @Field(type = FieldType.Text,name = "gender")
    private String gender ;

    @Field(type =FieldType.Text,name = "state")
    private String state ;




}
