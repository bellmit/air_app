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
 * @since 2020-03-10
 */
@TableName("tb_state_dic")
public class StateDic implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 省份代码
     */
    @TableField("StateNum")
    private Integer StateNum;

    /**
     * 省份
     */
    @TableField("StateName")
    private String StateName;

    /**
     * 行政区划代码
     */
    @TableField("Xzqdm")
    private Integer Xzqdm;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStateNum() {
        return StateNum;
    }

    public void setStateNum(Integer StateNum) {
        this.StateNum = StateNum;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String StateName) {
        this.StateName = StateName;
    }

    public Integer getXzqdm() {
        return Xzqdm;
    }

    public void setXzqdm(Integer Xzqdm) {
        this.Xzqdm = Xzqdm;
    }

    @Override
    public String toString() {
        return "StateDic{" +
        "id=" + id +
        ", StateNum=" + StateNum +
        ", StateName=" + StateName +
        ", Xzqdm=" + Xzqdm +
        "}";
    }
}
