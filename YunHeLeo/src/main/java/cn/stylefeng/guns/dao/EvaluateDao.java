package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.Evaluate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EvaluateDao extends BaseMapper<Evaluate> {
    /**
     * 查询所有评价
     * @return
     */
    /*@Select("<script>" +
            "SELECT u.`id`, u.`t_username`, u.`t_mobile`, c.`t_course_name`, ec.`t_content`, \n" +
            "ec.`t_update_date`,ec.`t_score`, CONVERT( GROUP_CONCAT(l.`t_label_name`) USING utf8 ) t_label_name \n" +
            "FROM tb_user u, tb_course c, tb_eval_course ec LEFT JOIN tb_label l ON FIND_IN_SET(l.`id`,ec.`t_label_id`)\n" +
            "WHERE u.`id`=ec.`t_user_guid` AND ec.`row_guid`=c.`id`\n" +
            " <if test='tUsername!=null and tUsername!=\"\"' > AND ( u.`t_username` LIKE '%${tUsername}%' </if> " +
            " <if test='tMobile!=null and tMobile!=\"\" ' > OR u.`t_mobile`=#{tMobile} </if> " +
            " <if test='tCourseName!=null and tCourseName!=\"\" ' > OR c.t_course_name like '%${tCourseName}%') </if>" +
            "GROUP BY ec.`id` ORDER BY t_update_date DESC " +
            "</script>")*/
    @Select("<script>" +
            "SELECT u.`id`, u.`t_username`, u.`t_mobile`, c.`t_course_name`, ec.`t_content`,ec.`t_update_date`,ec.`t_score`,\n" +
            "(SELECT GROUP_CONCAT(l.t_label_name) FROM tb_label l WHERE FIND_IN_SET(l.id,ec.t_label_id)) labelName\n" +
            "FROM tb_user u, tb_course c, tb_eval_course ec, tb_label l\n" +
            "WHERE u.row_guid=ec.t_user_guid AND ec.`row_guid`=c.`row_guid` AND l.id=ec.t_label_id" +
            " <if test='tUsername!=null and tUsername!=\"\"' > AND ( u.`t_username` LIKE '%${tUsername}%' </if> " +
            " <if test='tMobile!=null and tMobile!=\"\" ' > OR u.`t_mobile`=#{tMobile} </if> " +
            " <if test='tCourseName!=null and tCourseName!=\"\" ' > OR c.t_course_name like '%${tCourseName}%') </if>" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_content", property = "tContent"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "labelName", property = "tLabelName"),
            @Result(column = "t_score", property = "tScore")
    })
    List<Evaluate> findAll(Evaluate evaluate);

    @Select("SELECT COUNT(*) FROM tb_eval_course")
    int count();
}
