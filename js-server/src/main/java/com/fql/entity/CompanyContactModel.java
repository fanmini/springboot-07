package com.fql.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author:qian
 * @version
 *
 */

@Data
@Entity(name = "t_company_contact")
public class CompanyContactModel extends BaseModel {
    private  String imgHref ;
    private String text;

    public CompanyContactModel(String imgHref, String text) {
        this.imgHref = imgHref;
        this.text = text;
    }

    public CompanyContactModel() {
    }

    public String getImgHref() {
        return imgHref;
    }

    public void setImgHref(String imgHref) {
        this.imgHref = imgHref;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
