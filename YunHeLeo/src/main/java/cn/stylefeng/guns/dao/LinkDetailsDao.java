package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Link;
import cn.stylefeng.guns.pojos.LinkDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LinkDetailsDao extends BaseMapper<LinkDetails> {

    @Select("SELECT l.id LinkId,l.row_guid LinkRowGuid,l.t_link_name,l.t_video_url,l.t_status,l.t_update_date,\n" +
            "c.row_guid ClassRowGuid, e.t_username\n" +
            "FROM tb_link l, tb_class c, tb_employee e\n" +
            "WHERE l.t_class_id=c.row_guid AND e.id=l.t_create_man AND c.row_guid=#{rowGuid}")
    List<LinkDetails> findLinkId(@Param("rowGuid") String rowGuid);

    @Select("SELECT l.`row_guid`,t_link_name,t_video_url,e.`t_username`,t_update_date FROM tb_link l,tb_employee e\n" +
            "WHERE e.`id`=l.t_create_man AND l.`row_guid`= #{rowguid}")
    @Results({
            @Result(column = "t_link_name", property = "tLinkName"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "row_guid", property = "LinkRowGuid"),
            @Result(column = "t_video_url", property = "tVideoUrl"),
            @Result(column = "t_update_date", property = "tUpdateDate")
    })
    LinkDetails findByVideo(@Param("rowguid") String rowguid);

}
