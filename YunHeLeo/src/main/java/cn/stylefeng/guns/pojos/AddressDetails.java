package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

public class AddressDetails {

    private String id;
    private String AreaName;// 区
    private Integer tAreaDic;// 区 编码
    private String CityName;// 市
    private Integer tCityDic;// 市 编码
    private String StateName;// 省
    private Integer tProvinceDic;// 省 编码

    private String tMobile;

    /**
     * 收货人
     */
    private String tUsername;

    /**
     * 详细地址
     */
    private String tAddress;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public Integer gettAreaDic() {
        return tAreaDic;
    }

    public void settAreaDic(Integer tAreaDic) {
        this.tAreaDic = tAreaDic;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public Integer gettCityDic() {
        return tCityDic;
    }

    public void settCityDic(Integer tCityDic) {
        this.tCityDic = tCityDic;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public Integer gettProvinceDic() {
        return tProvinceDic;
    }

    public void settProvinceDic(Integer tProvinceDic) {
        this.tProvinceDic = tProvinceDic;
    }

    public String gettMobile() {
        return tMobile;
    }

    public void settMobile(String tMobile) {
        this.tMobile = tMobile;
    }

    public String gettUsername() {
        return tUsername;
    }

    public void settUsername(String tUsername) {
        this.tUsername = tUsername;
    }

    public String gettAddress() {
        return tAddress;
    }

    public void settAddress(String tAddress) {
        this.tAddress = tAddress;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
