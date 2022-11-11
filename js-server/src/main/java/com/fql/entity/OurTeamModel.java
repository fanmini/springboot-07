package com.fql.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.Entity;

@Entity(name = "t_our_team")
@ApiModel(value = "教练",description = "教练模型")

public class OurTeamModel extends BaseModel {
    private String imgHref ;
    private String name  ;
    private String  position ;

    public OurTeamModel(String imgHref, String name, String position) {
        this.imgHref = imgHref;
        this.name = name;
        this.position = position;
    }

    public OurTeamModel() {
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
