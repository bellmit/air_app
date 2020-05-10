package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Img;
import cn.stylefeng.guns.pojos.EmpImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CarouselImgDao extends BaseMapper<Img> {

    @Update("UPDATE tb_img SET t_img_type=#{tImgType},t_name=#{tName},t_link_id=#{linkId},t_order_no=#{tOrderNo},t_img_url=#{tImgUrl}," +
            "t_link_type=#{tLinkType},t_upload_man=#{tUploadMan},t_upload_date=#{tUploadDate} WHERE id=#{id}")
    void updateId(Img img);

    @Select("SELECT * FROM tb_img where id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_img_type", property = "tImgType"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_name", property = "tName"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_link_type", property = "tLinkType"),
            @Result(column = "t_upload_man", property = "tUploadMan"),
            @Result(column = "t_upload_date", property = "tUploadDate")
    })
    Img findById(@Param("id") String id);

}
