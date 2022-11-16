package com.fql.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;

/**
 * @author Qian
 */
@Entity(name = "t_student")
@Data
@ApiModel(value = "学生",description = "学生模型")

public class StudentModel extends BaseModel {
    private String imgHref ;
}
