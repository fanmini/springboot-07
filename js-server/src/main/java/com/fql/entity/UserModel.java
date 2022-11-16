package com.fql.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;

/**
 * @author Qian
 */
@Data
@ToString
@Entity(name = "t_user")
@Table(uniqueConstraints =@UniqueConstraint(columnNames = {"userName"}))
@ApiModel(value = "用户",description = "用户模型，用于登录")
public class UserModel extends BaseModel implements Serializable {
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String password;
    @ApiModelProperty(value = "用户电话")
    private Long phone;
    @ApiModelProperty(value = "用户邮箱")
    private String email;
    @ApiModelProperty(value = "用户爱好")
    private String hobby;
    @ApiModelProperty(value = "用户登录验证码")
    private String code;



}
