package cn.stylefeng.guns.vo;

import lombok.Data;

import java.util.Date;

public class WorkResponse {

    private String rowGuid;
    private String tWorksName;
    private String tUploadDate;
    private String tImgUrl;
    private String className;
    private String courseName;
    private Boolean isEvaluation;
    private String tContent;
    private String teacherName;
    private String valuationDate;
    private String TeacherImg;

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String gettWorksName() {
        return tWorksName;
    }

    public void settWorksName(String tWorksName) {
        this.tWorksName = tWorksName;
    }

    public String gettUploadDate() {
        return tUploadDate;
    }

    public void settUploadDate(String tUploadDate) {
        this.tUploadDate = tUploadDate;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Boolean getIsEvaluation() {
        return isEvaluation;
    }

    public void setIsEvaluation(Boolean evaluation) {
        isEvaluation = evaluation;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getValuationDate() {
        return valuationDate;
    }

    public void setValuationDate(String valuationDate) {
        this.valuationDate = valuationDate;
    }

    public String getTeacherImg() {
        return TeacherImg;
    }

    public void setTeacherImg(String teacherImg) {
        TeacherImg = teacherImg;
    }
}
