package com.fql.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Data
@Entity(name = "t_product")
@ApiModel(value = "产品",description = "产品模型")

public class ProductModel extends BaseModel {
    private String imgHref;
    private String name;
    private BigDecimal price ;
    private Integer navId;


}
