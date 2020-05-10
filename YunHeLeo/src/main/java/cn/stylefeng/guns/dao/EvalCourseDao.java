package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.EvalCourse;
import cn.stylefeng.guns.pojos.CourseAll;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EvalCourseDao extends BaseMapper<EvalCourse> {

    @Select("SELECT CONVERT(GROUP_CONCAT(ec.`t_label_id`) USING utf8) labelId\n" +
            "FROM `tb_eval_course` ec WHERE ec.`course_guid`=#{rowguid} ")
    @Results({
            @Result(column = "labelId", property = "tLabelId")
    })
    EvalCourse countLabel(@Param("rowguid") String rowguid);
}
