package com.example.michellezhang.ddlkillerpro.database;


import org.litepal.crud.DataSupport;

/**
 * Created by zdl on 2017/4/16.
 */

public class SetInfo extends DataSupport {
    private  int id;
    private int noticeTime;
    private int noticeFrequency;
    private int widgetFlag;
    private int widgetNum;
    private int widgetDays;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNoticeFrequency() {
        return noticeFrequency;
    }

    public int getNoticeTime() {
        return noticeTime;
    }

    public int getWidgetDays() {
        return widgetDays;
    }

    public int getWidgetFlag() {
        return widgetFlag;
    }

    public int getWidgetNum() {
        return widgetNum;
    }

    public void setNoticeFrequency(int noticeFrequency) {
        this.noticeFrequency = noticeFrequency;
    }

    public void setNoticeTime(int noticeTime) {
        this.noticeTime = noticeTime;
    }

    public void setWidgetDays(int widgetDays) {
        this.widgetDays = widgetDays;
    }

    public void setWidgetFlag(int widgetFlag) {
        this.widgetFlag = widgetFlag;
    }

    public void setWidgetNum(int widgetNum) {
        this.widgetNum = widgetNum;
    }
}
