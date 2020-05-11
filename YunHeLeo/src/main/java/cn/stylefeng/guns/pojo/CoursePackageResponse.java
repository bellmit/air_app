package cn.stylefeng.guns.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

public class CoursePackageResponse {

    private String cpuRowguid;
    private String courseGuid;
    private String packageRowguid;
    private String tCourseName;
    private String packageName;
    private String stageName;
    private Integer cpuStatus;
    private Double price;
    private String tDueTime;
    private Integer activateDate;// 激活时长
    private Integer studyDate;// 可学习时长
    private Integer classCount;
    private String stageRowguid;
    private Integer classTypeId;
    private Boolean isBuy;// 是否购买

    public String getCpuRowguid() {
        return cpuRowguid;
    }

    public void setCpuRowguid(String cpuRowguid) {
        this.cpuRowguid = cpuRowguid;
    }

    public String getCourseGuid() {
        return courseGuid;
    }

    public void setCourseGuid(String courseGuid) {
        this.courseGuid = courseGuid;
    }

    public String getPackageRowguid() {
        return packageRowguid;
    }

    public void setPackageRowguid(String packageRowguid) {
        this.packageRowguid = packageRowguid;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public Integer getCpuStatus() {
        return cpuStatus;
    }

    public void setCpuStatus(Integer cpuStatus) {
        this.cpuStatus = cpuStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String gettDueTime() {
        return tDueTime;
    }

    public void settDueTime(String tDueTime) {
        this.tDueTime = tDueTime;
    }

    public Integer getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Integer activateDate) {
        this.activateDate = activateDate;
    }

    public Integer getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Integer studyDate) {
        this.studyDate = studyDate;
    }

    public Boolean getBuy() {
        return isBuy;
    }

    public void setBuy(Boolean buy) {
        isBuy = buy;
    }

    public Integer getClassCount() {
        return classCount;
    }

    public void setClassCount(Integer classCount) {
        this.classCount = classCount;
    }

    public String getStageRowguid() {
        return stageRowguid;
    }

    public void setStageRowguid(String stageRowguid) {
        this.stageRowguid = stageRowguid;
    }

    public Integer getClassTypeId() {
        return classTypeId;
    }

    public void setClassTypeId(Integer classTypeId) {
        this.classTypeId = classTypeId;
    }

    public Boolean getIsBuy() {
        return isBuy;
    }

    public void setIsBuy(Boolean buy) {
        isBuy = buy;
    }
}
