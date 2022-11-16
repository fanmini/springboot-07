package com.fql.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Data
@MappedSuperclass
public class BaseModel implements Serializable {
    // 主键注解
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键唯一id")

    private Integer id;

    @ApiModelProperty(value = "创建时间")
    @Column(updatable = false,insertable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Timestamp createTime;


    @ApiModelProperty(value = "修改时间")
    @Column(updatable = false,insertable = false,columnDefinition= "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")

    private Timestamp updateTime;

    @ApiModelProperty(value = "0：启动 1：停用")
    @Column(insertable = false ,columnDefinition = "int DEFAULT 0")

    private Integer enable;

    @Column(name ="pageNum")
    @ApiModelProperty(value = "当前页")

    private Integer page ;

    @Column(name ="pageSize")
    @ApiModelProperty(value = "每页多少条")

    private Integer limit ;


}
