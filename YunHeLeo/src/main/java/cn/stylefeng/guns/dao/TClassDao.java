package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.TClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface TClassDao extends BaseMapper<TClass> {
    @Select("SELECT c.id,c.t_name,t_teacher,t_point,t_methods FROM tb_class c WHERE c.row_guid=#{rowGuid}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_name", property = "tName")
    })
    TClass findByClass(@Param("rowGuid") String rowGuid);

    @Update("UPDATE tb_class c SET t_name=#{tName},t_teacher=#{tTeacher},t_point=#{tPoint}, " +
            " t_methods=#{tMethods}, t_img_url=#{imgUrl} " +
            " WHERE row_guid=#{rowGuid}")
    void updateByRowGuid(TClass tCla);
}
