package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Creation;
import cn.stylefeng.guns.pojos.EmpCreation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface CreationDao extends BaseMapper<Creation> {

    @Select("<script>" +
            "select c.id,c.t_creation_name,c.t_update_date from tb_creation c " +
            "where c.id=#{id}" +
            "</script>")
    @Results({
            @Result(column = "t_creation_name", property = "tCreationName"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "t_update_date", property = "tUpdateDate")
    })
    Creation findById(@Param("id") Integer id);

    @Update("update tb_creation set t_creation_name=#{tCreationName},t_update_man=#{tUpdateMan},t_update_date=#{tUpdateDate}" +
            " where id=#{id}")
    void update(Creation creation);

    // 创作方式下拉列表
    @Select("SELECT c.id,c.t_creation_name FROM tb_creation c")
    List<Creation> findCreationList();
}
