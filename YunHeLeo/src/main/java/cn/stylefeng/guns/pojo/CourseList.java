package cn.stylefeng.guns.pojo;

import java.util.Date;

public class CourseList {

    private String rowGuid;
    private String tCourseNum;
    private String tCourseName;
    private Integer tScore;
    private Double tPrice;
    private String tImgUrl;
    private Date tUpdateDate;
    private String tTypeName;
    private Integer tClassTypeId;
    private String tClassName;
    private Integer buyCount;
    private Integer classCount;
    private Integer stageCount;
    private Integer studyClassCount;
    private Integer tLearnCount;

    public String gettCourseNum() {
        return tCourseNum;
    }

    public void settCourseNum(String tCourseNum) {
        this.tCourseNum = tCourseNum;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
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

    public String gettClassName() {
        return tClassName;
    }

    public void settClassName(String tClassName) {
        this.tClassName = tClassName;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }

    public Integer gettLearnCount() {
        return tLearnCount;
    }

    public void settLearnCount(Integer tLearnCount) {
        this.tLearnCount = tLearnCount;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public Integer gettScore() {
        return tScore;
    }

    public void settScore(Integer tScore) {
        this.tScore = tScore;
    }

    public Double gettPrice() {
        return tPrice;
    }

    public void settPrice(Double tPrice) {
        this.tPrice = tPrice;
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

    public Integer getStudyClassCount() {
        return studyClassCount;
    }

    public void setStudyClassCount(Integer studyClassCount) {
        this.studyClassCount = studyClassCount;
    }
}
