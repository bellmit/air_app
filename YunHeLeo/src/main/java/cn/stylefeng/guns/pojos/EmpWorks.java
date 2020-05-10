package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

public class EmpWorks {
    private String id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String tUsername;
    /**
     * 作品编号
     */
    @TableField("t_works_num")
    private String tWorksNum;

    /**
     * 作品名
     */
    @TableField("t_works_name")
    private String tWorksName;

    /**
     * 教师评价日期
     */
    @TableField("t_valuation_date")
    private Date tValuationDate;

    /**
     * 评价教师GUID
     */
    @TableField("t_teacher_guid")
    private String tTeacherGuid;

    /**
     * 课程GUID
     */
    @TableField("t_course_guid")
    private String tCourseGuid;

    /**
     * 课节guid
     */
    @TableField("t_class_guid")
    private String tClassGuid;

    /**
     * 教师评价内容
     */
    @TableField("t_content")
    private String tContent;

    /**
     * 上传日期
     */
    @TableField("t_upload_date")
    private Date tUploadDate;

    /**
     * 作品图片
     */
    @TableField("t_img_url")
    private String tImgUrl;

    @TableField("t_works_type")
    private String tWorksType;

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

    public String gettWorksNum() {
        return tWorksNum;
    }

    public void settWorksNum(String tWorksNum) {
        this.tWorksNum = tWorksNum;
    }

    public String gettWorksName() {
        return tWorksName;
    }

    public void settWorksName(String tWorksName) {
        this.tWorksName = tWorksName;
    }

    public Date gettValuationDate() {
        return tValuationDate;
    }

    public void settValuationDate(Date tValuationDate) {
        this.tValuationDate = tValuationDate;
    }

    public String gettTeacherGuid() {
        return tTeacherGuid;
    }

    public void settTeacherGuid(String tTeacherGuid) {
        this.tTeacherGuid = tTeacherGuid;
    }

    public String gettCourseGuid() {
        return tCourseGuid;
    }

    public void settCourseGuid(String tCourseGuid) {
        this.tCourseGuid = tCourseGuid;
    }

    public String gettClassGuid() {
        return tClassGuid;
    }

    public void settClassGuid(String tClassGuid) {
        this.tClassGuid = tClassGuid;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public Date gettUploadDate() {
        return tUploadDate;
    }

    public void settUploadDate(Date tUploadDate) {
        this.tUploadDate = tUploadDate;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }

    public String gettWorksType() {
        return tWorksType;
    }

    public void settWorksType(String tWorksType) {
        this.tWorksType = tWorksType;
    }
}
