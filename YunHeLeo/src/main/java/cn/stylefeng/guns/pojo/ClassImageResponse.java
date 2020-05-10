package cn.stylefeng.guns.pojo;

import lombok.Data;

public class ClassImageResponse {

    private String classrowguid;
    private String tClassName;
    private String tImgUrl;

    public String getClassrowguid() {
        return classrowguid;
    }

    public void setClassrowguid(String classrowguid) {
        this.classrowguid = classrowguid;
    }

    public String gettClassName() {
        return tClassName;
    }

    public void settClassName(String tClassName) {
        this.tClassName = tClassName;
    }

    public String gettImgUrl() {
        return tImgUrl;
    }

    public void settImgUrl(String tImgUrl) {
        this.tImgUrl = tImgUrl;
    }
}
