package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-04
 */
public class EmpKnowledge implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户名
     */
    private String tUsername;

    @TableField("group_guid")
    private String groupGuid;

    @TableField("row_guid")
    private String rowGuid;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("t_knowledge_name")
    private String tKnowledgeName;

    /**
     * 知识点分级
     */
    @TableField("t_parent_id")
    private Integer tParentId;

    /**
     * 修改人（当前登陆用户）
     */
    @TableField("t_knowledge_man")
    private String tKnowledgeMan;

    /**
     * 修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    /**
     * 状态 0正常 1删除
     */
    @TableField("t_status")
    private Integer tStatus;

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String getGroupGuid() {
        return groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettKnowledgeName() {
        return tKnowledgeName;
    }

    public void settKnowledgeName(String tKnowledgeName) {
        this.tKnowledgeName = tKnowledgeName;
    }

    public Integer gettParentId() {
        return tParentId;
    }

    public void settParentId(Integer tParentId) {
        this.tParentId = tParentId;
    }

    public String gettKnowledgeMan() {
        return tKnowledgeMan;
    }

    public void settKnowledgeMan(String tKnowledgeMan) {
        this.tKnowledgeMan = tKnowledgeMan;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
        "groupGuid=" + groupGuid +
        ", rowGuid=" + rowGuid +
        ", id=" + id +
        ", tKnowledgeName=" + tKnowledgeName +
        ", tParentId=" + tParentId +
        ", tKnowledgeMan=" + tKnowledgeMan +
        ", tUpdateDate=" + tUpdateDate +
        ", tStatus=" + tStatus +
        "}";
    }
}
