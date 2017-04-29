package com.example.michellezhang.ddlkillerpro.database.impl;

import android.database.Cursor;

import com.example.michellezhang.ddlkillerpro.database.ItemInfo;
import com.example.michellezhang.ddlkillerpro.database.LabelInfo;

import org.litepal.crud.DataSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zdl on 2017/4/17.
 */

public class ItemImpl {
    //public
    public static void addItem(int itemid,Date itemComplected, String itemName, int labelID, Date itemNewTime, Date itemDeadline,
                               int showable, int noticeFlag, int groupFlag, int userID){
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setId(1);
        itemInfo.setItemComplected(itemComplected);
        itemInfo.setItemid(itemid);
        itemInfo.setItemName(itemName);
        itemInfo.setLabelID(labelID);
        itemInfo.setItemNewTime(itemNewTime);
        itemInfo.setItemDeadline(itemDeadline);
        itemInfo.setShowable(showable);
        itemInfo.setNoticeFlag(noticeFlag);
        itemInfo.setGroupFlag(groupFlag);
        itemInfo.setUserID(userID);
        itemInfo.save();
    }

    public static ItemInfo getItemInfo(Integer itemid){
        List<ItemInfo> itemInfo = DataSupport.where("itemid=?",itemid.toString()).find(ItemInfo.class);
        return itemInfo.get(0);
    }

    public HashMap<String, Object> getItemLogo(Integer itemid){
        List<ItemInfo> itemInfo = DataSupport.where("itemid=?",itemid.toString()).find(ItemInfo.class);
        Integer labelID = itemInfo.get(0).getLabelID();

        LabelImpl labelImpl = new LabelImpl();
        LabelInfo labelInfo = labelImpl.getLabel(labelID);
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("labelLogo", labelInfo.getLabelLogo());
        hashMap.put("labelName", labelInfo.getLabelName());
        return hashMap;
    }

    public static List<ItemInfo> getWidgetItem(int num) {
        List<ItemInfo> ItemInfos = DataSupport.order("itemDeadline desc").find(ItemInfo.class);
        List<ItemInfo> retItems = new ArrayList<ItemInfo>();
        for (ItemInfo item : ItemInfos){
            if (item.getItemComplected() == null){
                retItems.add(item);
                if (retItems.size() == num){
                    break;
                }
            }
        }
        return retItems;
    }

    public void setItem(Integer itemid,String itemName,int labelID,Date itemDeadline,int showable,int
            noticeFlag,int groupFlag) {
        ItemInfo itemInfo = new ItemInfo();
        itemInfo.setNoticeFlag(noticeFlag);
        itemInfo.setShowable(showable);
        itemInfo.setItemDeadline(itemDeadline);
        itemInfo.setItemName(itemName);
        itemInfo.setLabelID(labelID);
        itemInfo.setGroupFlag(groupFlag);
        itemInfo.updateAll("itemid=?", itemid.toString());
    }

    public void addItem(ItemInfo itemInfo){
        ItemInfo tmpItem = itemInfo;
        tmpItem.setId(1);
        tmpItem.save();
    }

    public void deleteItem(Integer itemid){
        ItemInfo itemInfo =new ItemInfo();
        itemInfo.deleteAll(ItemInfo.class,"itemid=?",itemid.toString());
    }

    public void itemCompleted(Integer itemid,Date itemcomplected){
        ItemInfo itemInfo =new ItemInfo();
        itemInfo.setItemComplected(itemcomplected);
        itemInfo.updateAll("itemid=?",itemid.toString());
    }
    public List<ItemInfo> getAllItem(Integer userID){
        List<ItemInfo> ItemInfos =DataSupport.where("userID=?",userID.toString()).find(ItemInfo.class);
        return ItemInfos;
    }

    public List<ItemInfo> getLabelAllItem(Integer labelID){
        List<ItemInfo> ItemInfos =DataSupport.where("labelID=?",labelID.toString()).find(ItemInfo.class);
        return ItemInfos;
    }

    public static List<ItemInfo> getAllItem(Date itemDeadline){

        SimpleDateFormat formatter = new SimpleDateFormat();
        //Date nextday=itemDeadline;
        //nextday.setDate(itemDeadline.getDay()+1);
      //  CursorableLinkedList ItemInfos= (CursorableLinkedList) DataSupport.findBySQL("select * from ItemInfo where itemDeadline > ? and itemDeadline < ?",formatter.format(itemDeadline),formatter.format(nextday) );
      //  String str = "select * from iteminfo where itemDeadline>? and itemDeadline < ?";
        //List<ItemInfo> ItemInfos =DataSupport.where("itemDeadline>? and itemDeadline<?",
        //        formatter.format(itemDeadline),
         //       formatter.format(nextday)).find(ItemInfo.class);
       // System.out.println();
        List<ItemInfo> ItemInfos =DataSupport.where("itemDeadline=?",
                formatter.format(itemDeadline)).find(ItemInfo.class);
        return ItemInfos;
    }

    public static List<ItemInfo> getItemByDate(Date itemDeadline) {
        List<ItemInfo> ItemInfos = null ;
        Date nextDay = new Date(itemDeadline.getTime() + (long)24 * 60 * 60 * 1000);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String today, strNextDay;
        today=sDateFormat.format(itemDeadline);
        strNextDay=sDateFormat.format(nextDay);

        Cursor c = DataSupport.findBySQL("select * from ItemInfo where itemDeadline > ? and itemDeadline < ?", today, strNextDay);

        int i=0;
        while (c.moveToNext()) {
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setItemName(c.getString(c.getColumnIndex("itemName")));
            System.out.println("Column" + c.getColumnCount());
            Date itemDDL = new Date();
            try {
                itemDDL = sDateFormat.parse((c.getString(c.getColumnIndex("itemDeadline"))));
            } catch (ParseException e){
                e.printStackTrace();
            }
            itemInfo.setItemDeadline(itemDDL);
            ItemInfos.add(itemInfo);
        }

        return ItemInfos;
    }

}
