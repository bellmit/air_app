package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.MyOrderResponse;
import cn.stylefeng.guns.pojo.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyOrderResponseDao {

    /**
     * 根据订单号查询订单基本信息
     * @param rowguid
     * @return
     */
    @Select("SELECT o.row_guid, o.t_order_no, o.t_user_guid, o.t_pay_way, o.prepay_id ,o.`t_price`, o.t_order_status \n" +
            "FROM tb_order o WHERE o.t_order_no=#{rowguid}")
    @Results({
            @Result(column = "row_guid",property = "rowGuid"),
            @Result(column = "t_pay_way",property = "tPayWay"),
            @Result(column = "t_order_no",property = "tOrderNo"),
            @Result(column = "prepay_id",property = "prepayId"),
            @Result(column = "t_user_guid",property = "tUserGuid"),
            @Result(column = "t_price",property = "tPrice"),
            @Result(column = "t_order_status",property = "tOrderStatus")
    })
    Order findByOrderNo(@Param("rowguid") String rowguid);

    /**
     * -- 订单基本信息
     */
    @Select("SELECT o.row_guid, o.t_order_no, UNIX_TIMESTAMP( o.`t_place_order_date`) t_place_order_date ,o.`t_price`, o.t_order_status \n" +
            "FROM tb_order o WHERE o.t_user_guid=#{userguid} ORDER BY t_place_order_date DESC ")
    @Results({
            @Result(column = "row_guid",property = "orderRowguid"),
            @Result(column = "t_order_no",property = "orderNo"),
            @Result(column = "t_place_order_date",property = "orderPlaceDate"),
            @Result(column = "t_price",property = "orderTotalPrice"),
            @Result(column = "t_order_status",property = "orderStatus")
    })
    List<MyOrderResponse> findOrderAll(@Param("userguid") String userguid);

    /**
     * 根据订单号查询订单基本信息
     * @param rowguid
     * @return
     */
    @Select("SELECT o.row_guid, o.t_order_no, o.t_user_guid, o.t_pay_way, " +
            "UNIX_TIMESTAMP( o.`t_place_order_date`) t_place_order_date ,o.`t_price`, " +
            "o.t_order_status,o.t_accept_address,o.t_accept_people,o.t_accept_phone \n" +
            "FROM tb_order o WHERE o.t_order_no=#{rowguid}")
    @Results({
            @Result(column = "row_guid",property = "orderRowguid"),
            @Result(column = "t_pay_way",property = "orderPayWay"),
            @Result(column = "t_order_no",property = "orderNo"),
            @Result(column = "t_user_guid",property = "orderUserGuid"),
            @Result(column = "t_place_order_date",property = "orderPlaceDate"),
            @Result(column = "t_price",property = "orderTotalPrice"),
            @Result(column = "t_accept_address",property = "acceptAddress"),
            @Result(column = "t_accept_people",property = "acceptPeople"),
            @Result(column = "t_accept_phone",property = "acceptPhone"),
            @Result(column = "t_order_status",property = "orderStatus")
    })
    MyOrderResponse findOrderAllRowguid(@Param("rowguid") String rowguid);

    /**
     * 根据订单号查询课程名称 课程rowguid
     */
    @Select("SELECT c.`row_guid` courseRowguid,c.t_course_name,c.t_img_url,ct.`t_class_name`,t.`t_type_name`,c.`t_class_type_id`\n" +
            "FROM tb_course c, tb_course_type ct, tb_order o,tb_type t WHERE o.t_order_no=#{orderRowguid} \n" +
            "AND ct.id=c.t_class_type_id AND c.row_guid=o.t_course_guid AND t.id=c.`t_course_type_id`")
    @Results({
            @Result(column = "courseRowguid",property = "orderCourseRowguid"),
            @Result(column = "t_img_url",property = "imgUrl"),
            @Result(column = "t_course_name",property = "orderCourseName"),
            @Result(column = "t_class_name",property = "orderClassType"),
            @Result(column = "t_class_type_id",property = "orderClassTypeId"),
            @Result(column = "t_type_name",property = "orderCourseType")
    })
    MyOrderResponse findOrderNoCourseInfo(@Param("orderRowguid") String orderRowguid);

    /**
     * 根据订单rowguid查询课程名称 课程rowguid
     */
    @Select("SELECT c.`row_guid` courseRowguid,c.t_course_name,c.t_img_url,ct.`t_class_name`,t.`t_type_name`,c.`t_class_type_id`\n" +
            "FROM tb_course c, tb_course_type ct, tb_order o,tb_type t WHERE o.row_guid=#{orderRowguid} \n" +
            "AND ct.id=c.t_class_type_id AND c.row_guid=o.t_course_guid AND t.id=c.`t_course_type_id`")
    @Results({
            @Result(column = "courseRowguid",property = "orderCourseRowguid"),
            @Result(column = "t_img_url",property = "imgUrl"),
            @Result(column = "t_course_name",property = "orderCourseName"),
            @Result(column = "t_class_name",property = "orderClassType"),
            @Result(column = "t_class_type_id",property = "orderClassTypeId"),
            @Result(column = "t_type_name",property = "orderCourseType")
    })
    MyOrderResponse findOrderCourseInfo(@Param("orderRowguid") String orderRowguid);

    /**
     * 根据订单号查询课包价格、名称
     */
    @Select("SELECT o.row_guid orderRowguid,cp.row_guid packageRowguid,cp.t_name packageName,cp.t_price,cp.t_activate_date,cp.t_study_date\n" +
            "FROM  tb_order o LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.row_guid,o.t_class_package_guid)\n" +
            "WHERE o.t_order_no=#{orderRowguid}")
    @Results({
            @Result(column = "row_guid",property = "orderRowguid"),
            @Result(column = "packageRowguid",property = "orderPackageRowguid"),
            @Result(column = "packageName",property = "orderPackageName"),
            @Result(column = "t_activate_date",property = "activateDate"),
            @Result(column = "t_study_date",property = "studyDate"),
            @Result(column = "t_price",property = "orderPackagePrice")
    })
    List<MyOrderResponse> findOrderNoCoursePackageInfo(@Param("orderRowguid") String orderRowguid);

    /**
     * 根据订单rowguid查询课包价格、名称
     */
    @Select("SELECT o.row_guid orderRowguid,cp.row_guid packageRowguid,cp.t_name packageName,cp.t_price,cp.t_activate_date,cp.t_study_date\n" +
            "FROM  tb_order o LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.row_guid,o.t_class_package_guid)\n" +
            "WHERE o.row_guid=#{orderRowguid}")
    @Results({
            @Result(column = "row_guid",property = "orderRowguid"),
            @Result(column = "packageRowguid",property = "orderPackageRowguid"),
            @Result(column = "packageName",property = "orderPackageName"),
            @Result(column = "t_activate_date",property = "activateDate"),
            @Result(column = "t_study_date",property = "studyDate"),
            @Result(column = "t_price",property = "orderPackagePrice")
    })
    List<MyOrderResponse> findCoursePackageInfo(@Param("orderRowguid") String orderRowguid);

    /**
     * 根据课包row_guid查询阶段
     */
    @Select("SELECT CONVERT(GROUP_CONCAT(s.t_name) USING utf8) stageName\n" +
            "FROM tb_course_package cp LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,cp.t_stage_id)\n" +
            "WHERE cp.row_guid=#{orderPackageRowguid} GROUP BY cp.id")
    @Results({
            @Result(column = "stageName",property = "orderStageName")
    })
    MyOrderResponse findCoursePackageStageInfo(@Param("orderPackageRowguid") String orderPackageRowguid);

    /**
     * 查询订单课程是否已加入课表
     */
    @Select("SELECT t_course_guid FROM `tb_timetable` t WHERE t.`t_course_guid`=#{courseGuid} " +
            "AND `user_guid`=#{userGuid} LIMIT 1")
    @Results({
            @Result(column = "t_course_guid",property = "orderCourseRowguid")
    })
    MyOrderResponse isCourseTimeTable(@Param("courseGuid") String courseGuid, @Param("userGuid") String userGuid);

}
