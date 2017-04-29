package com.example.michellezhang.ddlkillerpro.database.impl;

import com.example.michellezhang.ddlkillerpro.database.SetInfo;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by zdl on 2017/4/18.
 */

public class SetImpl {
    public void set(int id,int noticeTime,int noticeFrequency,int widgetFlag,int widgetNumber,int widgetDays){
        SetInfo setInfo =new SetInfo();
        setInfo.setNoticeTime(noticeTime);
        setInfo.setNoticeFrequency(noticeFrequency);
        setInfo.setWidgetFlag(widgetFlag);
        setInfo.setWidgetNum(widgetNumber);
        setInfo.setWidgetDays(widgetDays);
        setInfo.update(id);
    }

    public static List<SetInfo> getAllSet(int id){
        List<SetInfo> SetInfos =DataSupport.where("id=?","id").find(SetInfo.class);
        return SetInfos;
    }
    public static void setwidgetnumber(int id,int widgetnumber){
        SetInfo Setinfo=new SetInfo();
        Setinfo.setWidgetNum(widgetnumber);
        //Setinfo.setId(id);
        //Setinfo.save();
        Setinfo.update(id);
    }
    public static void setwidgetdays(int id,int widgetdays){
        SetInfo Setinfo=new SetInfo();
        Setinfo.setWidgetDays(widgetdays);
        //Setinfo.setId(id);
        Setinfo.update(id);
    }
    public static void setwidgetflag(int id,int widgetflag){
        SetInfo Setinfo=new SetInfo();
        Setinfo.setWidgetFlag(widgetflag);
        //Setinfo.setId(id);
        //Setinfo.save();
        Setinfo.update(id);
    }
    public static void setnoticetime(int id,int noticetime){
        SetInfo Setinfo=new SetInfo();
        Setinfo.setNoticeTime(noticetime);
        Setinfo.update(id);
    }
    public static void setnoticefrequency(int id,int noticefrequency){
        SetInfo Setinfo=new SetInfo();
        Setinfo.setNoticeFrequency(noticefrequency);
        Setinfo.update(id);
    }


}
