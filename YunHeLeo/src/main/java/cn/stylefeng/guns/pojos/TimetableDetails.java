package cn.stylefeng.guns.pojos;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
public class TimetableDetails {

    private String TableRowGuid;
    private String ClassRowGuid;
    private Date tCreateDate;
    private String userGuid;
    private String tWeeks;
    private String VideoUrl;
    private String tLinkName;
    private String tImgUrl;

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

    public Date gettCreateDate() {
        return tCreateDate;
    }

    public void settCreateDate(Date tCreateDate) {
        this.tCreateDate = tCreateDate;
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
