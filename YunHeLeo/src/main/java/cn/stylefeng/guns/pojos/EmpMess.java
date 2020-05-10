package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class EmpMess {

    /**
     * 消息标题
     */
    @TableField("t_message_title")
    private String tMessageTitle;

    /**
     * 消息内容
     */
    @TableField("t_message_content")
    private String tMessageContent;

    /**
     * 修改人
     */
    @TableField("t_update_man")
    private String tUpdateMan;

    /**
     * 修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    /**
     * 推送日期
     */
    @TableField("t_send_date")
    private Date tSendDate;

    /**
     * 是否推送:1是2否
     */
    @TableField("t_status")
    private Integer tStatus;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String tUsername;

    /**
     * 真实姓名
     */
    @TableField("t_realyname")
    private String tRealyname;

    /**
     * 手机号
     */
    @TableField("t_mobile")
    private String tMobile;

    /**
     * 密码
     */
    @TableField("t_password")
    private String tPassword;

    /**
     * 是否教师
     */
    @TableField("t_is_teacher")
    private boolean tIsTeacher;

    /**
     * 是否客服
     */
    @TableField("t_is_service")
    private boolean tIsService;

    /**
     * 创建日期
     */
    @TableField("t_createdate")
    private Date tCreatedate;

    /**
     * 修改日期
     */
    @TableField("t_updatedate")
    private Date tUpdatedate;

    /**
     * 角色ID
     */
    @TableField("role_id")
    private String roleId;

    @TableField("t_img_url")
    private String tImgUrl;

    public String gettMessageTitle() {
        return tMessageTitle;
    }

    public void settMessageTitle(String tMessageTitle) {
        this.tMessageTitle = tMessageTitle;
    }

    public String gettMessageContent() {
        return tMessageContent;
    }

    public void settMessageContent(String tMessageContent) {
        this.tMessageContent = tMessageContent;
    }

    public String gettUpdateMan() {
        return tUpdateMan;
    }

    public void settUpdateMan(String tUpdateMan) {
        this.tUpdateMan = tUpdateMan;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public Date gettSendDate() {
        return tSendDate;
    }

    public void settSendDate(Date tSendDate) {
        this.tSendDate = tSendDate;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettRealyname() {
        return tRealyname;
    }

    public void settRealyname(String tRealyname) {
        this.tRealyname = tRealyname;
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }

    public boolean istIsTeacher() {
        return tIsTeacher;
    }

    public void settIsTeacher(boolean tIsTeacher) {
        this.tIsTeacher = tIsTeacher;
    }

    public boolean istIsService() {
        return tIsService;
    }

    public void settIsService(boolean tIsService) {
        this.tIsService = tIsService;
    }

    public Date gettCreatedate() {
        return tCreatedate;
    }

    public void settCreatedate(Date tCreatedate) {
        this.tCreatedate = tCreatedate;
    }

    public Date gettUpdatedate() {
        return tUpdatedate;
    }

    public void settUpdatedate(Date tUpdatedate) {
        this.tUpdatedate = tUpdatedate;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }

    @Override
    public String toString() {
        return "EmpMess{" +
                "tMessageTitle='" + tMessageTitle + '\'' +
                ", tMessageContent='" + tMessageContent + '\'' +
                ", tUpdateMan='" + tUpdateMan + '\'' +
                ", tUpdateDate=" + tUpdateDate +
                ", tSendDate=" + tSendDate +
                ", tStatus=" + tStatus +
                ", id='" + id + '\'' +
                ", tUsername='" + tUsername + '\'' +
                ", tRealyname='" + tRealyname + '\'' +
                ", tMobile='" + tMobile + '\'' +
                ", tPassword='" + tPassword + '\'' +
                ", tIsTeacher=" + tIsTeacher +
                ", tIsService=" + tIsService +
                ", tCreatedate=" + tCreatedate +
                ", tUpdatedate=" + tUpdatedate +
                ", roleId='" + roleId + '\'' +
                ", tImgUrl='" + tImgUrl + '\'' +
                '}';
    }
}
