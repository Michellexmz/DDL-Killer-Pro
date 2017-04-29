package ddlkillerpro.bean;

import java.util.Date;
import java.sql.Timestamp;

/**
 * Created by MichelleZhang on 2017/4/12.
 */
public class ItemSet {
    private Integer itemID;
    private String itemName;
    private Integer labelID;
    private Integer userID;
    private Timestamp itemNewTime;
    private Date itemCompleted;
    private Date itemDeadline;
    private Integer showable;
    private Integer noticeFlag;
    private Integer groupFlag;

    public Integer getItemID(){
        return itemID;
    }

    public void setItemID(Integer itemID){
        this.itemID = itemID;
    }

    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getLabelID() {
        return labelID;
    }

    public void setLabelID(Integer labelID) {
        this.labelID = labelID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Date getItemCompleted() {
        return itemCompleted;
    }

    public void setItemCompleted(Date itemCompleted) {
        this.itemCompleted = itemCompleted;
    }

    public Date getItemDeadline() {
        return itemDeadline;
    }

    public void setItemDeadline(Date itemDeadline) {
        this.itemDeadline = itemDeadline;
    }

    public Timestamp getItemNewTime() {
        return itemNewTime;
    }

    public void setItemNewTime(Timestamp itemNewTime) {
        this.itemNewTime = itemNewTime;
    }

    public Integer isGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(Integer groupFlag) {
        this.groupFlag = groupFlag;
    }

    public Integer isNoticeFlag() {
        return noticeFlag;
    }

    public void setNoticeFlag(Integer noticeFlag) {
        this.noticeFlag = noticeFlag;
    }

    public Integer isShowable() {
        return showable;
    }

    public void setShowable(Integer showable) {
        this.showable = showable;
    }

    public ItemSet(Integer itemID, String itemName, Integer labelID, Timestamp itemNewTime,
                   Date itemDeadline, Date itemCompleted, Integer showable, Integer noticeFlag,
                   Integer groupFlag, Integer userID){
        this.itemID = itemID;
        this.itemName = itemName;
        this.labelID = labelID;
        this.itemNewTime = itemNewTime;
        this.itemDeadline = itemDeadline;
        this.itemCompleted = itemCompleted;
        this.showable = showable;
        this.noticeFlag = noticeFlag;
        this.groupFlag = groupFlag;
        this.userID = userID;
    }

    //添加事项时使用
    public ItemSet(String itemName, Integer labelID, Timestamp itemNewTime,
                   Date itemDeadline, Integer showable, Integer noticeFlag,
                   Integer groupFlag, Integer userID){
        this.itemName = itemName;
        this.labelID = labelID;
        this.itemNewTime = itemNewTime;
        this.itemDeadline = itemDeadline;
        this.showable = showable;
        this.noticeFlag = noticeFlag;
        this.groupFlag = groupFlag;
        this.userID = userID;
    }

    //在删除事项时使用
    public ItemSet(Integer itemID){
        this.itemID = itemID;
    }

    //设置事项完成时间时使用
    public ItemSet(Integer itemID, Date itemCompleted){
        this.itemID = itemID;
        this.itemCompleted = itemCompleted;
    }

    public ItemSet(){
        super();
    }
}
