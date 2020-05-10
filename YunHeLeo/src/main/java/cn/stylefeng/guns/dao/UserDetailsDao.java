package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.UserDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDetailsDao extends BaseMapper<UserDetails> {

    // 根据id查询用户详细基本信息
    @Select("SELECT u.row_guid, u.t_mobile, u.t_username,u.t_sex,u.t_register_date,u.t_student_no,u.t_head_img,\n" +
            "u.t_children_date,u.t_register_ip,l.t_level_name,ll.t_login_date,ll.wechat_name,ll.qq_no\n" +
            "FROM tb_user u,tb_level l,tb_login_log ll\n" +
            "WHERE u.stage_id = l.id\n" +
            "AND ll.recordguid = u.row_guid AND ll.t_login_date<=NOW()\n" +
            "AND u.row_guid = #{RowGuid} LIMIT 1")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_sex", property = "tSex"),
            @Result(column = "t_register_date", property = "tRegisterDate"),
            @Result(column = "t_student_no", property = "tStudentNo"),
            @Result(column = "t_head_img", property = "tHeadImg"),
            @Result(column = "t_children_date", property = "tChildrenDate"),
            @Result(column = "t_register_ip", property = "tRegisterIp"),
            @Result(column = "t_level_name", property = "tLevelName"),
            @Result(column = "t_login_date", property = "tLoginDate"),
            @Result(column = "t_login_ip", property = "tLoginIp"),
            @Result(column = "wechat_name", property = "wechatName"),
            @Result(column = "qq_no", property = "qqNo")
    })
    UserDetails findByIdUser(@Param("RowGuid") String RowGuid);

    // 根据id查询用户地址
    @Select("SELECT a.id,a.t_mobile,a.t_username tAddressUsername, a.t_address,aad.CityName CityName1,cd.CityName CityName2,sd.StateName,u.t_username\n" +
            "FROM tb_address a,tb_admin_area_dic aad, tb_city_dic cd, tb_state_dic sd,tb_user u\n" +
            "WHERE a.t_province_dic=sd.Xzqdm AND a.t_city_dic=cd.AdminAreaNum AND a.t_area_dic=aad.AdminAreaNum " +
            "AND u.row_guid=a.t_user_id AND u.row_guid=#{RowGuid}")
    @Results({
            @Result(column = "id", property = "id"),
//            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "tAddressUsername", property = "tAddressUsername"),
            @Result(column = "StateName", property = "StateName"),// 省
            @Result(column = "CityName2", property = "CityName"),// 市
            @Result(column = "CityName1", property = "CityQuName"),// 区
            @Result(column = "t_address", property = "tAddress")
    })
    UserDetails findByIdAddress(@Param("RowGuid") String RowGuid);

    // 根据id查询用户购课信息
    // 1.1查询长期包 包含课包
    @Select("<script>" +
            "SELECT u.row_guid, c.t_course_name,ct.t_class_name,CONVERT(GROUP_CONCAT(cp.t_name) USING utf8) StageName\n" +
            "FROM tb_user u,tb_course_user cu,tb_course_type ct, tb_course c LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.id,c.course_package_id)\n" +
            "WHERE u.row_guid=#{RowGuid}\n" +
            "AND cu.t_user_guid=u.row_guid AND cu.t_course_guid=c.id AND ct.id=c.t_class_type_id AND c.t_class_type_id=1 " +
            "GROUP BY c.id \n" +
            "</script>")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_class_name", property = "tClassName"),
            @Result(column = "StageName", property = "StageName")
    })
    List<UserDetails> findLongClass(@Param("RowGuid") String RowGuid);

    // 1.2查询短期班 不包含课包
    @Select("SELECT u.row_guid,u.t_username, c.t_course_name,ct.t_class_name\n" +
            "FROM tb_user u,tb_course_user cu,tb_course_type ct, tb_course c\n" +
            "WHERE u.row_guid=#{RowGuid}\n" +
            "AND cu.t_user_guid=u.row_guid AND cu.t_course_guid=c.row_guid AND ct.id=c.t_class_type_id AND c.t_class_type_id=2")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<UserDetails> findShortClass(@Param("RowGuid") String RowGuid);

    // 1.3查询免费班 不包含课包
    @Select("SELECT u.row_guid,u.t_username, c.t_course_name,ct.t_class_name\n" +
            "FROM tb_user u,tb_course_user cu,tb_course_type ct, tb_course c\n" +
            "WHERE u.row_guid=#{RowGuid}\n" +
            "AND cu.t_user_guid=u.row_guid AND cu.t_course_guid=c.row_guid AND ct.id=c.t_class_type_id AND c.t_class_type_id=0")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<UserDetails> findFreeClass(@Param("RowGuid") String RowGuid);

    // 2.查询长期班课包 包含阶段
    @Select("<script>" +
            "SELECT u.row_guid,cp.t_name PackageName,CONVERT(GROUP_CONCAT(s1.t_name) USING utf8) StageName\n" +
            "FROM tb_course_package cp LEFT JOIN tb_stage s1 ON FIND_IN_SET(s1.id,cp.t_stage_id),tb_user u\n" +
            "WHERE u.row_guid=#{RowGuid}\n" +
            "GROUP BY cp.id;" +
            "</script>")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "PackageName", property = "PackageName"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "StageName", property = "StageName")
    })
    List<UserDetails> findPackageStage(@Param("RowGuid") String RowGuid);

    // 3.查询购买日期
    @Select("SELECT u.row_guid,o.t_pay_date,o.t_due_date,o.t_activate_date,c.t_course_name,u.t_username,ct.t_class_name\n" +
            " FROM tb_order o, tb_user u, tb_course c, tb_course_type ct\n" +
            " WHERE u.row_guid=#{RowGuid} AND o.t_user_guid=u.row_guid AND o.t_course_guid=c.row_guid" +
            " AND ct.id=c.t_class_type_id;")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_pay_date", property = "PackageName"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_activate_date", property = "tActivateDate"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_due_date", property = "tDueDate")
    })
    List<UserDetails> findBuyDate(@Param("RowGuid") String RowGuid);

    // 4.查询课程状态
    @Select("SELECT u.row_guid,cu.t_status,c.t_course_name,u.t_username\n" +
            "FROM tb_course_user cu,tb_user u, tb_course c\n" +
            "WHERE u.row_guid=#{RowGuid} AND cu.t_user_guid=u.row_guid AND cu.t_course_guid=c.row_guid")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_username", property = "tUsername")
    })
    List<UserDetails> findStatus(@Param("RowGuid") String RowGuid);

}
