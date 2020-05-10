package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user_link_status")
public class UserLinkStatus {

    @TableId(value = "id", type = IdType.AUTO)
    private String id;
    @TableField(value = "user_guid")
    private String userGuid;
    @TableField(value = "class_guid")
    private String classGuid;
    @TableField(value = "link_guid")
    private String linkGuid;
    @TableField(value = "study_status")
    private Integer studyStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserGuid() {
        return userGuid;
    }

    public void setUserGuid(String userGuid) {
        this.userGuid = userGuid;
    }

    public String getClassGuid() {
        return classGuid;
    }

    public void setClassGuid(String classGuid) {
        this.classGuid = classGuid;
    }

    public String getLinkGuid() {
        return linkGuid;
    }

    public void setLinkGuid(String linkGuid) {
        this.linkGuid = linkGuid;
    }

    public Integer getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(Integer studyStatus) {
        this.studyStatus = studyStatus;
    }
}
