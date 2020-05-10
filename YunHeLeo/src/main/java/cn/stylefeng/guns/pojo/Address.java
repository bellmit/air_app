package cn.stylefeng.guns.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-10
 */
@TableName("tb_address")
@Data
public class Address implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("id")
    private String id;

    /**
     * 用户id
     */
    @TableField("t_user_id")
    private String tUserId;

    @TableField("t_mobile")
    private String tMobile;

    /**
     * 收货人
     */
    @TableField("t_username")
    private String tUsername;

    /**
     * 省份
     */
    @TableField("t_province_dic")
    private Long tProvinceDic;

    /**
     * 城市
     */
    @TableField("t_city_dic")
    private Long tCityDic;

    /**
     * 区县
     */
    @TableField("t_area_dic")
    private Long tAreaDic;

    /**
     * 详细地址
     */
    @TableField("t_address")
    private String tAddress;

    private Date createDate;

}
