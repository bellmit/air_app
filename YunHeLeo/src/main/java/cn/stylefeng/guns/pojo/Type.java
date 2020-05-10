package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-03
 */
@TableName("tb_type")
public class Type implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("group_guid")
    private String groupGuid;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类别名称
     */
    @TableField("t_type_name")
    private String tTypeName;

    /**
     * 排序
     */
    @TableField("t_order")
    private Integer tOrder;

    /**
     * 状态
     */
    @TableField("t_status")
    private Integer tStatus;


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

    public String gettTypeName() {
        return tTypeName;
    }

    public void settTypeName(String tTypeName) {
        this.tTypeName = tTypeName;
    }

    public Integer gettOrder() {
        return tOrder;
    }

    public void settOrder(Integer tOrder) {
        this.tOrder = tOrder;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Override
    public String toString() {
        return "Type{" +
        "rowGuid=" + rowGuid +
        ", groupGuid=" + groupGuid +
        ", id=" + id +
        ", tTypeName=" + tTypeName +
        ", tOrder=" + tOrder +
        ", tStatus=" + tStatus +
        "}";
    }
}
