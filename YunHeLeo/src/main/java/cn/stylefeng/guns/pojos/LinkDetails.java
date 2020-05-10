package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

public class LinkDetails {

    private String LinkRowGuid;
    private String ClassRowGuid;
    private Integer id;
    private Integer tLinkType;
    /**
     * 环节名称
     */
    private String tLinkName;
    private String tVideoUrl;

    /**
     * 状态 0 已发布 1未发布 2已下架
     */
    private Integer tStatus;

    /**
     * 创建人
     */
    private String tCreateMan;

    /**
     * 修改日期
     */
    private Date tUpdateDate;

    private String tUsername;

    public String getLinkRowGuid() {
        return LinkRowGuid;
    }

    public void setLinkRowGuid(String linkRowGuid) {
        LinkRowGuid = linkRowGuid;
    }

    public String getClassRowGuid() {
        return ClassRowGuid;
    }

    public void setClassRowGuid(String classRowGuid) {
        ClassRowGuid = classRowGuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer gettLinkType() {
        return tLinkType;
    }

    public void settLinkType(Integer tLinkType) {
        this.tLinkType = tLinkType;
    }

    public String gettLinkName() {
        return tLinkName;
    }

    public void settLinkName(String tLinkName) {
        this.tLinkName = tLinkName;
    }

    public String gettVideoUrl() {
        return tVideoUrl;
    }

    public void settVideoUrl(String tVideoUrl) {
        this.tVideoUrl = tVideoUrl;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public String gettCreateMan() {
        return tCreateMan;
    }

    public void settCreateMan(String tCreateMan) {
        this.tCreateMan = tCreateMan;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }
}
