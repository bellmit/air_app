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
@TableName("tb_course_user")
public class CourseUser implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("t_user_guid")
    private String tUserGuid;

    @TableField("t_course_guid")
    private String tCourseGuid;

    /**
     * 0未开始 1正在学 2已到期
     */
    @TableField("t_status")
    private Integer tStatus;

    @TableField("t_create_time")
    private Date tCreateTime;

    @TableField("t_course_package_id")
    private String tCoursePackageId;

    public String gettCoursePackageId() {
        return tCoursePackageId;
    }

    public void settCoursePackageId(String tCoursePackageId) {
        this.tCoursePackageId = tCoursePackageId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettUserGuid() {
        return tUserGuid;
    }

    public void settUserGuid(String tUserGuid) {
        this.tUserGuid = tUserGuid;
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

    public Date gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Date tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    @Override
    public String toString() {
        return "CourseUser{" +
        "id=" + id +
        ", tUserGuid=" + tUserGuid +
        ", tCourseGuid=" + tCourseGuid +
        ", tStatus=" + tStatus +
        ", tCreateTime=" + tCreateTime +
        "}";
    }
}
