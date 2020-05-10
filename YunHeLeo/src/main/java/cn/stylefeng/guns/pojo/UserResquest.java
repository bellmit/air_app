package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-09
 */
@TableName("tb_user")
public class UserResquest implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

    private Integer workCount;
    @TableField("wx_unionid")
    private String wx_unionid;
    @TableField("qq_unionid")
    private String qq_unionid;

    /**
     * 主键
     */
      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String tUsername;

    @TableField("t_password")
    private String tPassword;

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
     * 年龄
     */
    @TableField("t_age")
    private Integer tAge;

    /**
     * 孩子姓名
     */
    @TableField("t_name")
    private String tName;

    /**
     * 头像
     */
    @TableField("t_head_img")
    private String tHeadImg;

    /**
     * 所拥有的课程ID
     */
    @TableField("t_course_id")
    private String tCourseId;

    /**
     * 状态 0 正常 1 禁用 3 删除
     */
    @TableField("t_status")
    private Integer tStatus;

    @TableField("stage_id")
    private Integer stageId;

    @TableField("show_stage_id")
    private Integer showStageId;

    public Integer getShowStageId() {
        return showStageId;
    }

    public void setShowStageId(Integer showStageId) {
        this.showStageId = showStageId;
    }

    public Integer getWorkCount() {
        return workCount;
    }

    public void setWorkCount(Integer workCount) {
        this.workCount = workCount;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getGroupGuid() {
        return groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
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

    public Integer gettAge() {
        return tAge;
    }

    public void settAge(Integer tAge) {
        this.tAge = tAge;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettHeadImg() {
        return tHeadImg;
    }

    public void settHeadImg(String tHeadImg) {
        this.tHeadImg = tHeadImg;
    }

    public String gettCourseId() {
        return tCourseId;
    }

    public void settCourseId(String tCourseId) {
        this.tCourseId = tCourseId;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public String getWx_unionid() {
        return wx_unionid;
    }

    public void setWx_unionid(String wx_unionid) {
        this.wx_unionid = wx_unionid;
    }

    public String getQq_unionid() {
        return qq_unionid;
    }

    public void setQq_unionid(String qq_unionid) {
        this.qq_unionid = qq_unionid;
    }

    @Override
    public String toString() {
        return "User{" +
        "rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", id=" + id +
        ", tUsername=" + tUsername +
        ", tPassword=" + tPassword +
        ", tMobile=" + tMobile +
        ", tSex=" + tSex +
        ", tChildrenDate=" + tChildrenDate +
        ", tStudentNo=" + tStudentNo +
        ", tRegisterDate=" + tRegisterDate +
        ", tRegisterIp=" + tRegisterIp +
        ", tAge=" + tAge +
        ", tName=" + tName +
        ", tHeadImg=" + tHeadImg +
        ", tCourseId=" + tCourseId +
        ", tStatus=" + tStatus +
        ", stageId=" + stageId +
        "}";
    }
}
