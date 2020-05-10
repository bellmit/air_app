package cn.stylefeng.guns.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyOrderResponse {

    private String orderRowguid;// 订单ROWGUID
    private String orderNo;// 订单号
    private String orderCourseName;// 课程名称
    private String orderClassType;// 订单的班级类型
    private String orderCourseType;// 订单的课程类型
    private String imgUrl;// 课程图片
    private String orderPayWay;// 支付方式
    private Boolean isTimeTable;// 是否加入课表
    private String acceptAddress;// 详细地址
    private String acceptPeople;// 收货人
    private String acceptPhone;// 收货人手机号
    @JSONField(serialize = false)
    private String orderUserGuid;// 用户guid
    @JSONField(serialize = false)
    private Integer activateDate;// 待激活时长
    @JSONField(serialize = false)
    private Integer studyDate;// 可学习时长
    @JSONField(serialize = false)
    private String orderStageName;// 包含的阶段
    @JSONField(serialize = false)
    private String orderPackageName;// 课包名称
    @JSONField(serialize = false)
    private String orderPackageRowguid;// 课包rowguid
    @JSONField(serialize = false)
    private Double orderPackagePrice;// 课包价格
    private Double orderTotalPrice;// 订单总价
    private String orderPlaceDate;// 下单时间
    private String orderCourseRowguid;// 课程rowguid
    private Integer orderClassTypeId;// 班级类型id
    private Integer orderStatus;// 订单状态 1待支付 2已支付 3已取消
    private Object packageList;// = new HashMap();// 课包集合

    public String getOrderRowguid() {
        return orderRowguid;
    }

    public void setOrderRowguid(String orderRowguid) {
        this.orderRowguid = orderRowguid;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderCourseName() {
        return orderCourseName;
    }

    public void setOrderCourseName(String orderCourseName) {
        this.orderCourseName = orderCourseName;
    }

    public String getOrderClassType() {
        return orderClassType;
    }

    public void setOrderClassType(String orderClassType) {
        this.orderClassType = orderClassType;
    }

    public String getOrderCourseType() {
        return orderCourseType;
    }

    public void setOrderCourseType(String orderCourseType) {
        this.orderCourseType = orderCourseType;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOrderPayWay() {
        return orderPayWay;
    }

    public void setOrderPayWay(String orderPayWay) {
        this.orderPayWay = orderPayWay;
    }

    public Boolean getIsTimeTable() {
        return isTimeTable;
    }

    public void setIsTimeTable(Boolean timeTable) {
        isTimeTable = timeTable;
    }

    public String getAcceptAddress() {
        return acceptAddress;
    }

    public void setAcceptAddress(String acceptAddress) {
        this.acceptAddress = acceptAddress;
    }

    public String getAcceptPeople() {
        return acceptPeople;
    }

    public void setAcceptPeople(String acceptPeople) {
        this.acceptPeople = acceptPeople;
    }

    public String getAcceptPhone() {
        return acceptPhone;
    }

    public void setAcceptPhone(String acceptPhone) {
        this.acceptPhone = acceptPhone;
    }

    public String getOrderUserGuid() {
        return orderUserGuid;
    }

    public void setOrderUserGuid(String orderUserGuid) {
        this.orderUserGuid = orderUserGuid;
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

    public String getOrderStageName() {
        return orderStageName;
    }

    public void setOrderStageName(String orderStageName) {
        this.orderStageName = orderStageName;
    }

    public String getOrderPackageName() {
        return orderPackageName;
    }

    public void setOrderPackageName(String orderPackageName) {
        this.orderPackageName = orderPackageName;
    }

    public String getOrderPackageRowguid() {
        return orderPackageRowguid;
    }

    public void setOrderPackageRowguid(String orderPackageRowguid) {
        this.orderPackageRowguid = orderPackageRowguid;
    }

    public Double getOrderPackagePrice() {
        return orderPackagePrice;
    }

    public void setOrderPackagePrice(Double orderPackagePrice) {
        this.orderPackagePrice = orderPackagePrice;
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOrderPlaceDate() {
        return orderPlaceDate;
    }

    public void setOrderPlaceDate(String orderPlaceDate) {
        this.orderPlaceDate = orderPlaceDate;
    }

    public String getOrderCourseRowguid() {
        return orderCourseRowguid;
    }

    public void setOrderCourseRowguid(String orderCourseRowguid) {
        this.orderCourseRowguid = orderCourseRowguid;
    }

    public Integer getOrderClassTypeId() {
        return orderClassTypeId;
    }

    public void setOrderClassTypeId(Integer orderClassTypeId) {
        this.orderClassTypeId = orderClassTypeId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Object getPackageList() {
        return packageList;
    }

    public void setPackageList(Object packageList) {
        this.packageList = packageList;
    }
}
