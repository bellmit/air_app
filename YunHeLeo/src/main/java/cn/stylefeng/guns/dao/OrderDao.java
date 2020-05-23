package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.UOCCP;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDao extends BaseMapper<UOCCP> {

    /**
     * 判断当前用户是否购买了该课程
     * 当前登录用户guid和课程guid
     */
    @Select("SELECT o.row_guid,o.t_order_no,t_class_package_guid FROM tb_order o WHERE o.t_user_guid=#{rowguid} " +
            " AND o.t_course_guid=#{courseGuid} AND o.t_order_status=2")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_class_package_guid", property = "classPackageGuid")
    })
    UOCCP isBuyCourse(@Param("rowguid") String rowguid, @Param("courseGuid") String courseGuid);

    @Select("SELECT cpu.`row_guid` FROM `tb_course_package_user` cpu\n" +
            "WHERE cpu.`user_guid`=#{userguid} AND cpu.`t_course_guid`=#{courseGuid} limit 1")
    String isBuyFreeCourse(@Param("userguid") String userguid, @Param("courseGuid") String courseGuid);

    /**
     * 判断当前长期课是否已购买所有课包
     * @param rowguid
     * @param courseGuid
     * @return
     */
    @Select("SELECT o.row_guid,o.t_order_no,o.t_class_package_guid FROM tb_order o WHERE o.t_user_guid=#{rowguid} " +
            " AND o.t_course_guid=#{courseGuid} AND o.t_order_status=2")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_class_package_guid", property = "classPackageGuid")
    })
    UOCCP isBuyCourseAndPackage(@Param("rowguid") String rowguid, @Param("courseGuid") String courseGuid);

    /*@Select("<script>" +
            "SELECT o.id,u.t_username,\n" +
            "\tu.t_mobile, c.t_course_name, o.t_pay_date, o.t_order_no, o.t_place_order_date,\n" +
            "\to.t_order_status, o.t_price, o.t_pay_way, cp.t_name \n" +
            "FROM tb_user u, tb_order o, tb_course c, tb_course_package cp \n" +
            "WHERE o.t_user_guid = u.id AND o.t_course_guid = c.id AND o.t_class_package_guid = cp.id" +
            " <if test='tUsername!=null and tUsername!=\"\"'> " +
            "     AND u.t_username LIKE '%${tUsername}%'" +
            " </if>" +
            " <if test='tMobile!=null and tMobile!=\"\"'> " +
            "    OR u.t_mobile = #{tMobile} " +
            " </if> " +
            " <if test='tOrderNo!=null and tOrderNo!=\"\"'> " +
            "    OR o.t_order_no = #{tOrderNo}" +
            " </if> " +
            "</script>")*/

    @Select("<script>" +
            "SELECT o.id,u.t_username,u.t_mobile, c.t_course_name, o.t_pay_date, o.t_order_no, o.t_place_order_date,\n" +
            "o.t_order_status, o.t_price, o.t_pay_way, cp.t_name\n" +
            "FROM tb_user u, tb_order o, tb_course c, tb_course_package cp\n" +
            "WHERE o.t_user_guid = u.row_guid AND o.t_course_guid = c.row_guid AND o.t_class_package_guid = cp.row_guid" +
            " <if test='tUsername!=null and tUsername!=\"\"'> " +
            "     AND u.t_username LIKE '%${tUsername}%'" +
            " </if>" +
            " <if test='tMobile!=null and tMobile!=\"\"'> " +
            "    OR u.t_mobile = #{tMobile} " +
            " </if> " +
            " <if test='tOrderNo!=null and tOrderNo!=\"\"'> " +
            "    OR o.t_order_no = #{tOrderNo}" +
            " </if> " +
            "</script>")
    Page<UOCCP> findAll(UOCCP uoccp);

    @Select("select count(*) from tb_order")
    int count();

    // 根据当前登录用户查询订单信息 包括课包、阶段
//    @Select("SELECT o.row_guid, o.t_order_no, o.t_price, o.t_place_order_date, o.t_order_status ,\n" +
//            " CONVERT(GROUP_CONCAT(DISTINCT cp.t_name) USING utf8) packageName,CONVERT(GROUP_CONCAT(DISTINCT s.t_name) USING utf8) stageName\n" +
//            "FROM tb_course c, tb_course_type ct, tb_order o LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.row_guid,o.t_class_package_guid)\n" +
//            "LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,cp.t_stage_id)\n" +
//            "WHERE o.t_user_guid=#{rowGuid} AND ct.id=c.t_class_type_id \n" +
//            "AND o.t_course_guid=c.row_guid\n" +
//            "GROUP BY o.id;")
    @Select("SELECT o.row_guid, o.t_order_no,cp.t_name packageName,s.t_name stageName,cp.t_price,c.t_course_name\n" +
            "FROM tb_course c, tb_course_type ct, tb_order o LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.row_guid,o.t_class_package_guid)\n" +
            "LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,cp.t_stage_id)\n" +
            "WHERE o.t_user_guid=#{rowGuid} AND ct.id=c.t_class_type_id AND c.row_guid=o.t_course_guid\n" +
            "AND o.t_course_guid=c.row_guid;")
    List<UOCCP> findUserOrderPackage(@Param("rowGuid") String rowGuid);

    /*@Select("SELECT o.row_guid, o.t_order_no, o.t_price, c.t_course_name, ct.t_class_name\n" +
            "FROM tb_course c, tb_course_type ct, tb_order o\n" +
            "WHERE o.t_user_guid=#{rowGuid} AND ct.id=c.t_class_type_id \n" +
            "AND o.t_course_guid=c.row_guid;")*/
    @Select("SELECT o.row_guid, o.t_order_no,o.t_price, c.t_course_name, ct.t_class_name, o.t_order_status,o.t_place_order_date\n" +
            "FROM tb_course c, tb_course_type ct, tb_order o\n" +
            "WHERE o.t_user_guid=#{rowGuid} AND ct.id=c.t_class_type_id \n" +
            "AND o.t_course_guid=c.row_guid;")
    List<UOCCP> findUserOrderClass(@Param("rowGuid") String rowGuid);

}
