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
@TableName("tb_stage")
public class Stage implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 阶段名
     */
    @TableField("t_name")
    private String tName;

    @TableField("t_order")
    private String tOrder;

    /**
     * 课节id
     */
    @TableField("t_class_id")
    private String tClassId;

    /**
     * 状态
     */
    @TableField("t_status")
    private String tStatus;

    /**
     * 阶段创建人
     */
    @TableField("t_update_man")
    private String tUpdateMan;

    /**
     * 阶段修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    @TableField("t_learn_count")
    private Integer tLearnCount;


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

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettOrder() {
        return tOrder;
    }

    public void settOrder(String tOrder) {
        this.tOrder = tOrder;
    }

    public String gettClassId() {
        return tClassId;
    }

    public void settClassId(String tClassId) {
        this.tClassId = tClassId;
    }

    public String gettStatus() {
        return tStatus;
    }

    public void settStatus(String tStatus) {
        this.tStatus = tStatus;
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

    public Integer gettLearnCount() {
        return tLearnCount;
    }

    public void settLearnCount(Integer tLearnCount) {
        this.tLearnCount = tLearnCount;
    }

    @Override
    public String toString() {
        return "Stage{" +
        "rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", id=" + id +
        ", tName=" + tName +
        ", tOrder=" + tOrder +
        ", tClassId=" + tClassId +
        ", tStatus=" + tStatus +
        ", tUpdateMan=" + tUpdateMan +
        ", tUpdateDate=" + tUpdateDate +
        ", tLearnCount=" + tLearnCount +
        "}";
    }
}
