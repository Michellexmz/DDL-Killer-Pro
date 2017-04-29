package com.example.michellezhang.ddlkillerpro.database.impl;


import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.LabelInfo;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zdl on 2017/4/18.
 */

public class LabelImpl {
    public void addLabel(int labelid,String labelName,String labelColor,int labelLogo ){
        LabelInfo labelInfo=new LabelInfo();
        labelInfo.setId(labelid);
        labelInfo.setLabelName(labelName);
        labelInfo.setLabelColor(labelColor);
        labelInfo.setLabelLogo(labelLogo);
        labelInfo.setId(1);
        labelInfo.save();
    }

    public void addLabel(LabelInfo labelInfo){
        LabelInfo tmpLabel = labelInfo;
        tmpLabel.setId(1);
        tmpLabel.save();
    }

    public LabelInfo getLabel(Integer labelid){
        List<LabelInfo> labelInfo = DataSupport.where("labelid=?", labelid.toString()).find(LabelInfo.class);
        return labelInfo.get(0);
    }

    public Integer getLabelID(Integer labelLogo){
        List<LabelInfo> labelInfos = DataSupport.where("labelLogo=?",labelLogo.toString()).find(LabelInfo.class);
        return labelInfos.get(0).getLabelid();
    }

    public static void setLabel(Integer labelid, String labelName,int labelLogo){
        LabelInfo labelInfo=new LabelInfo();
        labelInfo.setLabelName(labelName);
        //labelInfo.setLabelColor(labelColor);
        labelInfo.setLabelLogo(labelLogo);
        labelInfo.updateAll("labelid = ?", labelid.toString());
    }

    public void deleteLabel(Integer labelid){
        DataSupport.deleteAll(LabelInfo.class,"labelid=?",labelid.toString());
        DataSupport.deleteAll(ItemInfo.class, "labelid=?",labelid.toString());
    }

    public static List<LabelInfo> getAllLabel(){
        List<LabelInfo> labelInfos=DataSupport.findAll(LabelInfo.class);
        return labelInfos;
    }
}
