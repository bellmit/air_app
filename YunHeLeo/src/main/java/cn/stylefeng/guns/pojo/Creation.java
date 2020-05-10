package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

@TableName("tb_creation")
public class Creation implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("t_creation_name")
    private String tCreationName;

    @TableField("t_update_man")
    private String tUpdateMan;

    @TableField("t_update_date")
    private Date tUpdateDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettCreationName() {
        return tCreationName;
    }

    public void settCreationName(String tCreationName) {
        this.tCreationName = tCreationName;
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

    @Override
    public String toString() {
        return "Creation{" +
        "id=" + id +
        ", tCreationName=" + tCreationName +
        ", tUpdateMan=" + tUpdateMan +
        ", tUpdateDate=" + tUpdateDate +
        "}";
    }
}
