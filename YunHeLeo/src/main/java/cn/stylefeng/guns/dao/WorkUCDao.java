package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.WorkUC;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface WorkUCDao extends BaseMapper<WorkUC> {

    /*@Select("<script>" +
            "SELECT w.id, w.t_content, w.t_upload_date, w.t_works_name, w.t_works_num, w.t_works_type, w.t_img_url, u.t_mobile, u.t_username,co.t_course_name, cl.t_name\n" +
            "FROM tb_user u, tb_works w, tb_course co, tb_class cl\n" +
            " where w.t_user_guid=u.id and w.t_class_guid=cl.id and co.id=w.t_course_guid " +
            " <if test='tUsername!=null and tUsername!=\"\" ' > and (u.t_username LIKE '%${tUsername}%' </if>" +
            " <if test='tMobile!=null and tMobile!=\"\" ' > or u.t_mobile=#{tMobile} </if>" +
            " <if test='tWorksNum!=null and tWorksNum!=\"\" ' > or w.t_works_num=#{tWorksNum} ) </if>" +
            "</script>")*/

    @Select("<script>" +
            "SELECT w.id, w.t_content, w.t_upload_date, w.t_works_name, w.t_works_num, w.t_works_type, w.t_img_url, u.t_mobile, u.t_username,co.t_course_name, cl.t_name\n" +
            "FROM tb_user u, tb_works w, tb_course co, tb_class cl\n" +
            "where w.t_user_guid=u.row_guid and w.t_class_guid=cl.row_guid and co.row_guid=w.t_course_guid" +
            " <if test='tUsername!=null and tUsername!=\"\" ' > and (u.t_username LIKE '%${tUsername}%' </if>" +
            " <if test='tMobile!=null and tMobile!=\"\" ' > or u.t_mobile=#{tMobile} </if>" +
            " <if test='tWorksNum!=null and tWorksNum!=\"\" ' > or w.t_works_num=#{tWorksNum} ) </if>" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_content", property = "tContent"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_works_name", property = "tWorksName"),
            @Result(column = "t_works_num", property = "tWorksNum"),
            @Result(column = "t_works_type", property = "tWorksType"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_name", property = "tName")
    })
    List<WorkUC> findAll(WorkUC workUC);

    @Select("SELECT count(*) FROM tb_works")
    int count();

   @Select("SELECT w.id, w.t_img_url, w.t_content,w.t_works_name,w.t_works_num, e.t_username empuser, u.t_username stuuser \n" +
           "FROM tb_works w, tb_employee e, tb_user u\n" +
           "WHERE w.t_teacher_guid=e.id and w.t_user_guid=u.row_guid AND w.id=#{id}")
   @Results({
           @Result(column = "id", property = "id"),
           @Result(column = "t_img_url", property = "tImgUrl"),
           @Result(column = "t_content", property = "tContent"),
           @Result(column = "empuser", property = "tEmpname"),
           @Result(column = "t_works_name", property = "tWorksName"),
           @Result(column = "t_works_num", property = "tWorksNum"),
           @Result(column = "stuuser", property = "tUsername")
   })
   WorkUC findById(@Param("id") Integer id);
}
