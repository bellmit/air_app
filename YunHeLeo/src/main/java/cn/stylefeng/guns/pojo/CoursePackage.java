package cn.stylefeng.guns.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-18
 */
@TableName("tb_course_package")
public class CoursePackage implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课包名称
     */
    @TableField("t_name")
    private String packageName;

    /**
     * 阶段id
     */
    @TableField("t_stage_id")
    private String tStageId;

    @TableField("t_class_type_id")
    private Integer tClassTypeId;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

    /**
     * 待激活时长
     */
    @TableField("t_activate_date")
    private Integer tActivateDate;

    /**
     * 可学习时长
     */
    @TableField("t_study_date")
    private Integer tStudyDate;

    @TableField("t_price")
    private Double tPrice;

    /**
     * 课包状态 0未发布 1已发布 2已下架
     */
    @TableField("t_status")
    private Integer tStatus;

    /**
     * 到期日期
     */
    @TableField("t_due_time")
    private Date tDueTime;

    /**
     * 激活日期
     */
    @TableField("t_activate_time")
    private Date tActivateTime;

    public Date gettDueTime() {
        return tDueTime;
    }

    public void settDueTime(Date tDueDate) {
        this.tDueTime = tDueDate;
    }

    public Date gettActivateTime() {
        return tActivateTime;
    }

    public void settActivateTime(Date tActivateTime) {
        this.tActivateTime = tActivateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String gettStageId() {
        return tStageId;
    }

    public void settStageId(String tStageId) {
        this.tStageId = tStageId;
    }

    public Integer gettClassTypeId() {
        return tClassTypeId;
    }

    public void settClassTypeId(Integer tClassTypeId) {
        this.tClassTypeId = tClassTypeId;
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

    public Integer gettActivateDate() {
        return tActivateDate;
    }

    public void settActivateDate(Integer tActivateDate) {
        this.tActivateDate = tActivateDate;
    }

    public Integer gettStudyDate() {
        return tStudyDate;
    }

    public void settStudyDate(Integer tStudyDate) {
        this.tStudyDate = tStudyDate;
    }

    public Double gettPrice() {
        return tPrice;
    }

    public void settPrice(Double tPrice) {
        this.tPrice = tPrice;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Override
    public String toString() {
        return "CoursePackage{" +
        "id=" + id +
        ", packageName=" + packageName +
        ", tStageId=" + tStageId +
        ", tClassTypeId=" + tClassTypeId +
        ", rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", tActivateDate=" + tActivateDate +
        ", tStudyDate=" + tStudyDate +
        ", tPrice=" + tPrice +
        ", tStatus=" + tStatus +
        "}";
    }
}
