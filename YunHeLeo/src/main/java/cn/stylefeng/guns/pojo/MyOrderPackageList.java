package cn.stylefeng.guns.pojo;

import lombok.Data;

public class MyOrderPackageList {

    private String packageRowguid;
    private String packageStageName;
    private String packageName;
    private Double packagePrice;
    private Integer activateDate;// 待激活时长
    private Integer studyDate;// 可学习时长

    public String getPackageRowguid() {
        return packageRowguid;
    }

    public void setPackageRowguid(String packageRowguid) {
        this.packageRowguid = packageRowguid;
    }

    public String getPackageStageName() {
        return packageStageName;
    }

    public void setPackageStageName(String packageStageName) {
        this.packageStageName = packageStageName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Double getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(Double packagePrice) {
        this.packagePrice = packagePrice;
    }

    public Integer getActivateDate() {
        return activateDate;
    }

    public void setActivateDate(Integer activateDate) {
        this.activateDate = activateDate;
    }

    public Integer getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(Integer studyDate) {
        this.studyDate = studyDate;
    }
}
