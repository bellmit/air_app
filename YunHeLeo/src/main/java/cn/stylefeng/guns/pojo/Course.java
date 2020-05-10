package cn.stylefeng.guns.pojo;

import java.math.BigDecimal;
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
@TableName("tb_course")
public class Course implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程编号
     */
    @TableField("t_course_num")
    private String tCourseNum;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

    /**
     * 课程名称
     */
    @TableField("t_course_name")
    private String tCourseName;

    /**
     * 课程类型【长期课…】0免费课
     */
    @TableField("t_class_type_id")
    public Integer tClassTypeId;

    /**
     * 类别ID 分类ID
     */
    @TableField("t_course_type_id")
    private Integer tCourseTypeId;

    /**
     * 适合级别
     */
    @TableField("t_level")
    private String tLevel;

    @TableField("t_order_no")
    private Integer tOrderNo;

    /**
     * 课程价格
     */
    @TableField("t_price")
    private Double tPrice;

    /**
     * 老师 [管理员是老师的ID] [1,2,3]
     */
    @TableField("t_teacher_id")
    private String tTeacherId;

    @TableField("t_service_id")
    private String tServiceId;

    @TableField("t_img_url")
    private String tImgUrl;

    /**
     * 画材准备
     */
    @TableField("t_painting")
    private String tPainting;

    /**
     * 课程介绍
     */
    @TableField("t_course_introduce")
    private String tCourseIntroduce;

    /**
     * 温馨提示
     */
    @TableField("t_prompt")
    private String tPrompt;

    /**
     * 试听 0:true 1:false
     */
    @TableField("t_try_listen")
    private Integer tTryListen;

    /**
     * 课程状态 0:正常 已发布 1:下架 2:保存
     */
    @TableField("t_status")
    private Integer tStatus;

    /**
     * 课程修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    /**
     * 课程修改人
     */
    @TableField("t_update_man")
    private String tUpdateMan;

    /**
     * 学习数量
     */
    @TableField("t_learn_count")
    private Integer tLearnCount;

    @TableField("stage_id")
    private String stageId;

    /**
     * 课包id
     */
    @TableField("course_package_id")
    private String coursePackageId;

    @TableField("class_id")
    private String classId;

    private Integer buyCount;

    private Integer classCount;

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public Integer gettOrderNo() {
        return tOrderNo;
    }

    public void settOrderNo(Integer tOrderNo) {
        this.tOrderNo = tOrderNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettCourseNum() {
        return tCourseNum;
    }

    public void settCourseNum(String tCourseNum) {
        this.tCourseNum = tCourseNum;
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

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public Integer gettClassTypeId() {
        return tClassTypeId;
    }

    public void settClassTypeId(Integer tClassTypeId) {
        this.tClassTypeId = tClassTypeId;
    }

    public Integer gettCourseTypeId() {
        return tCourseTypeId;
    }

    public void settCourseTypeId(Integer tCourseTypeId) {
        this.tCourseTypeId = tCourseTypeId;
    }

    public String gettLevel() {
        return tLevel;
    }

    public void settLevel(String tLevel) {
        this.tLevel = tLevel;
    }

    public Double gettPrice() {
        return tPrice;
    }

    public void settPrice(Double tPrice) {
        this.tPrice = tPrice;
    }

    public String gettTeacherId() {
        return tTeacherId;
    }

    public void settTeacherId(String tTeacherId) {
        this.tTeacherId = tTeacherId;
    }

    public String gettServiceId() {
        return tServiceId;
    }

    public void settServiceId(String tServiceId) {
        this.tServiceId = tServiceId;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }

    public String gettPainting() {
        return tPainting;
    }

    public void settPainting(String tPainting) {
        this.tPainting = tPainting;
    }

    public String gettCourseIntroduce() {
        return tCourseIntroduce;
    }

    public void settCourseIntroduce(String tCourseIntroduce) {
        this.tCourseIntroduce = tCourseIntroduce;
    }

    public String gettPrompt() {
        return tPrompt;
    }

    public void settPrompt(String tPrompt) {
        this.tPrompt = tPrompt;
    }

    public Integer gettTryListen() {
        return tTryListen;
    }

    public void settTryListen(Integer tTryListen) {
        this.tTryListen = tTryListen;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public String gettUpdateMan() {
        return tUpdateMan;
    }

    public void settUpdateMan(String tUpdateMan) {
        this.tUpdateMan = tUpdateMan;
    }

    public Integer gettLearnCount() {
        return tLearnCount;
    }

    public void settLearnCount(Integer tLearnCount) {
        this.tLearnCount = tLearnCount;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getCoursePackageId() {
        return coursePackageId;
    }

    public void setCoursePackageId(String coursePackageId) {
        this.coursePackageId = coursePackageId;
    }

    @Override
    public String toString() {
        return "Course{" +
        "id=" + id +
        ", tCourseNum=" + tCourseNum +
        ", rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", tCourseName=" + tCourseName +
        ", tClassTypeId=" + tClassTypeId +
        ", tCourseTypeId=" + tCourseTypeId +
        ", tLevel=" + tLevel +
        ", tPrice=" + tPrice +
        ", tTeacherId=" + tTeacherId +
        ", tServiceId=" + tServiceId +
        ", tImgUrl=" + tImgUrl +
        ", tPainting=" + tPainting +
        ", tCourseIntroduce=" + tCourseIntroduce +
        ", tPrompt=" + tPrompt +
        ", tTryListen=" + tTryListen +
        ", tStatus=" + tStatus +
        ", tUpdateDate=" + tUpdateDate +
        ", tUpdateMan=" + tUpdateMan +
        ", tLearnCount=" + tLearnCount +
        ", stageId=" + stageId +
        ", coursePackageId=" + coursePackageId +
        "}";
    }
}
