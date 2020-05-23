package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TypeDao extends BaseMapper<Type> {

    @Select("<script>" +
            "select * from tb_type where row_guid=#{rowGuid} " +
            " order by t_order" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_order", property = "tOrder"),
            @Result(column = "t_status", property = "tStatus")
    })
    Type findById(@Param("rowGuid") String rowGuid);

    @Select("<script>" +
            "select * from tb_type where 1=1 and t_status=1 " +
            " <if test='tTypeName!=null and tTypeName!=\"\"'> and t_type_name like '%${tTypeName}%'</if>" +
            " order by t_order" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_order", property = "tOrder"),
            @Result(column = "t_status", property = "tStatus")
    })
    Page<Type> findAll(Type type);

    @Select("select count(*) from tb_type")
    int countType();

    /**
     * 下拉列表
     * @return
     */
    @Select("<script>" +
            "select * from tb_type where t_status=1" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_order", property = "tOrder"),
            @Result(column = "t_status", property = "tStatus")
    })
    List<Type> findType();

    @Update("UPDATE tb_type SET t_status=2 WHERE id=#{id}")
    void updateByRowguid(@Param("id") String id);

    @Select("SELECT * FROM tb_type t\n" +
            "WHERE t.id=(SELECT c.`t_course_type_id` FROM tb_course c WHERE c.`t_course_type_id`=#{id} LIMIT 1 ) ")
    Type findByCourseTypeId(@Param("id") String id);

}
