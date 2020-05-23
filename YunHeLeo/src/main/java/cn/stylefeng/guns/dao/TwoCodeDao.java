package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.ClassImageResponse;
import cn.stylefeng.guns.pojo.TwoCode;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TwoCodeDao {

    // -- 根据课程rowguid的客服id查询客服二维码
    @Select("SELECT e.`t_img_url` imgUrl FROM tb_course c, `tb_employee` e " +
            "WHERE e.id=c.`t_service_id` AND c.row_guid=#{rowguid}")
    TwoCode findImgUrl(@Param("rowguid") String rowguid);

    @Select("SELECT c.`row_guid`, c.t_name, c.`t_img_url` FROM tb_class c WHERE c.`row_guid`=#{rowguid}")
    @Results({
            @Result(column = "row_guid", property = "classrowguid"),
            @Result(column = "t_name", property = "tClassName"),
            @Result(column = "t_img_url", property = "tImgUrl")
    })
    ClassImageResponse findClassImage(@Param("rowguid") String rowguid);

}
