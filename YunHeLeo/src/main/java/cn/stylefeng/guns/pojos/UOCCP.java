package cn.stylefeng.guns.pojos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

public class UOCCP {

    private String rowGuid;

    private String groupGuid;

    /**
     * 主键
     */
    private Integer id;

    /**
     * 用户名
     */
    private String tUsername;

    /**
     * 手机号
     */
    private String tMobile;

    /**
     * 课包
     */
    private String tName;


    /**
     * 所拥有的课程ID
     */
    private String tCourseId;

    /**
     * 订单号
     */
    private String tOrderNo;

    /**
     * 订单状态 1待支付 2已支付 3已取消
     */
    private Integer tOrderStatus;

    /**
     * 支付方式
     */
    private String tPayWay;

    /**
     * 订单金额
     */
    private BigDecimal tPrice;

    /**
     * 支付时间
     */
    private Date tPayDate;

    /**
     * 商户订单号
     */
    private String tShopNo;

    /**
     * 下单时间
     */
    private Date tPlaceOrderDate;

    /**
     * 收货地址
     */
    private String tAcceptAddress;

    /**
     * 收货人
     */
    private String tAcceptPeople;

    /**
     * 联系手机
     */
    private String tAcceptPhone;

    /**
     * 课程名称
     */
    private String tCourseName;

    private String packageName;

    private String classPackageGuid;

    private String stageName;

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getGroupGuid() {
        return groupGuid;
    }

    public void setGroupGuid(String groupGuid) {
        this.groupGuid = groupGuid;
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

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettCourseId() {
        return tCourseId;
    }

    public void settCourseId(String tCourseId) {
        this.tCourseId = tCourseId;
    }

    public String gettOrderNo() {
        return tOrderNo;
    }

    public void settOrderNo(String tOrderNo) {
        this.tOrderNo = tOrderNo;
    }

    public Integer gettOrderStatus() {
        return tOrderStatus;
    }

    public void settOrderStatus(Integer tOrderStatus) {
        this.tOrderStatus = tOrderStatus;
    }

    public String gettPayWay() {
        return tPayWay;
    }

    public void settPayWay(String tPayWay) {
        this.tPayWay = tPayWay;
    }

    public BigDecimal gettPrice() {
        return tPrice;
    }

    public void settPrice(BigDecimal tPrice) {
        this.tPrice = tPrice;
    }

    public Date gettPayDate() {
        return tPayDate;
    }

    public void settPayDate(Date tPayDate) {
        this.tPayDate = tPayDate;
    }

    public String gettShopNo() {
        return tShopNo;
    }

    public void settShopNo(String tShopNo) {
        this.tShopNo = tShopNo;
    }

    public Date gettPlaceOrderDate() {
        return tPlaceOrderDate;
    }

    public void settPlaceOrderDate(Date tPlaceOrderDate) {
        this.tPlaceOrderDate = tPlaceOrderDate;
    }

    public String gettAcceptAddress() {
        return tAcceptAddress;
    }

    public void settAcceptAddress(String tAcceptAddress) {
        this.tAcceptAddress = tAcceptAddress;
    }

    public String gettAcceptPeople() {
        return tAcceptPeople;
    }

    public void settAcceptPeople(String tAcceptPeople) {
        this.tAcceptPeople = tAcceptPeople;
    }

    public String gettAcceptPhone() {
        return tAcceptPhone;
    }

    public void settAcceptPhone(String tAcceptPhone) {
        this.tAcceptPhone = tAcceptPhone;
    }

    public String gettCourseName() {
        return tCourseName;
    }

    public void settCourseName(String tCourseName) {
        this.tCourseName = tCourseName;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassPackageGuid() {
        return classPackageGuid;
    }

    public void setClassPackageGuid(String classPackageGuid) {
        this.classPackageGuid = classPackageGuid;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
}
