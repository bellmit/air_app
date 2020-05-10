package cn.stylefeng.guns.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AliyunVideoResult {

    private boolean success;//是否成功
    private String url;//返回信息
    private Object data;// 返回数据

    public AliyunVideoResult(boolean success, String url, Object data) {
        this.success = success;
        this.url = url;
        this.data = data;
    }
}
