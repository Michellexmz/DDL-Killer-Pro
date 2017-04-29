package com.example.michellezhang.ddlkillerpro.database;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by zdl on 2017/4/16.
 */

public class LabelInfo extends DataSupport implements Serializable {
    private int id;
    private int labelid;
    private String labelName;
    private String labelColor;
    private Integer labelLogo;

    public LabelInfo(String name, Integer logo){
        this.labelName = name;
        this.labelLogo = logo;
    }

    public LabelInfo(Integer labelid, String labelName, Integer labelLogo, String labelColor){
        this.labelid = labelid;
        this.labelName = labelName;
        this.labelLogo = labelLogo;
        this.labelColor = labelColor;
    }

    public int getLabelid() {
        return labelid;
    }

    public void setLabelid(int labelid) {
        this.labelid = labelid;
    }

    public LabelInfo(){
        super();
    }

    public String getLabelColor() {
        return labelColor;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }


    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getLabelLogo() {
        return labelLogo;
    }

    public void setLabelLogo(Integer labelLogo) {
        this.labelLogo = labelLogo;
    }
}
