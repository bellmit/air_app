package cn.stylefeng.guns.pojos;

import lombok.Data;

public class AddressXz {

    private Integer AdminAreaNum;// 区代码
    private Integer CityNum;//市代码
    private Integer Xzqdm;// 省代码
    private String AreaName;// 区
    private String CityName;// 市
    private String StateName;// 省

    public Integer getAdminAreaNum() {
        return AdminAreaNum;
    }

    public void setAdminAreaNum(Integer adminAreaNum) {
        AdminAreaNum = adminAreaNum;
    }

    public Integer getCityNum() {
        return CityNum;
    }

    public void setCityNum(Integer cityNum) {
        CityNum = cityNum;
    }

    public Integer getXzqdm() {
        return Xzqdm;
    }

    public void setXzqdm(Integer xzqdm) {
        Xzqdm = xzqdm;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String areaName) {
        AreaName = areaName;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }
}
