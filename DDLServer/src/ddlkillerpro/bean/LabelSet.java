package ddlkillerpro.bean;

/**
 * Created by MichelleZhang on 2017/4/12.
 */
public class LabelSet {
    private String labelName;
    private Integer labelID;
    private String labelColor;
    private Integer labelLogo;
    public String getLabelName(){
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Integer getLabelID() {
        return labelID;
    }

    public void setLabelID(Integer labelID) {
        this.labelID = labelID;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }

    public Integer getLabelLogo() {
        return labelLogo;
    }

    public void setLabelLogo(Integer labelLogo) {
        this.labelLogo = labelLogo;
    }

    //修改分类属性时使用
    public LabelSet(Integer labelID, String labelName, String labelColor, Integer labelLogo){
        this.labelColor = labelColor;
        this.labelID = labelID;
        this.labelLogo = labelLogo;
        this.labelName = labelName;
    }

    //创建新的分类时使用
    public LabelSet(String labelName, Integer labelLogo, String labelColor){
        this.labelColor = labelColor;
        this.labelLogo = labelLogo;
        this.labelName = labelName;
    }

    //删除分类时使用
    public LabelSet(Integer labelID){
        this.labelID = labelID;
    }

    public LabelSet(){
        super();
    }
}
