package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-11
 */
@TableName("tb_video")
public class Video implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

    /**
     * 视频名称
     */
    @TableField("t_video_name")
    private String tVideoName;

    /**
     * 视频url
     */
    @TableField("t_video_url")
    private String tVideoUrl;

    @TableField("t_status")
    private Integer tStatus;

    /**
     * 环节id
     */
    @TableField("t_link_id")
    private Integer tLinkId;


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

    public String getGroupGuid() {
        return groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }

    public String gettVideoName() {
        return tVideoName;
    }

    public void settVideoName(String tVideoName) {
        this.tVideoName = tVideoName;
    }

    public String gettVideoUrl() {
        return tVideoUrl;
    }

    public void settVideoUrl(String tVideoUrl) {
        this.tVideoUrl = tVideoUrl;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public Integer gettLinkId() {
        return tLinkId;
    }

    public void settLinkId(Integer tLinkId) {
        this.tLinkId = tLinkId;
    }

    @Override
    public String toString() {
        return "Video{" +
        "id=" + id +
        ", rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", tVideoName=" + tVideoName +
        ", tVideoUrl=" + tVideoUrl +
        ", tStatus=" + tStatus +
        ", tLinkId=" + tLinkId +
        "}";
    }
}
