package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.ClassDedails;
import cn.stylefeng.guns.pojos.CourseAll;
import cn.stylefeng.guns.pojos.StageClass;
import cn.stylefeng.guns.pojos.StageClass1;
import cn.stylefeng.guns.vo.ClassLinkVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassLinkVoDao {

    /**
     * 课节rowguid查询所属环节学习状态
     * @param rowGuid
     * @return
     */
    @Select("SELECT l.row_guid,l.t_class_id,l.t_link_name,l.study_status\n" +
            "FROM tb_link l WHERE l.t_class_id=#{rowGuid}")
    @Results({
            @Result(column = "row_guid", property = "linkRowGuid"),
            @Result(column = "t_class_id", property = "classRowGuid"),
            @Result(column = "t_link_name", property = "tLinkName"),
            @Result(column = "study_status", property = "studyStatus")
    })
    List<ClassLinkVo> findClassLinkStatus(@Param("rowGuid") String rowGuid);

    /**
     * 课包是否已购买  课节是否可以看
     */
    @Select("SELECT c.id classId,\n" +
            " c.row_guid classRowGuid,\n" +
            " cpu.`t_due_time`,cpu.`t_status`,\n" +
            " cpu.`t_activate_time` " +
            "FROM tb_course_package_user cpu ,\n" +
            " tb_course_package cp LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid, cp.t_stage_id)\n" +
            " LEFT JOIN tb_class c ON FIND_IN_SET(c.id, s.t_class_id)\n" +
            "WHERE c.row_guid = #{rowguid}\n" +
            "  AND c.`t_status` = 0\n" +
            "  AND cpu.`t_package_guid`=cp.`row_guid` AND cpu.`user_guid`= #{userguid}")
    @Results({
            @Result(column = "classRowGuid", property = "classRowGuid"),
            @Result(column = "classId", property = "classRowGuid"),
            @Result(column = "t_status", property = "status"),
            @Result(column = "t_activate_time", property = "activateTime"),
            @Result(column = "t_due_time", property = "dueTime")
    })
    StageClass1 isStudyClass(@Param("rowguid") String rowguid, @Param("userguid") String userguid);

}
