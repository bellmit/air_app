package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.CoursePackageUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CoursePackageUserDao extends BaseMapper<CoursePackageUser> {

    @Select("SELECT cpu.row_guid, cpu.user_guid,cpu.t_course_guid,cpu.t_package_guid,cpu.t_status,cpu.t_due_time, cpu.t_activate_time\n" +
            "FROM tb_course_package_user cpu")
    List<CoursePackageUser> findAll();

    @Select("SELECT cpu.row_guid, cpu.user_guid,cpu.t_course_guid,cpu.t_package_guid,cpu.t_status,cpu.t_due_time, cpu.t_activate_time\n" +
            "FROM tb_course_package_user cpu where cpu.row_guid=#{rowGuid}")
    CoursePackageUser findById(@Param("rowGuid") String rowGuid);

    @Select("SELECT cpu.row_guid, cpu.user_guid,cpu.t_course_guid,cpu.t_package_guid,cpu.t_status,cpu.t_due_time, cpu.t_activate_time\n" +
            "FROM tb_course_package_user cpu where cpu.user_guid=#{rowGuid} AND cpu.t_course_guid=#{courseguid} AND cpu.t_package_guid=#{packagguid}")
    List<CoursePackageUser> findByPackageUserGuid(@Param("rowGuid") String rowGuid, @Param("courseguid") String courseguid, @Param("packagguid") String packagguid);

    @Select("SELECT cpu.row_guid, cpu.user_guid,cpu.t_course_guid,cpu.t_package_guid,cpu.t_status,cpu.t_due_time, cpu.t_activate_time\n" +
            "FROM tb_course_package_user cpu where cpu.user_guid=#{rowGuid} AND cpu.t_course_guid=#{courseguid}")
    List<CoursePackageUser> findByUserGuid(@Param("rowGuid") String rowGuid, @Param("courseguid") String courseguid);

    /**
     * 未开始学 已激活
     * @param coursePackageUser
     */
    @Update("UPDATE tb_course_package_user cpu SET cpu.t_status=#{tStatus},cpu.t_activate_time=#{tActivateTime},cpu.t_due_time=#{tDueTime} Where cpu.row_guid=#{rowGuid}")
    void updateByRowGuid(CoursePackageUser coursePackageUser);

    @Update("UPDATE tb_course_package_user cpu SET cpu.t_status=2 where cpu.row_guid=#{rowGuid}")
    void updatePackageStatus(@Param("rowGuid") String rowGuid);

}
