package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Creation;
import cn.stylefeng.guns.pojos.EmpCreation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface EmpCreationDao extends BaseMapper<EmpCreation> {

    @Select("<script>" +
            "select c.id, c.t_creation_name,c.t_update_date,e.t_username from tb_creation c, tb_employee e " +
            "where 1=1 and c.t_update_man = e.id " +
            "<if test='tCreationName!=null and tCreationName!=\"\"'> and t_creation_name like '%${tCreationName}%'</if> " +
            "</script>")
    @Results({
            @Result(column = "t_creation_name", property = "tCreationName"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "t_update_date", property = "tUpdateDate")
    })
    Page<EmpCreation> findAll(EmpCreation creation);

    @Select("<script>" +
            "select c.id, c.t_creation_name,c.t_update_date,e.t_username from tb_creation c, tb_employee e " +
            "where 1=1 and c.t_update_man = e.id and c.id=#{id}" +
            "</script>")
    @Results({
            @Result(column = "t_creation_name", property = "tCreationName"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "t_update_date", property = "tUpdateDate")
    })
    EmpCreation findById(@Param("id") String id);

    @Select("select count(*) from tb_creation")
    int count();
}
