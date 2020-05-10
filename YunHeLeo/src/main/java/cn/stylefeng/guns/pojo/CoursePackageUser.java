package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@TableName("tb_course_package_user")
@ToString
public class CoursePackageUser {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("row_guid")
    private String rowGuid;

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

    /**
     * 用户GUID
     */
    @TableField("user_guid")
    private String userGuid;

    /**
     * 课程GUID
     */
    @TableField("t_course_guid")
    private String tCourseGuid;

    @TableField("t_status")
    private Integer tStatus;

    @TableField("t_package_guid")
    private String tPackageGuid;

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

    public Date gettDueTime() {
        return tDueTime;
    }

    public void settDueTime(Date tDueTime) {
        this.tDueTime = tDueTime;
    }

    public Date gettActivateTime() {
        return tActivateTime;
    }

    public void settActivateTime(Date tActivateTime) {
        this.tActivateTime = tActivateTime;
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

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public String gettPackageGuid() {
        return tPackageGuid;
    }

    public void settPackageGuid(String tPackageGuid) {
        this.tPackageGuid = tPackageGuid;
    }
}
