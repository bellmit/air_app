package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

public class Evaluate {

    private Integer id;
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

    /**
     * 标签类型名称
     */
    @TableField("t_label_name")
    private String tLabelName;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String tUsername;

    /**
     * 课程名称
     */
    @TableField("t_course_name")
    private String tCourseName;

    /**
     * 手机号
     */
    @TableField("t_mobile")
    private String tMobile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String gettLabelName() {
        return tLabelName;
    }

    public void settLabelName(String tLabelName) {
        this.tLabelName = tLabelName;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile;
    }
}
