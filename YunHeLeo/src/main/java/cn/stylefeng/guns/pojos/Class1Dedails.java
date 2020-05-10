package cn.stylefeng.guns.pojos;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
public class Class1Dedails {

    private String id;
    private String classRowGuid;
    private String className;
    private String tLinkName;
    private String imgUrl;
    private String tPainting;
    private List list;
    /**
     * 是否试听
     */
    private Integer tIstest;
    /**
     * 用户名
     */
    private String tUsername;

    /**
     * 真实姓名
     */
    private String tRealyname;

    private String tKnowledgeName;

    private String tCreationName;

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

    public String getClassRowGuid() {
        return classRowGuid;
    }

    public void setClassRowGuid(String classRowGuid) {
        this.classRowGuid = classRowGuid;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    public String gettPainting() {
        return tPainting;
    }

    public void settPainting(String tPainting) {
        this.tPainting = tPainting;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
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

    public String gettRealyname() {
        return tRealyname;
    }

    public void settRealyname(String tRealyname) {
        this.tRealyname = tRealyname;
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
