package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

public class WorkUC {

    private Integer id;
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

    /**
     * 用户名
     */
    @TableField("t_username")
    private String tUsername;
    private String tEmpname;

    /**
     * 手机号
     */
    @TableField("t_mobile")
    private String tMobile;

    /**
     * 课程名称
     */
    @TableField("t_course_name")
    private String tCourseName;

    /**
     * 课节名称
     */
    @TableField("t_name")
    private String tName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettEmpname() {
        return tEmpname;
    }

    public void settEmpname(String tEmpname) {
        this.tEmpname = tEmpname;
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }
}
