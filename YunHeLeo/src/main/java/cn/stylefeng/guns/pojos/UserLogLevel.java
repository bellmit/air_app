package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

public class UserLogLevel {

    private String rowGuid;

    private Integer id;

    /**
     * 用户名
     */
    @TableField("t_username")
    private String tUsername;

    /**
     * 手机号
     */
    @TableField("t_mobile")
    private String tMobile;

    /**
     * 性别
     */
    @TableField("t_sex")
    private String tSex;

    /**
     * 注册日期
     */
    @TableField("t_register_date")
    private Date tRegisterDate;

    /**
     * 注册IP
     */
    @TableField("t_register_ip")
    private String tRegisterIp;

    /**
     * 年龄
     */
    @TableField("t_age")
    private Integer tAge;

    /**
     * 登录日期
     */
    @TableField("t_login_date")
    private Date tLoginDate;

    /**
     * 登录IP
     */
    @TableField("t_login_ip")
    private String tLoginIp;

    private String tLevelName;

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile;
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex;
    }

    public Date gettRegisterDate() {
        return tRegisterDate;
    }

    public void settRegisterDate(Date tRegisterDate) {
        this.tRegisterDate = tRegisterDate;
    }

    public String gettRegisterIp() {
        return tRegisterIp;
    }

    public void settRegisterIp(String tRegisterIp) {
        this.tRegisterIp = tRegisterIp;
    }

    public Integer gettAge() {
        return tAge;
    }

    public void settAge(Integer tAge) {
        this.tAge = tAge;
    }

    public Date gettLoginDate() {
        return tLoginDate;
    }

    public void settLoginDate(Date tLoginDate) {
        this.tLoginDate = tLoginDate;
    }

    public String gettLoginIp() {
        return tLoginIp;
    }

    public void settLoginIp(String tLoginIp) {
        this.tLoginIp = tLoginIp;
    }

    public String gettLevelName() {
        return tLevelName;
    }

    public void settLevelName(String tLevelName) {
        this.tLevelName = tLevelName;
    }
}
