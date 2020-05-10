package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Employee;
import cn.stylefeng.guns.pojo.Message;
import cn.stylefeng.guns.pojos.EmpMess;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmpMessDao extends BaseMapper<EmpMess> {

    @Select("<script>" +
            "select m.id, m.t_message_title, m.t_message_content, m.t_update_man," +
            "m.t_update_date,m.t_send_date,m.t_status,e.t_username" +
            " from tb_message m, tb_employee e " +
            "where m.t_update_man = e.id " +
            "<if test='tMessageTitle!=null and tMessageTitle!=\"\" '> " +
            " and t_message_title like '%${tMessageTitle}%'</if>" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_message_title", property = "tMessageTitle"),
            @Result(column = "t_message_content", property = "tMessageContent"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_send_date", property = "tSendDate"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_username", property = "tUsername")
    })
    Page<EmpMess> findAll(EmpMess empMess);

    @Select("<script>" +
            "select m.id, m.t_message_title, m.t_message_content, m.t_update_man," +
            "m.t_update_date,m.t_send_date,m.t_status,e.t_username" +
            " from tb_message m, tb_employee e " +
            "where m.t_update_man = e.id and m.id = #{id} " +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_message_title", property = "tMessageTitle"),
            @Result(column = "t_message_content", property = "tMessageContent"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_send_date", property = "tSendDate"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_username", property = "tUsername")
    })
    EmpMess findById(@Param("id") String id);
}
