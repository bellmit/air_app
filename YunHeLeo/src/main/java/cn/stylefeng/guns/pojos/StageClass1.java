package cn.stylefeng.guns.pojos;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StageClass1 {

    private String id;
    private String StageRowGuid;
    private String classRowGuid;
    private String StageName;
    private String ClassName;
    private String tCourseName;
    private String ClassUpdateDate;
    private String StageUpdateDate;
    private String imgUrl;
    private String tPainting;
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

    private Date activateTime;
    private Date dueTime;
    private Integer status;

    private List list = new ArrayList();

    private Boolean isStudy; // 是否加锁（是否可以学习）

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Date getDueTime() {
        return dueTime;
    }

    public void setDueTime(Date dueTime) {
        this.dueTime = dueTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getIsStudy() {
        return isStudy;
    }

    public void setIsStudy(Boolean study) {
        isStudy = study;
    }

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

    public String gettPainting() {
        return tPainting;
    }

    public void settPainting(String tPainting) {
        this.tPainting = tPainting;
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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
