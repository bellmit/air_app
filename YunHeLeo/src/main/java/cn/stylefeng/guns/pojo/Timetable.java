package cn.stylefeng.guns.pojo;

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
 * @since 2020-03-31
 */
@TableName("tb_timetable")
public class Timetable implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_guid")
    private String userGuid;

    /**
     * 课程GUID
     */
    @TableField("t_course_guid")
    private String tCourseGuid;

    @TableField("t_package_guid")
    private String tPackageGuid;

    /**
     * 加入课程表日期
     */
    @TableField("t_create_date")
    private Date tCreateDate;

    /**
     * 星期
     */
    @TableField("t_weeks")
    private Integer tWeeks;

    @TableField("t_class_id")
    private String tClassId;

    /**
     * 状态
     */
    @TableField("t_status")
    private Integer tStatus;

    public String gettClassId() {
        return tClassId;
    }

    public void settClassId(String tClassId) {
        this.tClassId = tClassId;
    }

    public Date gettCreateDate() {
        return tCreateDate;
    }

    public void settCreateDate(Date tCreateDate) {
        this.tCreateDate = tCreateDate;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String gettCourseGuid() {
        return tCourseGuid;
    }

    public void settCourseGuid(String tCourseGuid) {
        this.tCourseGuid = tCourseGuid;
    }

    public String gettPackageGuid() {
        return tPackageGuid;
    }

    public void settPackageGuid(String tPackageGuid) {
        this.tPackageGuid = tPackageGuid;
    }

    public Integer gettWeeks() {
        return tWeeks;
    }

    public void settWeeks(Integer tWeeks) {
        this.tWeeks = tWeeks;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Override
    public String toString() {
        return "Timetable{" +
        "rowGuid=" + rowGuid +
        ", id=" + id +
        ", userGuid=" + userGuid +
        ", tCourseGuid=" + tCourseGuid +
        ", tPackageGuid=" + tPackageGuid +
        ", tWeeks=" + tWeeks +
        ", tStatus=" + tStatus +
        "}";
    }
}
