package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

public class UserDetails {

    private Integer id;

    private String rowGuid;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String tUsername;

    private String tAddressUsername;

    /**
     * 手机号
     */
    @TableField("t_mobile")
    private String tMobile;

    /**
     * 性别
     */
    @TableField("t_sex")
    private String tSex;

    /**
     * 注册日期
     */
    @TableField("t_register_date")
    private Date tRegisterDate;

    /**
     * 注册IP
     */
    @TableField("t_register_ip")
    private String tRegisterIp;

    /**
     * 孩子生日
     */
    @TableField("t_children_date")
    private Date tChildrenDate;

    /**
     * 学号
     */
    @TableField("t_student_no")
    private String tStudentNo;

    /**
     * 登录日期
     */
    @TableField("t_login_date")
    private Date tLoginDate;

    /**
     * 登录IP
     */
    @TableField("t_login_ip")
    private String tLoginIp;

    private String tLevelName;

    private String wechatName;

    private String qqNo;

    private String tLabelName;

    private String tHeadImg;

    private String tAddress;

    private String CityName;

    private String CityQuName;

    private String StateName;

    private String tCourseName;

    private String tClassName;

    private String StageName;

    private String PackageName;

    private Date tDueDate;

    private Date tActivateDate;

    private Integer tStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettAddressUsername() {
        return tAddressUsername;
    }

    public void settAddressUsername(String tAddressUsername) {
        this.tAddressUsername = tAddressUsername;
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile;
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    public Date gettRegisterDate() {
        return tRegisterDate;
    }

    public void settRegisterDate(Date tRegisterDate) {
        this.tRegisterDate = tRegisterDate;
    }

    public String gettRegisterIp() {
        return tRegisterIp;
    }

    public void settRegisterIp(String tRegisterIp) {
        this.tRegisterIp = tRegisterIp;
    }

    public Date gettChildrenDate() {
        return tChildrenDate;
    }

    public void settChildrenDate(Date tChildrenDate) {
        this.tChildrenDate = tChildrenDate;
    }

    public String gettStudentNo() {
        return tStudentNo;
    }

    public void settStudentNo(String tStudentNo) {
        this.tStudentNo = tStudentNo;
    }

    public Date gettLoginDate() {
        return tLoginDate;
    }

    public void settLoginDate(Date tLoginDate) {
        this.tLoginDate = tLoginDate;
    }

    public String gettLoginIp() {
        return tLoginIp;
    }

    public void settLoginIp(String tLoginIp) {
        this.tLoginIp = tLoginIp;
    }

    public String gettLevelName() {
        return tLevelName;
    }

    public void settLevelName(String tLevelName) {
        this.tLevelName = tLevelName;
    }

    public String getWechatName() {
        return wechatName;
    }

    public void setWechatName(String wechatName) {
        this.wechatName = wechatName;
    }

    public String getQqNo() {
        return qqNo;
    }

    public void setQqNo(String qqNo) {
        this.qqNo = qqNo;
    }

    public String gettLabelName() {
        return tLabelName;
    }

    public void settLabelName(String tLabelName) {
        this.tLabelName = tLabelName;
    }

    public String gettHeadImg() {
        return tHeadImg;
    }

    public void settHeadImg(String tHeadImg) {
        this.tHeadImg = tHeadImg;
    }

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getCityQuName() {
        return CityQuName;
    }

    public void setCityQuName(String cityQuName) {
        CityQuName = cityQuName;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String gettClassName() {
        return tClassName;
    }

    public void settClassName(String tClassName) {
        this.tClassName = tClassName;
    }

    public String getStageName() {
        return StageName;
    }

    public void setStageName(String stageName) {
        StageName = stageName;
    }

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public Date gettDueDate() {
        return tDueDate;
    }

    public void settDueDate(Date tDueDate) {
        this.tDueDate = tDueDate;
    }

    public Date gettActivateDate() {
        return tActivateDate;
    }

    public void settActivateDate(Date tActivateDate) {
        this.tActivateDate = tActivateDate;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }
}
