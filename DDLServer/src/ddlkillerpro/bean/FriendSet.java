package ddlkillerpro.bean;

/**
 * Created by MichelleZhang on 2017/4/15.
 */
public class FriendSet {
    private Integer rtpID;
    private Integer userID;
    private Integer friendID;

    public Integer getFriendID() {
        return friendID;
    }

    public void setFriendID(Integer friendID) {
        this.friendID = friendID;
    }

    public Integer getRtpID() {
        return rtpID;
    }

    public void setRtpID(Integer rtpID) {
        this.rtpID = rtpID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    //
    public FriendSet(Integer rtpID, Integer userID, Integer friendID){
        this.friendID = friendID;
        this.rtpID = rtpID;
        this.userID = userID;
    }

    //增加好友时
    public FriendSet(Integer userID, Integer friendID){
        this.userID = userID;
        this.friendID = friendID;
    }
}
