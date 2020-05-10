package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Img;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImgDao {


    @Select("select i.row_guid,i.id,i.t_img_type,i.t_img_url,i.t_link_id,i.t_link_type,i.t_name,i.t_order_no\n" +
            "from tb_img i")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "id", property = "id"),
            @Result(column = "t_img_type", property = "tImgType"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_link_id", property = "linkId"),
            @Result(column = "t_link_type", property = "tLinkType"),
            @Result(column = "t_name", property = "tName"),
            @Result(column = "t_order_no", property = "tOrderNo")
    })
    List<Img> branner();
}
