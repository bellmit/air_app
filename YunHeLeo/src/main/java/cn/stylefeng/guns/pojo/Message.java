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
 * @since 2020-03-02
 */
@TableName("tb_message")
public class Message implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("recordguid")
    private String recordguid;

      @TableId(value = "id", type = IdType.ID_WORKER)
    private String id;

    /**
     * 消息标题
     */
    @TableField("t_message_title")
    private String tMessageTitle;

    /**
     * 消息内容
     */
    @TableField("t_message_content")
    private String tMessageContent;

    /**
     * 修改人
     */
    @TableField("t_update_man")
    private String tUpdateMan;

    /**
     * 修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    /**
     * 推送日期
     */
    @TableField("t_send_date")
    private Date tSendDate;

    /**
     * 是否推送:1是2否
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gettMessageTitle() {
        return tMessageTitle;
    }

    public void settMessageTitle(String tMessageTitle) {
        this.tMessageTitle = tMessageTitle;
    }

    public String gettMessageContent() {
        return tMessageContent;
    }

    public void settMessageContent(String tMessageContent) {
        this.tMessageContent = tMessageContent;
    }

    public String gettUpdateMan() {
        return tUpdateMan;
    }

    public void settUpdateMan(String tUpdateMan) {
        this.tUpdateMan = tUpdateMan;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    public Date gettSendDate() {
        return tSendDate;
    }

    public void settSendDate(Date tSendDate) {
        this.tSendDate = tSendDate;
    }

    public Integer gettStatus() {
        return tStatus;
    }

    public void settStatus(Integer tStatus) {
        this.tStatus = tStatus;
    }

    @Override
    public String toString() {
        return "Message{" +
        "rowGuid=" + rowGuid +
        ", recordguid=" + recordguid +
        ", id=" + id +
        ", tMessageTitle=" + tMessageTitle +
        ", tMessageContent=" + tMessageContent +
        ", tUpdateMan=" + tUpdateMan +
        ", tUpdateDate=" + tUpdateDate +
        ", tSendDate=" + tSendDate +
        ", tStatus=" + tStatus +
        "}";
    }
}
