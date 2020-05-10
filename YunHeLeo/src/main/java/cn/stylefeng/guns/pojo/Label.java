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
 * @since 2020-03-03
 */
@TableName("tb_label")
public class Label implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("recordguid")
    private String recordguid;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标签类型名称
     */
    @TableField("t_label_name")
    private String tLabelName;

    /**
     * 修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    /**
     * 修改人
     */
    @TableField("t_update_man")
    private String tUpdateMan;

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

    public String getRecordguid() {
        return recordguid;
    }

    public void setRecordguid(String recordguid) {
        this.recordguid = recordguid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettLabelName() {
        return tLabelName;
    }

    public void settLabelName(String tLabelName) {
        this.tLabelName = tLabelName;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public String gettUpdateMan() {
        return tUpdateMan;
    }

    public void settUpdateMan(String tUpdateMan) {
        this.tUpdateMan = tUpdateMan;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Override
    public String toString() {
        return "Label{" +
        "rowGuid=" + rowGuid +
        ", recordguid=" + recordguid +
        ", id=" + id +
        ", tLabelName=" + tLabelName +
        ", tUpdateDate=" + tUpdateDate +
        ", tUpdateMan=" + tUpdateMan +
        ", tStatus=" + tStatus +
        "}";
    }
}
