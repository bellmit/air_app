package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-13
 */
@TableName("tb_works")
public class Works implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("recordguid")
    private String recordguid;

      @TableId(value = "id", type = IdType.AUTO)
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

    @TableField("t_user_guid")
    private String tUserGuid;

    /**
     * 评价教师GUID
     */
    @TableField("t_teacher_guid")
    private String tTeacherGuid;

    /**
     * 教师评价日期
     */
    @TableField("t_valuation_date")
    private Date tValuationDate;

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
     * 课程GUID
     */
    @TableField("t_course_guid")
    private String tCourseGuid;

    @TableField("t_link_guid")
    private String linkGuid;

    /**
     * 课节guid
     */
    @TableField("t_class_guid")
    private String tClassGuid;

    /**
     * 作品图片
     */
    @TableField("t_img_url")
    private String tImgUrl;

    @TableField("t_works_type")
    private String tWorksType;

    /**
     * 状态
     */
    @TableField("t_status")
    private Integer tStatus;

    public String getLinkGuid() {
        return linkGuid;
    }

    public void setLinkGuid(String linkGuid) {
        this.linkGuid = linkGuid;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getRecordguid() {
        return recordguid;
    }

    public void setRecordguid(String recordguid) {
        this.recordguid = recordguid;
    }

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

    public String gettUserGuid() {
        return tUserGuid;
    }

    public void settUserGuid(String tUserGuid) {
        this.tUserGuid = tUserGuid;
    }

    public String gettTeacherGuid() {
        return tTeacherGuid;
    }

    public void settTeacherGuid(String tTeacherGuid) {
        this.tTeacherGuid = tTeacherGuid;
    }

    public Date gettValuationDate() {
        return tValuationDate;
    }

    public void settValuationDate(Date tValuationDate) {
        this.tValuationDate = tValuationDate;
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

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Override
    public String toString() {
        return "Works{" +
        "rowGuid=" + rowGuid +
        ", recordguid=" + recordguid +
        ", id=" + id +
        ", tWorksNum=" + tWorksNum +
        ", tWorksName=" + tWorksName +
        ", tUserGuid=" + tUserGuid +
        ", tTeacherGuid=" + tTeacherGuid +
        ", tValuationDate=" + tValuationDate +
        ", tContent=" + tContent +
        ", tUploadDate=" + tUploadDate +
        ", tCourseGuid=" + tCourseGuid +
        ", tClassGuid=" + tClassGuid +
        ", tImgUrl=" + tImgUrl +
        ", tWorksType=" + tWorksType +
        ", tStatus=" + tStatus +
        "}";
    }
}
