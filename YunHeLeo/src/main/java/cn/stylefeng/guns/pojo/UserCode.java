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
 * @since 2020-03-22
 */
@TableName("tb_User_Code")
public class UserCode implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("Row_Guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("t_CreateTime")
    private Date tCreatetime;

    @TableField("t_code")
    private String tCode;


    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getGroupGuid() {
        return groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date gettCreatetime() {
        return tCreatetime;
    }

    public void settCreatetime(Date tCreatetime) {
        this.tCreatetime = tCreatetime;
    }

    public String gettCode() {
        return tCode;
    }

    public void settCode(String tCode) {
        this.tCode = tCode;
    }

    @Override
    public String toString() {
        return "UserCode{" +
        "rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", id=" + id +
        ", tCreatetime=" + tCreatetime +
        ", tCode=" + tCode +
        "}";
    }
}
