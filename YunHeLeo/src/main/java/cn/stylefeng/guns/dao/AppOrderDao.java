package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AppOrderDao extends BaseMapper<Order> {

    @Select("SELECT o.row_guid,o.t_course_guid,o.t_order_no,o.t_accept_address,o.t_price," +
            "o.t_class_package_guid,o.t_pay_date,o.t_pay_way,o.t_order_status, o.t_shop_no " +
            ",o.t_accept_phone,o.t_class_package_guid\n" +
            "FROM tb_order o WHERE o.t_order_no=#{t_order_no}")
    Order findOrder(@Param("t_order_no") String t_order_no);

    @Update("update tb_order set t_order_status=#{tOrderStatus},t_pay_way=#{tPayWay},t_pay_date=#{tPayDate} where row_guid=#{rowGuid}")
    void updateByRowGuid(Order order1);

    // 修改支付方式 支付日期 订单状态
    @Update("UPDATE tb_order SET t_pay_way=#{tPayWay}, t_pay_date=#{tPayDate}, t_order_status=#{tOrderStatus} " +
            "WHERE t_order_no=#{tOrderNo}")
    void updateByOutOrderNo(Order order);

    // 取消订单 订单状态 1待支付 2已支付 3已取消
    @Update("UPDATE tb_order SET t_order_status=3 WHERE t_order_no=#{tOrderNo}")
    void updateCancelOrderNo(@Param("tOrderNo") String tOrderNo);

}
