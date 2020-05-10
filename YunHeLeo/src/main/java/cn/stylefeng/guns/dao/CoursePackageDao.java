package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.CoursePackage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CoursePackageDao extends BaseMapper<CoursePackage> {

    @Select("SELECT cp.row_guid, cp.t_name,cp.t_activate_date,cp.t_stage_id, cp.t_study_date, cp.t_price, cp.t_status, cp.t_stage_id, cp.t_activate_time," +
            "t_due_time\n" +
            "FROM tb_course_package cp WHERE cp.row_guid=#{rowGuid}")
    public CoursePackage findById(@Param("rowGuid") String rowGuid);

    @Update("update tb_course_package set t_name=#{packageName},t_price=#{tPrice},t_stage_id=#{tStageId},t_study_date=#{tStudyDate}," +
            "t_activate_date=#{tActivateDate},t_activate_time=#{tActivateTime},t_due_time=#{tDueTime} where row_guid=#{rowGuid}")
    void updateByRowGuid(CoursePackage coursePackage);

    @Update("update tb_course_package set t_status=#{tStatus} where row_guid=#{rowGuid}")
    void updateDownByRowGuid(CoursePackage coursePackage);

    @Update("update tb_course_package set t_status=3 where row_guid=#{rowGuid}")
    void deleteCoursePackage(String rowGuid);

    /**
     * 发布课包
     * @param rowGuid
     */
    @Update("UPDATE tb_course_package SET t_status=1 WHERE row_guid=#{rowGuid}")
    void releaseCoursePackage(@Param("rowGuid") String rowGuid);
}
