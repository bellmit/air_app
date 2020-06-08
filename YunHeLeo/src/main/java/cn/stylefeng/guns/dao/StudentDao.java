package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Level;
import cn.stylefeng.guns.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentDao extends BaseMapper<User> {

    @Update("update tb_user set t_password=#{tPassword} where t_mobile=#{tMobile}")
    void updatePwd(User user);

    /*@Select("select id,row_guid,t_username,t_mobile,t_password,t_status,t_children_date,t_register_date,t_student_no,t_head_img from tb_user where t_mobile=#{tMobile}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_children_date", property = "tChildrenDate"),
            @Result(column = "t_register_date", property = "tRegisterDate"),
            @Result(column = "t_student_no", property = "tStudentNo"),
            @Result(column = "t_head_img", property = "tHeadImg"),
            @Result(column = "t_password", property = "tPassword")
    })*/
    @Select("select id,row_guid,t_username,t_mobile,t_password,t_status,unix_timestamp(t_children_date) as tChildrenDate," +
            "t_register_date,t_student_no,t_head_img,show_stage_id,stage_id,t_sex from tb_user where t_mobile=#{tMobile}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "tChildrenDate", property = "tChildrenDate"),
            @Result(column = "t_register_date", property = "tRegisterDate"),
            @Result(column = "t_student_no", property = "tStudentNo"),
            @Result(column = "t_head_img", property = "tHeadImg"),
            @Result(column = "show_stage_id", property = "showStageId"),
            @Result(column = "stage_id", property = "stageId"),
            @Result(column = "t_sex", property = "tSex"),
            @Result(column = "t_password", property = "tPassword")
    })
    User findByMobile(@Param("tMobile") String tMobile);

    @Select("select id,row_guid,t_username,t_mobile,t_password,t_status,unix_timestamp(t_children_date) as tChildrenDate," +
            "t_register_date,t_student_no,t_head_img,show_stage_id,stage_id, t_sex from tb_user where row_guid=#{rowGuid}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "tChildrenDate", property = "tChildrenDate"),
            @Result(column = "t_register_date", property = "tRegisterDate"),
            @Result(column = "t_student_no", property = "tStudentNo"),
            @Result(column = "t_head_img", property = "tHeadImg"),
            @Result(column = "show_stage_id", property = "showStageId"),
            @Result(column = "stage_id", property = "stageId"),
            @Result(column = "t_sex", property = "tSex"),
            @Result(column = "t_password", property = "tPassword")
    })
    User findByGuid(@Param("rowGuid") String rowGuid);

    @Select("select id,row_guid,t_username,t_mobile,t_password,t_status,t_head_img from tb_user")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_head_img", property = "tHeadImg"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_password", property = "tPassword")
    })
    List<User> findAll();

    @Select("select l.id,l.t_level_name from tb_level l")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_level_name", property = "tLevelName")
    })
    List<Level> getStudyLevel();

    @Select("SELECT u.row_guid,u.show_stage_id,u.id,u.t_age,u.t_children_date AS tChildrenDate,u.t_head_img," +
            "u.t_mobile,u.t_name,u.t_password,u.t_register_date,\n" +
            "u.t_register_ip,u.t_sex,u.t_student_no,u.t_username, u.stage_id, l.t_level_name \n" +
            "FROM tb_user u,tb_level l WHERE u.row_guid=#{rowGuid} AND l.id=u.stage_id ")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_age", property = "tAge"),
            @Result(column = "tChildrenDate", property = "tChildrenDate"),
            @Result(column = "t_name", property = "tName"),
            @Result(column = "t_head_img", property = "tHeadImg"),
            @Result(column = "t_register_date", property = "tRegisterDate"),
            @Result(column = "t_register_ip", property = "tRegisterIp"),
            @Result(column = "t_sex", property = "tSex"),
            @Result(column = "t_student_no", property = "tStudentNo"),
            @Result(column = "stage_id", property = "stageId"),
            @Result(column = "show_stage_id", property = "showStageId"),
            @Result(column = "t_level_name", property = "tLevelName"),
            @Result(column = "t_password", property = "tPassword")
    })
    User userInfo(@Param("rowGuid") String rowGuid);

    // 查询当前用户作品数
    /*@Select("SELECT COUNT(*)\n" +
            "FROM tb_link l\n" +
            "WHERE l.t_link_name='作品' AND l.t_create_man=#{rowGuid}")*/
    @Select("SELECT COUNT(*) FROM tb_works w WHERE w.`t_user_guid`=#{rowGuid}")
    int findUserWorksCount(@Param("rowGuid") String rowGuid);

    // 查询wx_unionid有没有对应的用户
    @Select("select row_guid from tb_user where wx_unionid = #{wx_unionid};")
    String findUserByWxUnionid(@Param("wx_unionid") String wx_unionid);

    @Select("select row_guid from tb_user where qq_unionid = #{qq_unionid};")
    String findUserByQQUnionid(@Param("qq_unionid") String qq_unionid);
}
