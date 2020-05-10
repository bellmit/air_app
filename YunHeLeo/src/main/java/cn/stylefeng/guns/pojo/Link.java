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
 * @since 2020-03-18
 */
@TableName("tb_link")
public class Link implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 环节名称
     */
    @TableField("t_link_name")
    private String tLinkName;

    @TableField("t_video_url")
    private String tVideoUrl;

    /**
     * 课节id
     */
    @TableField("t_class_id")
    private String tClassId;

    /**
     * 状态 0 已发布 1未发布 2已下架
     */
    @TableField("t_status")
    private Integer tStatus;

    @TableField("study_status")
    private Integer studyStatus;

    @TableField("study_time")
    private Date studyTime;

    /**
     * 创建人
     */
    @TableField("t_create_man")
    private String tCreateMan;

    /**
     * 修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    public Integer getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }

    public Date getStudyTime() {
        return studyTime;
    }

    public void setStudyTime(Date studyTime) {
        this.studyTime = studyTime;
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

    public String gettLinkName() {
        return tLinkName;
    }

    public void settLinkName(String tLinkName) {
        this.tLinkName = tLinkName;
    }

    public String gettVideoUrl() {
        return tVideoUrl;
    }

    public void settVideoUrl(String tVideoUrl) {
        this.tVideoUrl = tVideoUrl;
    }

    public String gettClassId() {
        return tClassId;
    }

    public void settClassId(String tClassId) {
        this.tClassId = tClassId;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public String gettCreateMan() {
        return tCreateMan;
    }

    public void settCreateMan(String tCreateMan) {
        this.tCreateMan = tCreateMan;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    @Override
    public String toString() {
        return "Link{" +
        "rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", id=" + id +
        ", tLinkName=" + tLinkName +
        ", tVideoUrl=" + tVideoUrl +
        ", tClassId=" + tClassId +
        ", tStatus=" + tStatus +
        ", tCreateMan=" + tCreateMan +
        ", tUpdateDate=" + tUpdateDate +
        "}";
    }
}
