package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

public class CourseTypes {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

    /**
     * 课程名称
     */
    @TableField("t_course_name")
    private String tCourseName;

    @TableField("t_type_name")
    private String tTypeName;

    /**
     * 课程类型【长期课…】
     */
    @TableField("t_class_type_id")
    private Integer tClassTypeId;

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

    /**
     * 课程价格
     */
    @TableField("t_price")
    private BigDecimal tPrice;

    /**
     * 老师 [管理员是老师的ID] [1,2,3]
     */
    @TableField("t_teacher_id")
    private String tTeacherId;

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

    private String tPrompt;

    /**
     * 试听 0:true 1:false
     */
    @TableField("t_try_listen")
    private Integer tTryListen;

    /**
     * 课程状态 0:正常 已发布 1:下架 2:删除
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

    /**
     * 课程类型名称 [长期课…]
     */
    @TableField("t_class_name")
    private String tClassName;

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

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String gettTypeName() {
        return tTypeName;
    }

    public void settTypeName(String tTypeName) {
        this.tTypeName = tTypeName;
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

    public BigDecimal gettPrice() {
        return tPrice;
    }

    public void settPrice(BigDecimal tPrice) {
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

    public String gettClassName() {
        return tClassName;
    }

    public void settClassName(String tClassName) {
        this.tClassName = tClassName;
    }
}
