package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Stage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StageDao extends BaseMapper<Stage> {

    /**
     * 根据id查询阶段
     * @param RowGuid
     * @return
     */
    @Select("SELECT s.id,s.row_guid,s.t_name,s.t_class_id,s.t_order,s.t_status, s.`t_class_id` classId\n" +
            "FROM tb_stage s WHERE s.row_guid=#{RowGuid}")
    Stage findById(@Param("RowGuid") String RowGuid);

    /**
     * 某课程下的所有阶段
     * @param RowGuid
     * @return
     */
    @Select("SELECT s.row_guid stageRowGuid, s.t_name stageName\n" +
            "FROM tb_course co LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,co.stage_id)\n" +
            "WHERE co.row_guid=#{RowGuid}")
    @Results({
            @Result(column = "stageRowGuid",property = "rowGuid"),
            @Result(column = "stageName",property = "tName")
    })
    List<Stage> findAll(@Param("RowGuid") String RowGuid);
}
