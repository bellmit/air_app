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
 * @since 2020-03-09
 */
@TableName("tb_level")
public class Level implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("t_level_name")
    private String tLevelName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettLevelName() {
        return tLevelName;
    }

    public void settLevelName(String tLevelName) {
        this.tLevelName = tLevelName;
    }

    @Override
    public String toString() {
        return "Level{" +
        "id=" + id +
        ", tLevelName=" + tLevelName +
        "}";
    }
}
