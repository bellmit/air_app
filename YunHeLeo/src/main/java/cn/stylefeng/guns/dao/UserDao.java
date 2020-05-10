package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.UserLogLevel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<UserLogLevel> {

/*    @Select(value = {"<script>" +
            "SELECT u.row_guid,u.t_mobile,u.t_username,u.t_sex,u.t_register_date,\n" +
            "\tu.t_register_ip,l.t_level_name,ll.t_login_date \n" +
            "FROM tb_user u,tb_level l,tb_login_log ll \n" +
            "WHERE u.stage_id=l.id " +
            " AND ll.t_login_date=(SELECT l.t_login_date FROM tb_login_log l WHERE l.t_login_date<=NOW() LIMIT 1)"+// AND ll.recordguid=u.row_guid " +
            " <if test='tUsername!=null and tUsername!=\"\" ' > and ( u.t_username LIKE '%${tUsername}%' </if>" +
            " <if test='tMobile!=null and tMobile!=\"\" ' > or u.t_mobile=#{tMobile}) </if> " +
            "</script>"})
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_level_name", property = "tLevelName"),
            @Result(column = "t_register_ip", property = "tRegisterIp"),
            @Result(column = "t_register_date", property = "tRegisterDate"),
//            @Result(column = "t_login_ip", property = "tLoginIp"),
            @Result(column = "t_login_date", property = "tLoginDate"),
            @Result(column = "t_sex", property = "tSex")
    })*/
    List<UserLogLevel> findAll(UserLogLevel user);

    @Select("SELECT COUNT(*) FROM tb_user u")
    int count();
}
