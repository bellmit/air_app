package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Link;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface LinkDao extends BaseMapper<Link> {

    @Select("SELECT l.row_guid, l.t_class_id, l.study_status, l.study_time, l.t_link_name, l.t_video_url\n" +
            "FROM tb_link l\n" +
            "WHERE l.row_guid=#{rowguid}")
    Link findById(@Param("rowguid") String rowguid);

    @Update("UPDATE tb_link SET study_status=#{studyStatus}, study_time=#{studyTime} " +
            " WHERE row_guid=#{rowGuid}")
    void updateByRowguid(Link link);

    @Update("UPDATE tb_link SET t_video_url=#{tVideoUrl},t_create_man=#{tCreateMan},t_update_date=#{tUpdateDate} " +
            "WHERE row_guid=#{rowGuid}")
    void updateByVideo(Link link);

    // 下架环节
    @Update("UPDATE tb_link SET t_status=2 " +
            " WHERE row_guid=#{rowGuid}")
    void updateDownVideo(String rowguid);

    @Update("UPDATE tb_link SET t_status=0 " +
            " WHERE row_guid=#{rowGuid}")
    void upVideo(String rowguid);
}
