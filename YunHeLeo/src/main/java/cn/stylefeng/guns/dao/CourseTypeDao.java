package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.CourseType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseTypeDao extends BaseMapper<CourseType> {

    /**
     * 班型单选按钮
     * @return
     */
    @Select("SELECT\n" +
            " ct.id, ct.t_class_name\n" +
            "FROM\n" +
            "\ttb_course_type ct")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseType> courseList();
}
