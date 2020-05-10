package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.EmpImg;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CarouselDao extends BaseMapper<EmpImg> {

    @Select("select count(*) from tb_img")
    int countImg();

    @Select("<script>" +
            "select * from tb_img m, tb_employee e where m.t_upload_man = e.id and m.id=#{id}" +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_img_type", property = "tImgType"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_name", property = "tName"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_link_type", property = "tLinkType"),
            @Result(column = "t_upload_man", property = "tUploadMan"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_username", property = "tUsername")
    })
    EmpImg findById(String id);

    @Select("<script>" +
            "select m.id,m.t_img_type,m.t_order_no,m.t_name,m.t_img_url,m.t_link_type," +
            "m.t_upload_man,m.t_upload_date,e.t_username from tb_img m, tb_employee e where m.t_upload_man = e.id " +
            "<if test='tName!=null and tName!=\"\"'> and t_name like '%${tName}%'</if> " +
            "order by m.t_order_no " +
            "</script>")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_img_type", property = "tImgType"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_name", property = "tName"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_link_type", property = "tLinkType"),
            @Result(column = "t_upload_man", property = "tUploadMan"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_username", property = "tUsername")
    })
    Page<EmpImg> findAll(EmpImg empImg);

}
