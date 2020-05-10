package cn.stylefeng.guns.pojos;

import lombok.Data;

import java.util.List;

public class StageClass {

    private String id;
    private String StageRowGuid;
    private String classRowGuid;
    private String StageName;
    private String ClassName;
    private String tCourseName;
    private String ClassUpdateDate;
    private String StageUpdateDate;
    private String imgUrl;
    private String tKnowledgeName;
    private String tCreationName;
    private String tTeacher;
    private String tUpdateDate;
    private Integer tStatus;

    /**
     * 用户名
     */
    private String tUsername;
    /**
     * 真实姓名
     */
    private String tRealyname;

    private Integer buyCount;
    private Integer classCount;
    private Integer tIstest;
    private Integer studyClassCount;
    private Integer stageClassCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStageRowGuid() {
        return StageRowGuid;
    }

    public void setStageRowGuid(String stageRowGuid) {
        StageRowGuid = stageRowGuid;
    }

    public String getClassRowGuid() {
        return classRowGuid;
    }

    public void setClassRowGuid(String classRowGuid) {
        this.classRowGuid = classRowGuid;
    }

    public String getStageName() {
        return StageName;
    }

    public void setStageName(String stageName) {
        StageName = stageName;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String getClassUpdateDate() {
        return ClassUpdateDate;
    }

    public void setClassUpdateDate(String classUpdateDate) {
        ClassUpdateDate = classUpdateDate;
    }

    public String getStageUpdateDate() {
        return StageUpdateDate;
    }

    public void setStageUpdateDate(String stageUpdateDate) {
        StageUpdateDate = stageUpdateDate;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String gettKnowledgeName() {
        return tKnowledgeName;
    }

    public void settKnowledgeName(String tKnowledgeName) {
        this.tKnowledgeName = tKnowledgeName;
    }

    public String gettCreationName() {
        return tCreationName;
    }

    public void settCreationName(String tCreationName) {
        this.tCreationName = tCreationName;
    }

    public String gettTeacher() {
        return tTeacher;
    }

    public void settTeacher(String tTeacher) {
        this.tTeacher = tTeacher;
    }

    public String gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(String tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettRealyname() {
        return tRealyname;
    }

    public void settRealyname(String tRealyname) {
        this.tRealyname = tRealyname;
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

    public Integer gettIstest() {
        return tIstest;
    }

    public void settIstest(Integer tIstest) {
        this.tIstest = tIstest;
    }

    public Integer getStudyClassCount() {
        return studyClassCount;
    }

    public void setStudyClassCount(Integer studyClassCount) {
        this.studyClassCount = studyClassCount;
    }

    public Integer getStageClassCount() {
        return stageClassCount;
    }

    public void setStageClassCount(Integer stageClassCount) {
        this.stageClassCount = stageClassCount;
    }
}
