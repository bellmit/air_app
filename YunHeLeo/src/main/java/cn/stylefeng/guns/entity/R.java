package cn.stylefeng.guns.entity;

import lombok.Data;

@Data
public class R {

    private boolean success;//是否成功
    private Integer meta;
    private String message;
    private Object data;// 返回数据

    public R(boolean success, Integer meta, String message, Object data) {
        this.success = success;
        this.meta = meta;
        this.message = message;
        this.data = data;
    }

    public R(boolean success, Integer meta) {
        this.success = success;
        this.meta = meta;
    }

    public R(boolean success, Integer meta, Object data) {
        this.success = success;
        this.meta = meta;
        this.data = data;
    }

    public R(ResultCode code) {
        this.success = code.success;
    }

    public R(ResultCode code, Object data) {
        this.success = code.success;
        this.data = data;
    }

    public static R SUCCESS() {
        return new R(ResultCode.SUCCESS);
    }

    public static R ERROR() {
        return new R(ResultCode.SERVER_ERROR);
    }

    public static R FAIL() {
        return new R(ResultCode.FAIL);
    }

    public static R SERVER_ERROR() {
        return new R(ResultCode.SERVER_ERROR);
    }
}
