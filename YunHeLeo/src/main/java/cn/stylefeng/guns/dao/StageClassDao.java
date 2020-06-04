package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.StageClass;
import cn.stylefeng.guns.pojos.StageClass1Node;
import cn.stylefeng.guns.pojos.StageClassNode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StageClassDao extends BaseMapper<StageClass> {

    // 查询阶段 包括对应的课节
    List<StageClassNode> findStage(String RowGuid);

    @Select("SELECT CONVERT(GROUP_CONCAT(e.t_realyname) USING utf8) t_realyname\n" +
            "FROM tb_employee e LEFT JOIN tb_class c ON FIND_IN_SET(e.id,c.t_teacher)\n" +
            "WHERE c.row_guid=#{rowguid}")
    @Results({
            @Result(column = "t_realyname", property = "tRealyname")
    })
    StageClass findTeacher(@Param("rowguid") String rowguid);

    /**
     * 查询当前课程下的课节已学课节数量
     * @param rowGuid
     * @return
     */
    @Select("SELECT COUNT(*) classCount\n" +
            "FROM tb_course c LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,c.stage_id) LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,s.t_class_id)\n" +
            "WHERE cl.study_status=1 AND c.row_guid=#{rowGuid}")
    @Results({
            @Result(column = "classCount", property = "classCount")
    })
    Integer findClassStudyCount(@Param("rowGuid") String rowGuid);

    /**
     * 查询当前课程下 所有阶段下的总课节数量
     * @param rowGuid
     * @return
     */
    @Select("SELECT COUNT(*) studyClassCount\n" +
            "FROM tb_course c LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,c.stage_id) LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,s.t_class_id)\n" +
            "WHERE c.row_guid=#{rowGuid} AND cl.t_status=0")
    @Results({
            @Result(column = "studyClassCount", property = "studyClassCount")
    })
    Integer findClassTotalCount(@Param("rowGuid") String rowGuid);

    /**
     * 某阶段下课节数
     * @param stageRowGuid
     * @return
     */
    @Select("SELECT COUNT(*) stageClassCount\n" +
            "FROM tb_stage s LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,s.t_class_id)\n" +
            "WHERE s.row_guid=#{stageRowGuid} AND cl.t_status=0 \n")
    @Results({
            @Result(column = "stageClassCount", property = "stageClassCount")
    })
    Integer findStageClassCount(@Param("stageRowGuid") String stageRowGuid);

}
