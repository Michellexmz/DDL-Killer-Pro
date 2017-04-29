package ddlkillerpro.bean;

/**
 * Created by MichelleZhang on 2017/4/12.
 */
public class UserSet {
    private String userName;
    private Integer userID;
    private String userPwd;
    private String userImg; //图片类型Bitmap?

    public String getUserName(){
        return userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserPwd(){
        return userPwd;
    }
    public void setUserPwd(String userPwd){
        this.userPwd = userPwd;
    }
    public String getUserImg(){
        return userImg;
    }
    public void setUserImg(String userImg){
        this.userImg = userImg;
    }
    public Integer getUserID(){
        return userID;
    }
    public void setUserID(Integer userID){
        this.userID = userID;
    }

    //修改用户属性时使用
    public UserSet(Integer userID, String userName, String userPwd, String userImg){
        this.userID = userID;
        this.userName = userName;
        this.userPwd = userPwd;
        this.userImg = userImg;
    }

    //添加用户时使用
    public UserSet(String userName, String userPwd, String userImg){
        this.userName = userName;
        this.userPwd = userPwd;
        this.userImg = userImg;
    }

    public UserSet(){
        super();
    }
}
