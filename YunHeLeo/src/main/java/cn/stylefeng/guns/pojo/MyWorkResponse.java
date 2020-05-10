package cn.stylefeng.guns.pojo;

import lombok.Data;

public class MyWorkResponse {

    private String rowGuid;
    private String tUploadDate;
    private String tImgUrl;
    private String courseName;
    private Boolean isEvaluation;
    private String tContent;

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
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
}
