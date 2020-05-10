package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.CourseTypes;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CourseTypesDao extends BaseMapper<CourseTypes> {

    /**
     * 课程编辑信息 根据id查询班型
     * @param rowGuid
     * @return
     */
    @Select("SELECT ct.id, ct.t_class_name,c.row_guid,c.t_class_type_id,c.t_course_type_id,c.t_course_introduce,c.t_course_name, c.t_service_id, c.t_teacher_id,c.t_level," +
            "c.t_course_introduce,c.t_course_num,c.t_img_url,c.t_level,c.t_order_no,c.t_painting,c.t_price,c.t_prompt,t.t_type_name,c.t_status  \n" +
            "FROM tb_course_type ct, tb_course c, tb_type t \n" +
            "WHERE ct.id = c.t_class_type_id AND t.id=c.t_course_type_id and c.row_guid=#{rowGuid}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_class_name", property = "tClassName"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_type_id", property = "tCourseTypeId"),
            @Result(column = "t_class_type_id", property = "tClassTypeId"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_course_introduce", property = "tCourseIntroduce"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_level", property = "tLevel"),
            @Result(column = "t_painting", property = "tPainting"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_prompt", property = "tPrompt"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_type_name", property = "tTypeName"),
    })
    CourseTypes findById(@Param("rowGuid") String rowGuid);

}
