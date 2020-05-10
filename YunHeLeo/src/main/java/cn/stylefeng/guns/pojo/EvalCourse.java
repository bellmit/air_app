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
 * @since 2020-03-07
 */
@TableName("tb_eval_course")
public class EvalCourse implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("recordguid")
    private String recordguid;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户GUID
     */
    @TableField("t_user_guid")
    private String tUserGuid;

    /**
     * 评价内容
     */
    @TableField("t_content")
    private String tContent;

    @TableField("t_label_id")
    private String tLabelId;

    @TableField("t_score")
    private Integer tScore;

    /**
     * 修改日期
     */
    @TableField("t_update_date")
    private Date tUpdateDate;

    @TableField("course_guid")
    private String courseGuid;

    public String getCourseGuid() {
        return courseGuid;
    }

    public void setCourseGuid(String courseGuid) {
        this.courseGuid = courseGuid;
    }

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

    public String gettUserGuid() {
        return tUserGuid;
    }

    public void settUserGuid(String tUserGuid) {
        this.tUserGuid = tUserGuid;
    }

    public String gettContent() {
        return tContent;
    }

    public void settContent(String tContent) {
        this.tContent = tContent;
    }

    public String gettLabelId() {
        return tLabelId;
    }

    public void settLabelId(String tLabelId) {
        this.tLabelId = tLabelId;
    }

    public Integer gettScore() {
        return tScore;
    }

    public void settScore(Integer tScore) {
        this.tScore = tScore;
    }

    public Date gettUpdateDate() {
        return tUpdateDate;
    }

    public void settUpdateDate(Date tUpdateDate) {
        this.tUpdateDate = tUpdateDate;
    }

    @Override
    public String toString() {
        return "EvalCourse{" +
        "rowGuid=" + rowGuid +
        ", recordguid=" + recordguid +
        ", id=" + id +
        ", tUserGuid=" + tUserGuid +
        ", tContent=" + tContent +
        ", tLabelId=" + tLabelId +
        ", tScore=" + tScore +
        ", tUpdateDate=" + tUpdateDate +
        "}";
    }
}
