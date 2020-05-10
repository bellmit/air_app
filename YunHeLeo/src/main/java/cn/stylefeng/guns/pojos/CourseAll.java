package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

public class CourseAll implements Serializable {

    private Integer id;

    private String rowGuid;

    private String tCourseNum;

    private String tCourseName;

    private String tCourseIntroduce;

    private Integer tClassTypeId;

    private Integer tOrderNo;

    private String tContent;

    private Integer tScore;

    private String tUsername;

    private String tImgUrl;

    private String tPainting;

    /**
     * 课程价格
     */
    private Double tPrice;

    private Integer tStatus;

    private Date tUpdateDate;

    private String tLevelName;

    private String tClassName;

    private String tTypeName;

    private Integer tCourseTypeId;

    private Integer tLearnCount;

    private String tUserGuid;

    private Integer courseCount;

    private String tCourseGuid;
    private String packageGuid;
    private Boolean isActivate;

    private Integer buyCount;
    private Integer classCount;
    private Integer stageCount;
    private String tPrompt;
    private Integer studyClassCount;

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

    public String gettCourseNum() {
        return tCourseNum;
    }

    public void settCourseNum(String tCourseNum) {
        this.tCourseNum = tCourseNum;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String gettCourseIntroduce() {
        return tCourseIntroduce;
    }

    public void settCourseIntroduce(String tCourseIntroduce) {
        this.tCourseIntroduce = tCourseIntroduce;
    }

    public Integer gettClassTypeId() {
        return tClassTypeId;
    }

    public void settClassTypeId(Integer tClassTypeId) {
        this.tClassTypeId = tClassTypeId;
    }

    public Integer gettOrderNo() {
        return tOrderNo;
    }

    public void settOrderNo(Integer tOrderNo) {
        this.tOrderNo = tOrderNo;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public Integer gettScore() {
        return tScore;
    }

    public void settScore(Integer tScore) {
        this.tScore = tScore;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
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

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public String gettLevelName() {
        return tLevelName;
    }

    public void settLevelName(String tLevelName) {
        this.tLevelName = tLevelName;
    }

    public String gettClassName() {
        return tClassName;
    }

    public void settClassName(String tClassName) {
        this.tClassName = tClassName;
    }

    public String gettTypeName() {
        return tTypeName;
    }

    public void settTypeName(String tTypeName) {
        this.tTypeName = tTypeName;
    }

    public Integer gettCourseTypeId() {
        return tCourseTypeId;
    }

    public void settCourseTypeId(Integer tCourseTypeId) {
        this.tCourseTypeId = tCourseTypeId;
    }

    public Integer gettLearnCount() {
        return tLearnCount;
    }

    public void settLearnCount(Integer tLearnCount) {
        this.tLearnCount = tLearnCount;
    }

    public String gettUserGuid() {
        return tUserGuid;
    }

    public void settUserGuid(String tUserGuid) {
        this.tUserGuid = tUserGuid;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    public String gettCourseGuid() {
        return tCourseGuid;
    }

    public void settCourseGuid(String tCourseGuid) {
        this.tCourseGuid = tCourseGuid;
    }

    public String getPackageGuid() {
        return packageGuid;
    }

    public void setPackageGuid(String packageGuid) {
        this.packageGuid = packageGuid;
    }

    public Boolean getIsActivate() {
        return isActivate;
    }

    public void setIsActivate(Boolean activate) {
        isActivate = activate;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public Integer getStageCount() {
        return stageCount;
    }

    public void setStageCount(Integer stageCount) {
        this.stageCount = stageCount;
    }

    public String gettPrompt() {
        return tPrompt;
    }

    public void settPrompt(String tPrompt) {
        this.tPrompt = tPrompt;
    }

    public Integer getStudyClassCount() {
        return studyClassCount;
    }

    public void setStudyClassCount(Integer studyClassCount) {
        this.studyClassCount = studyClassCount;
    }
}
