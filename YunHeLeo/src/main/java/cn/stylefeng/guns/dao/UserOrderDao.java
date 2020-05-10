package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.UserOrder;
import cn.stylefeng.guns.pojos.WorkUC;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserOrderDao extends BaseMapper<UserOrder> {

    // 查询每个学员的订单量
    @Select("SELECT u.row_guid, u.`t_username`,COUNT(*) count FROM tb_order o,tb_user u " +
            "WHERE o.t_user_guid = u.`row_guid` AND u.`row_guid`=#{RowGuid} GROUP BY u.id")
    @Results({
            @Result(column = "row_guid",property = "RowGuid"),
            @Result(column = "t_username",property = "tUsername"),
            @Result(column = "count",property = "count")
    })
    UserOrder userOrderCount(@Param("RowGuid") String RowGuid);

    // 查询用户的作品量
    @Select("SELECT\n" +
            "\tu.row_guid,u.t_username,count(*) count\n" +
            "FROM\n" +
            "\ttb_user u,\n" +
            "\ttb_works w\n" +
            "WHERE\n" +
            "\tw.row_guid=u.row_guid AND u.row_guid=#{RowGuid} GROUP BY u.id")
    @Results({
            @Result(column = "row_guid", property = "RowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "count", property = "count")
    })
    UserOrder userWorkCount(@Param("RowGuid") String RowGuid);

    // 查询用户的课程试听量
    @Select("SELECT u.row_guid, u.t_username, count(*) count\n" +
            "FROM tb_user u, tb_course c, tb_try_course tc\n" +
            "WHERE tc.user_guid=u.row_guid AND c.row_guid=tc.course_guid AND u.row_guid=#{RowGuid} GROUP BY u.id")
    @Results({
            @Result(column = "row_guid", property = "RowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "count", property = "count")
    })
    UserOrder userCourseCount(@Param("RowGuid") String RowGuid);

}
