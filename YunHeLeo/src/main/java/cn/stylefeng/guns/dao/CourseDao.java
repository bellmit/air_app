package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Course;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CourseDao extends BaseMapper<Course> {

    /**
     * 根据课程编号id查询
     */
    @Select("SELECT c.id,c.row_guid,c.t_course_num FROM tb_course c WHERE c.t_course_num=#{tCourseNum}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum")
    })
    Course findByNum(@Param("tCourseNum") String tCourseNum);

    @Select("SELECT c.id,\n" +
            "\tc.t_course_name,c.t_course_num,\n" +
            "\tc.t_course_type_id,\n" +
            "\tc.t_level,t_order_no,\n" +
            "\tc.t_price,t_class_type_id,\n" +
            "\tc.t_teacher_id,\n" +
            "\tc.t_service_id,\n" +
            "\tc.t_img_url,\n" +
            "\tc.t_course_introduce,\n" +
            "\tc.t_prompt,\n" +
            "\tc.t_try_listen,\n" +
            "\tc.t_status,\n" +
            "\tc.t_update_date,\n" +
            "\tc.t_update_man,c.stage_id,c.course_package_id,c.class_id FROM tb_course c WHERE c.row_guid=#{RowGuid}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_course_type_id", property = "tCourseTypeId"),
            @Result(column = "t_level", property = "tLevel"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_class_type_id", property = "tClassTypeId"),
            @Result(column = "t_teacher_id", property = "tTeacherId"),
            @Result(column = "t_service_id", property = "tServiceId"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_course_introduce", property = "tCourseIntroduce"),
            @Result(column = "t_prompt", property = "tPrompt"),
            @Result(column = "t_try_listen", property = "tTryListen"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "stage_id", property = "stageId"),
            @Result(column = "course_package_id", property = "coursePackageId"),
            @Result(column = "class_id", property = "classId")
    })
    Course findById(@Param("RowGuid") String RowGuid);

    @Delete("delete from tb_course where row_guid=#{RowGuid}")
    void deleteByRowGuid(String RowGuid);

    /**
     * 随机获取6个免费课
     * @return
     */
    @Select("select c.row_guid,c.t_course_name,c.t_course_num,t_img_url,t_class_type_id " +
            "from tb_course c where t_class_type_id =0 AND c.t_status=0 ORDER BY RAND() limit 6")
    List<Course> freeCourse();

    /**
     * 随机获取6个免费课 [根据用户选择的阶段]
     * @return
     */
    @Select("select c.row_guid,c.t_course_name,c.t_course_num,t_img_url,t_class_type_id " +
            "from tb_course c where t_class_type_id =0 AND c.t_status=0 " +
            //"AND c.t_level=#{showStageId} " +
            " AND FIND_IN_SET(#{showStageId},c.`t_level`) "+
            "ORDER BY RAND() limit 6")
    List<Course> freeShowStageCourse(@Param("showStageId") Integer showStageId);

    /**
     * 随机获取6个收费课
     * @return
     */
    @Select("select c.row_guid,c.t_course_name,c.t_course_num,t_img_url,t_price,t_class_type_id " +
            "from tb_course c where t_class_type_id !=0 AND c.t_status=0 ORDER BY RAND() limit 6")
    List<Course> chargeCourse();

    /**
     * 随机获取6个收费课 [根据用户选择的阶段]
     * @return
     */
    @Select("select c.row_guid,c.t_course_name,c.t_course_num,t_img_url,t_price,t_class_type_id " +
            "from tb_course c where t_class_type_id !=0 AND c.t_status=0 " +
            //"AND c.t_level=#{showStageId} " +
            " AND FIND_IN_SET(#{showStageId},c.`t_level`) "+
            "ORDER BY RAND() limit 6")
    List<Course> chargeShowStageCourse(@Param("showStageId") Integer showStageId);

    /**
     * 根据选择级别 查询 不同课程
     */
    @Select("<script>" +
            "select c.row_guid,c.id,c.t_course_name,c.t_course_num,c.t_course_introduce,c.t_class_type_id," +
            "c.t_course_type_id,c.t_img_url,c.t_learn_count\n" +
            "from tb_course c where c.t_level=#{levelId} AND c.t_class_type_id=#{type} AND c.t_status=0" +
            "</script>")
    List<Course> findByLevel(@Param("levelId") Integer levelId, @Param("type") Integer type);

    /**
     * 根据阶段选择随机获取免费课
     * @param levelId
     * @return
     */
    @Select("select c.row_guid,c.t_course_name,c.t_course_num,t_img_url,c.t_class_type_id,c.t_course_type_id " +
            "from tb_course c where t_class_type_id =0 AND c.t_level=#{levelId} AND c.t_status=0 ORDER BY RAND() limit 6")
    List<Course> findLevelFree(Integer levelId);

    /**
     * 根据阶段获取收费课
     * @param levelId
     * @return
     */
    @Select("select c.row_guid,c.t_course_name,c.t_course_num,t_img_url,c.t_course_type_id,c.t_class_type_id " +
            "from tb_course c where t_class_type_id!=0 AND c.t_level=#{levelId} AND c.t_status=0 ORDER BY RAND() limit 6")
    List<Course> findLeveCharge(Integer levelId);

    /**
     * 修改用户的阶段
     * @param rowguid
     * @param stageId
     */
    @Update("update tb_user c set stage_id=#{stageId} where row_guid=#{rowguid}")
    void updateUserLevel(String rowguid, Integer stageId);

    @Select("SELECT c.id, c.row_guid, c.t_course_name,c.t_course_num,\n" +
            "\tc.t_course_type_id, c.t_level,t_order_no,\n" +
            "\tc.t_price,t_class_type_id, c.t_teacher_id, c.t_service_id,\n" +
            "\tc.t_img_url, c.t_course_introduce, c.t_prompt,\n" +
            "\tc.t_try_listen, c.t_status, c.t_update_date,\n" +
            "\tc.t_update_man,c.stage_id,c.course_package_id,c.class_id FROM tb_course c ")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_course_type_id", property = "tCourseTypeId"),
            @Result(column = "t_level", property = "tLevel"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_class_type_id", property = "tClassTypeId"),
            @Result(column = "t_teacher_id", property = "tTeacherId"),
            @Result(column = "t_service_id", property = "tServiceId"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_course_introduce", property = "tCourseIntroduce"),
            @Result(column = "t_prompt", property = "tPrompt"),
            @Result(column = "t_try_listen", property = "tTryListen"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "stage_id", property = "stageId"),
            @Result(column = "course_package_id", property = "coursePackageId"),
            @Result(column = "class_id", property = "classId")
    })
    List<Course> findAll();

}
