package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AppOrderDao extends BaseMapper<Order> {

    @Select("SELECT o.row_guid,o.t_user_guid,o.t_course_guid,o.t_order_no,o.t_accept_address,o.t_price," +
            "o.t_class_package_guid,o.t_pay_date,o.t_pay_way,o.t_order_status, o.t_shop_no " +
            ",o.t_accept_phone,o.t_class_package_guid\n" +
            "FROM tb_order o WHERE o.t_order_no=#{t_order_no}")
    @Results({
            @Result(column = "row_guid",property = "rowGuid"),
            @Result(column = "t_user_guid",property = "tUserGuid"),
            @Result(column = "t_course_guid",property = "tCourseGuid"),
            @Result(column = "t_order_no",property = "tOrderNo"),
            @Result(column = "t_accept_address",property = "tAcceptAddress"),
            @Result(column = "t_price",property = "tPrice"),
            @Result(column = "t_class_package_guid",property = "tClassPackageGuid"),
            @Result(column = "t_pay_date",property = "tPayDate"),
            @Result(column = "t_pay_way",property = "tPayWay"),
            @Result(column = "t_order_status",property = "tOrderStatus"),
            @Result(column = "t_shop_no",property = "tShopNo"),
            @Result(column = "t_accept_phone",property = "tAcceptPhone")
    })
    Order findOrder(@Param("t_order_no") String t_order_no);

    @Select("SELECT o.row_guid,o.t_user_guid,o.t_course_guid,o.t_order_no,o.t_accept_address,o.t_price," +
            "o.t_class_package_guid,o.t_pay_date,o.t_pay_way,o.t_order_status, o.t_shop_no, o.t_place_order_date " +
            ",o.t_accept_phone,o.t_class_package_guid\n" +
            "FROM tb_order o")
    @Results({
            @Result(column = "row_guid",property = "rowGuid"),
            @Result(column = "t_user_guid",property = "tUserGuid"),
            @Result(column = "t_course_guid",property = "tCourseGuid"),
            @Result(column = "t_order_no",property = "tOrderNo"),
            @Result(column = "t_accept_address",property = "tAcceptAddress"),
            @Result(column = "t_price",property = "tPrice"),
            @Result(column = "t_class_package_guid",property = "tClassPackageGuid"),
            @Result(column = "t_pay_date",property = "tPayDate"),
            @Result(column = "t_pay_way",property = "tPayWay"),
            @Result(column = "t_order_status",property = "tOrderStatus"),
            @Result(column = "t_shop_no",property = "tShopNo"),
            @Result(column = "t_place_order_date",property = "tPlaceOrderDate"),
            @Result(column = "t_accept_phone",property = "tAcceptPhone")
    })
    List<Order> findOrderList();

    @Update("update tb_order set t_order_status=#{tOrderStatus},t_pay_way=#{tPayWay},t_pay_date=#{tPayDate} where row_guid=#{rowGuid}")
    void updateByRowGuid(Order order1);

    @Update("update tb_order set t_order_status=3 where row_guid=#{rowGuid}")
    void updateOrderStatus(@Param("rowGuid") String rowGuid);

    // 修改支付方式 支付日期 订单状态
    @Update("UPDATE tb_order SET t_pay_way=#{tPayWay}, t_pay_date=#{tPayDate}, t_order_status=#{tOrderStatus} " +
            "WHERE t_order_no=#{tOrderNo}")
    void updateByOutOrderNo(Order order);

    // 取消订单 订单状态 1待支付 2已支付 3已取消
    @Update("UPDATE tb_order SET t_order_status=3 WHERE t_order_no=#{tOrderNo}")
    void updateCancelOrderNo(@Param("tOrderNo") String tOrderNo);

}
