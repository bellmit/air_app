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
@TableName("tb_city_dic")
public class CityDic implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 城市代码
     */
    @TableField("CityNum")
    private Integer CityNum;

    /**
     * 城市名
     */
    @TableField("CityName")
    private String CityName;

    /**
     * 行政区划代码
     */
    @TableField("Xzqdm")
    private Integer Xzqdm;

    /**
     * 区县代码
     */
    @TableField("AdminAreaNum")
    private Integer AdminAreaNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityNum() {
        return CityNum;
    }

    public void setCityNum(Integer CityNum) {
        this.CityNum = CityNum;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public Integer getXzqdm() {
        return Xzqdm;
    }

    public void setXzqdm(Integer Xzqdm) {
        this.Xzqdm = Xzqdm;
    }

    public Integer getAdminAreaNum() {
        return AdminAreaNum;
    }

    public void setAdminAreaNum(Integer AdminAreaNum) {
        this.AdminAreaNum = AdminAreaNum;
    }

    @Override
    public String toString() {
        return "CityDic{" +
        "id=" + id +
        ", CityNum=" + CityNum +
        ", CityName=" + CityName +
        ", Xzqdm=" + Xzqdm +
        ", AdminAreaNum=" + AdminAreaNum +
        "}";
    }
}
