package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Works;
import cn.stylefeng.guns.vo.WorkResponse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WorksDao extends BaseMapper<Works> {

    @Select("SELECT w.id, w.t_img_url, w.t_content \n" +
            "FROM tb_works w \n" +
            "WHERE w.id=#{id}")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "t_img_url",property = "tImgUrl"),
            @Result(column = "t_content",property = "tContent")
    })
    Works findById(@Param("id") Integer id);

    @Select("SELECT w.id,w.`row_guid`\n" +
            "FROM tb_works w WHERE w.`t_link_guid`=#{linkguid} \n" +
            "AND w.`t_user_guid`=#{userguid} limit 1")
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "t_img_url",property = "tImgUrl"),
            @Result(column = "t_content",property = "tContent")
    })
    Works findWorkStatus(@Param("linkguid") String linkguid, @Param("userguid") String userguid);

}
