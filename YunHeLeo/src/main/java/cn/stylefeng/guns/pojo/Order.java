package cn.stylefeng.guns.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2020-03-13
 */
@TableName("tb_order")
public class Order implements Serializable {

    private static final long serialVersionUID=1L;

    @TableField("row_guid")
    private String rowGuid;

    @TableField("recordguid")
    private String recordguid;

      @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 课程GUID
     */
    @TableField("t_course_guid")
    private String tCourseGuid;

    /**
     * 用户GUID
     */
    @TableField("t_user_guid")
    private String tUserGuid;

    /**
     * 课包GUID
     */
    @TableField("t_class_package_guid")
    private String tClassPackageGuid;

    /**
     * 订单号
     */
    @TableField("t_order_no")
    private String tOrderNo;

    /**
     * 订单状态 1待支付 2已支付 3已取消
     */
    @TableField("t_order_status")
    private Integer tOrderStatus;

    /**
     * 支付方式
     */
    @TableField("t_pay_way")
    private String tPayWay;

    /**
     * 订单金额
     */
    @TableField("t_price")
    private Double tPrice;

    /**
     * 支付时间
     */
    @TableField("t_pay_date")
    private Date tPayDate;

    /**
     * 商户订单号
     */
    @TableField("t_shop_no")
    private String tShopNo;

    /**
     * 下单时间
     */
    @TableField("t_place_order_date")
    private Date tPlaceOrderDate;

    /**
     * 收货地址
     */
    @TableField("t_accept_address")
    private String tAcceptAddress;

    /**
     * 收货人
     */
    @TableField("t_accept_people")
    private String tAcceptPeople;

    /**
     * 联系手机
     */
    @TableField("t_accept_phone")
    private String tAcceptPhone;

    @TableField("prepay_id")
    private String prepayId;

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getRowGuid() {
        return rowGuid;
    }

    public void setRowGuid(String rowGuid) {
        this.rowGuid = rowGuid;
    }

    public String getRecordguid() {
        return recordguid;
    }

    public void setRecordguid(String recordguid) {
        this.recordguid = recordguid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettCourseGuid() {
        return tCourseGuid;
    }

    public void settCourseGuid(String tCourseGuid) {
        this.tCourseGuid = tCourseGuid;
    }

    public String gettUserGuid() {
        return tUserGuid;
    }

    public void settUserGuid(String tUserGuid) {
        this.tUserGuid = tUserGuid;
    }

    public String gettClassPackageGuid() {
        return tClassPackageGuid;
    }

    public void settClassPackageGuid(String tClassPackageGuid) {
        this.tClassPackageGuid = tClassPackageGuid;
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

    public Double gettPrice() {
        return tPrice;
    }

    public void settPrice(Double tPrice) {
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

    @Override
    public String toString() {
        return "Order{" +
        "rowGuid=" + rowGuid +
        ", recordguid=" + recordguid +
        ", id=" + id +
        ", tCourseGuid=" + tCourseGuid +
        ", tUserGuid=" + tUserGuid +
        ", tClassPackageGuid=" + tClassPackageGuid +
        ", tOrderNo=" + tOrderNo +
        ", tOrderStatus=" + tOrderStatus +
        ", tPayWay=" + tPayWay +
        ", tPrice=" + tPrice +
        ", tPayDate=" + tPayDate +
        ", tShopNo=" + tShopNo +
        ", tPlaceOrderDate=" + tPlaceOrderDate +
        ", tAcceptAddress=" + tAcceptAddress +
        ", tAcceptPeople=" + tAcceptPeople +
        ", tAcceptPhone=" + tAcceptPhone +
        "}";
    }
}
