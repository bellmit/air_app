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
 * @since 2020-03-02
 */
@TableName("tb_img")
public class Img implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

    @TableId(value = "id")
    private String id;

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

    @TableField("t_link_id")
    private String linkId;

    /**
     * 链接类型
     */
    @TableField("t_link_type")
    private String tLinkType;

    /**
     * 状态
     */
    @TableField("t_status")
    private Integer tStatus;

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

    private Integer courseTypeId;// 课程类型id  长期班...

    public Integer getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Integer courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
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

    @Override
    public String toString() {
        return "Img{" +
        "rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", id=" + id +
        ", tImgType=" + tImgType +
        ", tName=" + tName +
        ", tOrderNo=" + tOrderNo +
        ", tImgUrl=" + tImgUrl +
        ", tLinkType=" + tLinkType +
        ", tStatus=" + tStatus +
        ", tUploadMan=" + tUploadMan +
        ", tUploadDate=" + tUploadDate +
        "}";
    }
}
