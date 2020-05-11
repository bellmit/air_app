package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.CoursePackageResponse;
import cn.stylefeng.guns.pojo.MyWorkResponse;
import cn.stylefeng.guns.vo.WorkResponse;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MyWorkDao {

    /**
     * 查看我的作品 全课
     * @param rowguid
     * @return
     */
    @Select("<script>SELECT w.row_guid, w.t_img_url, w.t_content , w.t_works_name, c.t_course_name, cl.t_name," +
            " UNIX_TIMESTAMP( w.t_upload_date ) t_upload_date \n" +
            "FROM tb_course c, tb_class cl, tb_works w \n" +
            "WHERE w.t_course_guid=c.row_guid AND cl.row_guid=w.t_class_guid \n" +
            "AND w.t_user_guid=#{rowguid}" +
            "<if test='courseguid!=\"\" and courseguid!=null '> AND c.row_guid=#{courseguid} </if>" +
            "</script>")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_course_name", property = "courseName"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "t_works_name", property = "tWorksName"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_content", property = "tContent")
    })
    List<WorkResponse> findMyWork(@Param("rowguid") String rowguid, @Param("courseguid") String courseguid);

    /**
     * 查看我的作品 本节
     * @param rowguid
     * @return
     */
    @Select("SELECT w.row_guid, w.t_img_url, w.t_content , w.t_works_name, c.t_course_name," +
            " cl.t_name, UNIX_TIMESTAMP( w.t_upload_date ) t_upload_date \n" +
            "FROM tb_course c, tb_class cl, tb_works w \n" +
            "WHERE w.t_course_guid=c.row_guid AND cl.row_guid=w.t_class_guid\n" +
            "AND w.t_user_guid=#{rowguid} AND cl.`row_guid`=#{classguid}")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_course_name", property = "courseName"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "t_works_name", property = "tWorksName"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_content", property = "tContent")
    })
    List<WorkResponse> findMyClassWork(@Param("rowguid") String rowguid, @Param("classguid") String classguid);

    /**
     * 查看作品详情
     * @param rowguid
     * @return
     */
    @Select("SELECT w.row_guid, w.t_img_url, w.t_content , w.t_works_name, c.t_course_name,\n" +
            " cl.t_name, UNIX_TIMESTAMP(w.t_upload_date) t_upload_date, e.`t_username`," +
            " UNIX_TIMESTAMP( w.`t_valuation_date` ) t_valuation_date,e.`t_img_url` TeacherImg\n" +
            "FROM tb_course c, tb_class cl, tb_works w, `tb_employee` e\n" +
            "WHERE w.row_guid=#{rowguid} \n" +
            "AND w.t_course_guid=c.row_guid AND cl.row_guid=w.t_class_guid AND e.id=t_teacher_guid")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_course_name", property = "courseName"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "t_works_name", property = "tWorksName"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_username", property = "teacherName"),
            @Result(column = "t_valuation_date", property = "valuationDate"),
            @Result(column = "TeacherImg", property = "TeacherImg"),
            @Result(column = "t_content", property = "tContent")
    })
    WorkResponse findWorkDetail(@Param("rowguid") String rowguid);

    /**
     * 查询我的作品 全部
     * @param useguid
     * @return
     */
    @Select("SELECT w.`row_guid` work_rowguid, w.`t_img_url`,w.`t_content`, \n" +
            "UNIX_TIMESTAMP( w.`t_upload_date`) t_upload_date,c.`t_course_name` FROM tb_works w, tb_course c\n" +
            "WHERE w.`t_user_guid`=#{useguid} AND c.`row_guid`=w.`t_course_guid`")
    @Results({
            @Result(column = "work_rowguid", property = "rowGuid"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_content", property = "tContent"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_course_name", property = "courseName")
    })
    List<MyWorkResponse> findMyWorkAll(@Param("useguid") String useguid);

    @Select("SELECT w.row_guid, w.t_img_url, w.t_content , w.t_works_name, c.t_course_name,\n" +
            " cl.t_name, UNIX_TIMESTAMP(w.t_upload_date) t_upload_date,\n" +
            " UNIX_TIMESTAMP( w.`t_valuation_date` ) t_valuation_date\n" +
            "FROM tb_course c, tb_class cl, tb_works w\n" +
            "WHERE w.row_guid=#{rowguid} AND w.t_course_guid=c.row_guid AND cl.row_guid=w.t_class_guid")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_course_name", property = "courseName"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "t_works_name", property = "tWorksName"),
            @Result(column = "t_upload_date", property = "tUploadDate"),
            @Result(column = "t_valuation_date", property = "valuationDate"),
            @Result(column = "t_content", property = "tContent")
    })
    WorkResponse findWorkNoHaveContentDetail(@Param("rowguid") String rowguid);

    /**
     * 查看我的课包
     * -- 课包状态 是否到期 是否激活
     * -- 用户课包状态 3未激活 非3已激活
     * -- 如果激活查看到期时间
     */
    // 查询课程下的所有课包 课包名称 课包学习时长  激活时长
    @Select("SELECT cp.`t_name` packageName,cp.`row_guid` packageRowguid,cp.t_price," +
            "cp.`t_study_date`,cp.`t_activate_date`," +
            "c.`row_guid` courseRowguid, c.`t_course_name`,c.t_class_type_id \n" +
            "FROM tb_course c LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.`row_guid`,c.`course_package_id`)\n" +
            "WHERE c.`row_guid`=#{courseGuid}")
    @Results({
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "packageRowguid", property = "packageRowguid"),
            @Result(column = "courseRowguid", property = "courseGuid"),
            @Result(column = "t_class_type_id", property = "classTypeId"),
            @Result(column = "t_price", property = "price"),
            @Result(column = "t_activate_date", property = "activateDate"),
            @Result(column = "t_study_date", property = "studyDate"),
            @Result(column = "packageName", property = "packageName")
    })
    List<CoursePackageResponse> findMyCoursePackageAll(@Param("courseGuid") String courseGuid);

    // 某课包的状态 到期时间
    @Select("SELECT cpu.`row_guid` cpu_rowguid, cpu.`t_course_guid`,cpu.`t_package_guid`,UNIX_TIMESTAMP( cpu.`t_due_time`) t_due_time, cpu.`t_status` cpu_status\n" +
            "FROM tb_course_package_user cpu\n" +
            "WHERE cpu.`t_package_guid`=#{packageguid} AND cpu.`user_guid`=#{userguid}")
    @Results({
            @Result(column = "t_course_guid", property = "courseGuid"),
            @Result(column = "cpu_rowguid", property = "cpuRowguid"),
            @Result(column = "t_package_guid", property = "packageRowguid"),
            @Result(column = "cpu_status", property = "cpuStatus"),
            @Result(column = "t_due_time", property = "tDueTime")
    })
    CoursePackageResponse findMyCoursePackage(@Param("userguid") String userguid,
                                              @Param("packageguid") String packageguid);

    // 某课包所包含的阶段
    @Select("SELECT cp.`row_guid` t_package_guid, CONVERT(GROUP_CONCAT(s.t_name) USING utf8) stageTname\n" +
            "FROM tb_course_package cp LEFT JOIN tb_stage s ON FIND_IN_SET(s.`row_guid`,cp.`t_stage_id`)\n" +
            "WHERE cp.`row_guid`=#{packageguid}")
    @Results({
            @Result(column = "t_package_guid", property = "packageRowguid"),
            @Result(column = "stageTname", property = "stageName")
    })
    CoursePackageResponse findPackageStage(@Param("packageguid") String packageguid);

    /**
     * 查看我的课包
     * -- 根据课包查看阶段名和课节数
     */
    @Select("SELECT cp.`row_guid` package_rowguid, cp.`t_name`, cp.`t_price` ," +
            "CONVERT(GROUP_CONCAT(s.t_name) USING utf8) StageName, cp.t_activate_date, cp.t_study_date, " +
            " CONVERT(GROUP_CONCAT(s.row_guid) USING utf8) stageRowguid, cp.t_status \n" +
            "FROM tb_course_package cp LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid, cp.`t_stage_id`) \n" +
            "WHERE cp.`row_guid`=#{rowguid} AND cp.t_status=1\n" +
            "GROUP BY cp.`id`")
    @Results({
            @Result(column = "StageName", property = "stageName"),
            @Result(column = "package_rowguid", property = "packageRowguid"),
            @Result(column = "stageRowguid", property = "stageRowguid"),
            @Result(column = "t_price", property = "price"),
            @Result(column = "t_activate_date", property = "t_activate_date"),
            @Result(column = "t_study_date", property = "t_study_date"),
            @Result(column = "t_name", property = "packageName")
    })
    CoursePackageResponse findMyCoursePackageStage(@Param("rowguid") String rowguid);

    /**
     * 阶段rowguid
     */
    @Select("SELECT cp.`row_guid`,s.row_guid stageRowGuid\n" +
            "FROM tb_course_package cp LEFT JOIN tb_stage s ON FIND_IN_SET(s.`row_guid`,cp.`t_stage_id`)\n" +
            "WHERE cp.`row_guid`=#{packageRowguid}")
    @Results({
            @Result(column = "row_guid", property = "packageRowguid"),
            @Result(column = "stageRowGuid", property = "stageRowguid")
    })
    List<CoursePackageResponse> findStageRowguid(@Param("packageRowguid") String packageRowguid);

    /**
     * 根据阶段查询课节数
     */
    @Select("SELECT LENGTH(s.t_class_id) - LENGTH(REPLACE(s.t_class_id,',',''))+1 AS classCount FROM tb_stage s WHERE s.row_guid=#{stageRowguid}")
    @Results({
            @Result(column = "classCount", property = "classCount")
    })
    Integer classCount(@Param("stageRowguid") String stageRowguid);

}
