package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

public class EmpImg {

    /**
     * 主键
     */
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
     * 状态 1正常 2禁用
     */
    @TableField("t_status")
    private Integer tStatus;

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


    /**
     * 轮播图类型
     */
    @TableField("t_img_type")
    private String tImgType;

    /**
     * 名称
     */
    @TableField("t_name")
    private String tName;

    /**
     * 排序号
     */
    @TableField("t_order_no")
    private String tOrderNo;

    /**
     * 链接类型
     */
    @TableField("t_img_url")
    private String tImgUrl;

    /**
     * 关联id
     */
    @TableField("t_link_id")
    private String linkId;

    /**
     * 链接类型
     */
    @TableField("t_link_type")
    private String tLinkType;

    /**
     * 上传人
     */
    @TableField("t_upload_man")
    private String tUploadMan;

    /**
     * 上传日期
     */
    @TableField("t_upload_date")
    private Date tUploadDate;

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
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

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
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

    public String gettImgType() {
        return tImgType;
    }

    public void settImgType(String tImgType) {
        this.tImgType = tImgType;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettOrderNo() {
        return tOrderNo;
    }

    public void settOrderNo(String tOrderNo) {
        this.tOrderNo = tOrderNo;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }

    public String gettLinkType() {
        return tLinkType;
    }

    public void settLinkType(String tLinkType) {
        this.tLinkType = tLinkType;
    }

    public String gettUploadMan() {
        return tUploadMan;
    }

    public void settUploadMan(String tUploadMan) {
        this.tUploadMan = tUploadMan;
    }

    public Date gettUploadDate() {
        return tUploadDate;
    }

    public void settUploadDate(Date tUploadDate) {
        this.tUploadDate = tUploadDate;
    }
}
