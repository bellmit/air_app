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
    private Integer t_activate_date;// 激活时长
    private Integer t_study_date;// 可学习时长
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

    public Integer getT_activate_date() {
        return t_activate_date;
    }

    public void setT_activate_date(Integer t_activate_date) {
        this.t_activate_date = t_activate_date;
    }

    public Integer getT_study_date() {
        return t_study_date;
    }

    public void setT_study_date(Integer t_study_date) {
        this.t_study_date = t_study_date;
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
