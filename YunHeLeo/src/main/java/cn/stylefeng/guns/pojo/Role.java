package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-02-28
 */
@TableName("tb_role")
public class Role implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "role_id", type = IdType.ID_WORKER)
    private String roleId;

    /**
     * 角色名称
     */
    @TableField("t_role_name")
    private String tRoleName;

    /**
     * 角色描述
     */
    @TableField("t_role_description")
    private String tRoleDescription;

    /**
     * 权限ID集
     */
    @TableField("t_ids")
    private String tIds;

    /**
     * 状态 1启用 2停用
     */
    @TableField("t_status")
    private Integer tStatus;

    /**
     * 新增时间
     */
    @TableField("t_create_date")
    private Date tCreateDate;

    /**
     * 修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;


    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String gettRoleName() {
        return tRoleName;
    }

    public void settRoleName(String tRoleName) {
        this.tRoleName = tRoleName;
    }

    public String gettRoleDescription() {
        return tRoleDescription;
    }

    public void settRoleDescription(String tRoleDescription) {
        this.tRoleDescription = tRoleDescription;
    }

    public String gettIds() {
        return tIds;
    }

    public void settIds(String tIds) {
        this.tIds = tIds;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    public Date gettCreateDate() {
        return tCreateDate;
    }

    public void settCreateDate(Date tCreateDate) {
        this.tCreateDate = tCreateDate;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    @Override
    public String toString() {
        return "Role{" +
        "roleId=" + roleId +
        ", tRoleName=" + tRoleName +
        ", tRoleDescription=" + tRoleDescription +
        ", tIds=" + tIds +
        ", tStatus=" + tStatus +
        ", tCreateDate=" + tCreateDate +
        ", tUpdateDate=" + tUpdateDate +
        "}";
    }
}
