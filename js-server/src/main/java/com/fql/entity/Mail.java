package com.fql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Qian
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mail implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 消息id
     */
    private String MesId;

    /**
     * 消息标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 接收者的邮箱号
     */
    private String to ;
    /**
     * 邮箱列表
     */
    private List<String> customer ;

}