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
@TableName("tb_admin_area_dic")
public class AdminAreaDic implements Serializable {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 区县代码
     */
    @TableField("AdminAreaNum")
    private Integer AdminAreaNum;

    /**
     * 区县名称
     */
    @TableField("CityName")
    private String CityName;

    /**
     * 区县手机代码
     */
    @TableField("AreaPhoneCode")
    private Integer AreaPhoneCode;

    /**
     * 区县关联城市代码
     */
    @TableField("ParentNodeNum")
    private Integer ParentNodeNum;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminAreaNum() {
        return AdminAreaNum;
    }

    public void setAdminAreaNum(Integer AdminAreaNum) {
        this.AdminAreaNum = AdminAreaNum;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    public Integer getAreaPhoneCode() {
        return AreaPhoneCode;
    }

    public void setAreaPhoneCode(Integer AreaPhoneCode) {
        this.AreaPhoneCode = AreaPhoneCode;
    }

    public Integer getParentNodeNum() {
        return ParentNodeNum;
    }

    public void setParentNodeNum(Integer ParentNodeNum) {
        this.ParentNodeNum = ParentNodeNum;
    }

    @Override
    public String toString() {
        return "AdminAreaDic{" +
        "id=" + id +
        ", AdminAreaNum=" + AdminAreaNum +
        ", CityName=" + CityName +
        ", AreaPhoneCode=" + AreaPhoneCode +
        ", ParentNodeNum=" + ParentNodeNum +
        "}";
    }
}
