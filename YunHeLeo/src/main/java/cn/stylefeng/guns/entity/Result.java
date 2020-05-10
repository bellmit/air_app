package cn.stylefeng.guns.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据响应对象
 * {
 * success ：是否成功
 * code    ：返回码
 * message ：返回信息
 * //返回数据
 * data：  ：{
 * <p>
 * }
 * }
 */
//@Data
//@NoArgsConstructor
public class Result {

    private boolean success;//是否成功
    private Integer meta;// 返回码
    private String message;//返回信息
    private Object data = null;// 返回数据
    private Object jwt;
    private int total;
    private Object userInfo;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getMeta() {
        return meta;
    }

    public void setMeta(Integer meta) {
        this.meta = meta;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getJwt() {
        return jwt;
    }

    public void setJwt(Object jwt) {
        this.jwt = jwt;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(Object userInfo) {
        this.userInfo = userInfo;
    }

    public Result(boolean success, Integer meta, String message) {
        this.success = success;
        this.meta = meta;
        this.message = message;
    }

    public Result(boolean success, Integer meta, String message, Object data, Object jwt, Object userInfo) {
        this.success = success;
        this.meta = meta;
        this.message = message;
        this.data = data;
        this.jwt = jwt;
        this.userInfo = userInfo;
    }

    public Result(boolean success, String message, Object data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Result(boolean success, String message, Object data, Object jwt) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.jwt = jwt;
    }

    public Result(boolean success, Integer meta, String message, Object data, Object jwt) {
        this.success = success;
        this.meta = meta;
        this.message = message;
        this.data = data;
        this.jwt = jwt;
    }

    public Result(Object data, boolean success, Integer meta, String message) {
        this.data = data;
        this.success = success;
        this.meta = meta;
        this.message = message;
    }

    public Result(boolean success, Integer meta, String message, Object data) {
        this.success = success;
        this.meta = meta;
        this.message = message;
        this.data = data;
    }

    public Result(boolean success, Integer meta, int total, String message, Object data) {
        this.success = success;
        this.meta = meta;
        this.message = message;
        this.data = data;
        this.total = total;
    }

    public Result(ResultCode code) {
        this.success = code.success;
        this.meta = code.code;
        this.message = code.message;
    }

    public Result(ResultCode code, Object data) {
        this.success = code.success;
        this.meta = code.code;
        this.message = code.message;
        this.data = data;
    }

    public Result(Integer code, String message, boolean success) {
        this.meta = code;
        this.message = message;
        this.success = success;
    }

    public static Result SUCCESS() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result ERROR() {
        return new Result(ResultCode.SERVER_ERROR);
    }

    public static Result FAIL() {
        return new Result(ResultCode.FAIL);
    }

    public static Result FAIL(String msg) {
        return new Result(false, ResultStatusCode.FAIL, msg);
    }

    public static Result SUCCESS(String tips, Object data) {
        return new Result(true, 0, tips, data);
    }
}
