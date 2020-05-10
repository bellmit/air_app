package cn.stylefeng.guns.pojos;

import lombok.Data;
import lombok.ToString;

@ToString
public class ClassDedailsResponse {

    private String id;
    private String rowGuid;
    private String classRowGuid;
    private String tName;
    private String tLinkName;
    private String imgUrl;
    /**
     * 是否试听
     */
    private Integer tIstest;
    /**
     * 用户名
     */
    private String tUsername;
    private String[] tTeacher;
    private Integer tStatus;

    /**
     * 真实姓名
     */
    private String tRealyname;

    private Integer[] tKnowledgeName;

    private Integer[] tCreationName;

    private String tCourseName;

    private String tUpdateDate;

    private Integer buyCount;
    private Integer classCount;
    private Integer studyClassCount;
    private Integer studyStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getClassRowGuid() {
        return classRowGuid;
    }

    public void setClassRowGuid(String classRowGuid) {
        this.classRowGuid = classRowGuid;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettLinkName() {
        return tLinkName;
    }

    public void settLinkName(String tLinkName) {
        this.tLinkName = tLinkName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer gettIstest() {
        return tIstest;
    }

    public void settIstest(Integer tIstest) {
        this.tIstest = tIstest;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String[] gettTeacher() {
        return tTeacher;
    }

    public void settTeacher(String[] tTeacher) {
        this.tTeacher = tTeacher;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public String gettRealyname() {
        return tRealyname;
    }

    public void settRealyname(String tRealyname) {
        this.tRealyname = tRealyname;
    }

    public Integer[] gettKnowledgeName() {
        return tKnowledgeName;
    }

    public void settKnowledgeName(Integer[] tKnowledgeName) {
        this.tKnowledgeName = tKnowledgeName;
    }

    public Integer[] gettCreationName() {
        return tCreationName;
    }

    public void settCreationName(Integer[] tCreationName) {
        this.tCreationName = tCreationName;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(String tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
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

    public Integer getStudyClassCount() {
        return studyClassCount;
    }

    public void setStudyClassCount(Integer studyClassCount) {
        this.studyClassCount = studyClassCount;
    }

    public Integer getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }
}
