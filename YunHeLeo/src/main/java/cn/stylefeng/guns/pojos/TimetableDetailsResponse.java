package cn.stylefeng.guns.pojos;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 课表响应给前端数据
 */
public class TimetableDetailsResponse {

    private String TableRowGuid;
    private String ClassRowGuid;
    private String courseRowGuid;
    private String tStudyDate;
    private String userGuid;
    private String tWeeks;
    private String VideoUrl;
    private String tLinkName;
    private String tImgUrl;
    private Integer studyStatus;

    private String tCourseName;
    private String tClassName;
    private String tStageName;
    private String tPackageName;

    public String getTableRowGuid() {
        return TableRowGuid;
    }

    public void setTableRowGuid(String tableRowGuid) {
        TableRowGuid = tableRowGuid;
    }

    public String getClassRowGuid() {
        return ClassRowGuid;
    }

    public void setClassRowGuid(String classRowGuid) {
        ClassRowGuid = classRowGuid;
    }

    public String getCourseRowGuid() {
        return courseRowGuid;
    }

    public void setCourseRowGuid(String courseRowGuid) {
        this.courseRowGuid = courseRowGuid;
    }

    public String gettStudyDate() {
        return tStudyDate;
    }

    public void settStudyDate(String tStudyDate) {
        this.tStudyDate = tStudyDate;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String gettWeeks() {
        return tWeeks;
    }

    public void settWeeks(String tWeeks) {
        this.tWeeks = tWeeks;
    }

    public String getVideoUrl() {
        return VideoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        VideoUrl = videoUrl;
    }

    public String gettLinkName() {
        return tLinkName;
    }

    public void settLinkName(String tLinkName) {
        this.tLinkName = tLinkName;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }

    public Integer getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String gettClassName() {
        return tClassName;
    }

    public void settClassName(String tClassName) {
        this.tClassName = tClassName;
    }

    public String gettStageName() {
        return tStageName;
    }

    public void settStageName(String tStageName) {
        this.tStageName = tStageName;
    }

    public String gettPackageName() {
        return tPackageName;
    }

    public void settPackageName(String tPackageName) {
        this.tPackageName = tPackageName;
    }
}
