package com.example.michellezhang.ddlkillerpro.database;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zdl on 2017/4/16.
 */

public class ItemInfo extends DataSupport implements Serializable{
    private int id;
    private int itemid;
    private String itemName;
    private int labelID;
    private Date itemNewTime;
    private Date itemComplected;
    private Date itemDeadline;
    private int showable;
    private int noticeFlag;
    private int groupFlag;
    private int userID;

    public ItemInfo(){
        super();
    }

    public ItemInfo(String itemName, Integer labelID, Date itemNewTime,
                   Date itemDeadline, Date itemComplected, Integer showable, Integer noticeFlag,
                   Integer groupFlag, Integer userID, Integer itemID) {
        this.id = itemID;
        this.itemName = itemName;
        this.labelID = labelID;
        this.itemNewTime = itemNewTime;
        this.itemDeadline = itemDeadline;
        this.showable = showable;
        this.noticeFlag = noticeFlag;
        this.groupFlag = groupFlag;
        this.userID = userID;
        this.itemComplected = itemComplected;
        this.itemid = itemID;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Date getItemComplected() {
        return itemComplected;
    }

    public Date getItemDeadline() {
        return itemDeadline;
    }

    public Date getItemNewTime() {
        return itemNewTime;
    }

    public Integer getGroupFlag() {
        return groupFlag;
    }


    public Integer getLabelID() {
        return labelID;
    }

    public Integer getNoticeFlag() {
        return noticeFlag;
    }

    public Integer getShowable() {
        return showable;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setGroupFlag(int groupFlag) {
        this.groupFlag = groupFlag;
    }

    public void setItemComplected(Date itemComplected) {
        this.itemComplected = itemComplected;
    }

    public void setItemDeadline(Date itemDeadline) {
        this.itemDeadline = itemDeadline;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemNewTime(Date itemNewTime) {
        this.itemNewTime = itemNewTime;
    }

    public void setLabelID(int labelID) {
        this.labelID = labelID;
    }

    public void setNoticeFlag(int noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public void setShowable(int showable) {
        this.showable = showable;
    }
}