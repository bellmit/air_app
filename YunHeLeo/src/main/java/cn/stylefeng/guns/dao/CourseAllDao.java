package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Course;
import cn.stylefeng.guns.pojo.CourseList;
import cn.stylefeng.guns.pojos.CourseAll;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface CourseAllDao extends BaseMapper<CourseAll> {

    /**
     * 查询报名成功后客服信息
     */
    @Select("SELECT c.`row_guid` rowGuid, c.`t_course_name` tCourseName, e.t_username tUsername, " +
            "e.`t_img_url` tImgUrl \n" +
            "FROM tb_course c, `tb_employee` e WHERE c.`row_guid`=#{rowguid} AND c.`t_service_id`=e.id")
    CourseAll findCourseService(@Param("rowguid") String rowguid);

    /**
     * 查询我的课程
     * @return
     */
    @Select("SELECT\n" +
            "\tc.row_guid,c.id,c.t_course_name,c.t_course_num, c.t_img_url,c.t_class_type_id,c.t_learn_count, " +
            "c.t_price,t.t_type_name,ct.t_class_name,cu.user_guid,cu.`t_package_guid`\n" +
            "FROM\n" +
            "\ttb_course c\n" +
            "LEFT JOIN tb_type t ON c.t_course_type_id = t.id\n" +
            "LEFT JOIN tb_course_type ct on  ct.id = c.t_class_type_id\n" +
            "LEFT JOIN tb_course_package_user cu on  cu.t_course_guid = c.row_guid\n" +
            "WHERE cu.user_guid = #{userGuid} and c.t_class_type_id=#{type}")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "id", property = "id"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_class_type_id", property = "tClassTypeId"),
            @Result(column = "t_package_guid", property = "packageGuid"),
            @Result(column = "user_guid", property = "tUserGuid"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseAll> findMyCourse(@Param("userGuid") String userGuid, @Param("type") Integer type);

    /**
     * 课包是否激活
     * @param packageGuid
     * @return
     */
    @Select("SELECT cu.user_guid, cu.`t_status`\n" +
            "FROM `tb_course_package_user` cu WHERE cu.`t_package_guid`=#{packageGuid} AND cu.user_guid=#{userGuid}")
    @Results({
            @Result(column = "user_guid", property = "tUserGuid"),
            @Result(column = "t_status", property = "tStatus")
    })
    CourseAll isActivate(@Param("packageGuid") String packageGuid, @Param("userGuid") String userGuid);

    @Select("select count(*) from tb_course")
    Integer courseCount();

    /**
     * 后台 查询课程列表
     * @param course
     * @return
     */
    /*@Select("<script>" +
            "SELECT co.row_guid,co.t_course_num,co.t_course_name,co.t_order_no,co.t_price," +
            "co.t_class_type_id, co.t_img_url,co.t_update_date,co.t_status,co.t_learn_count,t_course_type_id,\n" +
            "t.t_type_name,l.t_level_name,ct.t_class_name\n" +
            "FROM tb_course co,tb_type t, tb_level l,tb_course_type ct\n" +
            "WHERE co.t_class_type_id=ct.id AND co.t_level=l.id AND co.t_course_type_id=t.id " +
            " <if test='tCourseName!=null and tCourseName!=\"\" ' > AND t_course_name LIKE '%${tCourseName}%' </if>" +
            " <if test='tClassTypeId!=null and tClassTypeId!=\"\" ' > AND co.t_class_type_id = #{tClassTypeId} </if>" +
            " <if test='tCourseTypeId!=null and tCourseTypeId!=\"\" ' > AND co.t_course_type_id = #{tCourseTypeId} </if>" +
            " <if test='tStatus!=null and tStatus!=\"\" ' > AND co.t_status = #{tStatus} </if>" +
            "</script>")*/
    @Select("<script>SELECT co.row_guid,co.t_course_num,co.t_course_name,co.t_order_no,co.t_price,\n" +
            "co.t_class_type_id, co.t_img_url,co.t_update_date,co.t_status,co.t_learn_count,t_course_type_id,\n" +
            "t.t_type_name,ct.t_class_name,CONVERT(GROUP_CONCAT(l.t_level_name) USING utf8) t_level_name\n" +
            "FROM tb_type t,tb_course co LEFT JOIN tb_level l ON FIND_IN_SET(l.id,co.t_level),tb_course_type ct\n" +
            "WHERE co.t_class_type_id=ct.id  AND co.t_course_type_id=t.id \n" +
            " <if test='tCourseName!=null and tCourseName!=\"\" ' > AND t_course_name LIKE '%${tCourseName}%' </if>" +
            " <if test='tClassTypeId!=null and tClassTypeId!=\"\" or tClassTypeId==0 ' > AND co.t_class_type_id = #{tClassTypeId} </if>" +
            " <if test='tCourseTypeId!=null and tCourseTypeId!=\"\" ' > AND co.t_course_type_id = #{tCourseTypeId} </if>" +
            " <if test='tStatus!=null and tStatus!=\"\" ' > AND co.t_status = #{tStatus}  </if>" +
            " GROUP BY co.id " +
            " ORDER BY co.t_order_no DESC " +
            "</script>")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_order_no", property = "tOrderNo"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_class_name", property = "tClassName"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_course_type_id", property = "tCourseTypeId"),
            @Result(column = "t_class_type_id", property = "tClassTypeId"),
            @Result(column = "t_level_name", property = "tLevelName")
    })
    List<CourseAll> findCourseAll(Course course);

    /**
     * 查询课程报名人数
     */
    @Select("SELECT COUNT(cu.t_course_guid) courseCount,cu.t_course_guid tCourseGuid" +
            " FROM tb_course_user cu WHERE cu.t_course_guid=#{tCourseGuid} GROUP BY cu.t_course_guid")
    @Results({
            @Result(column = "courseCount", property = "buyCount"),
            @Result(column = "tCourseGuid", property = "tCourseGuid")
    })
    CourseAll buyCourseCount(@Param("tCourseGuid") String tCourseGuid);

    /**
     * 班型+最新
     * @param type
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num,c.t_price, c.t_img_url " +
            ", c.t_update_date, c.t_learn_count, c.t_class_type_id," +
            "t.t_type_name,ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND ct.id=c.t_class_type_id AND t_class_type_id=#{type} AND c.t_status=0\n" +
            "ORDER BY c.t_update_date DESC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_type_id", property = "tClassTypeId"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseNew(@Param("type") int type);

    /**
     * 班型+最新 [根据用户选择的阶段]
     * @param type
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num,c.t_price, c.t_img_url " +
            ", c.t_update_date, c.t_learn_count, c.t_class_type_id," +
            "t.t_type_name,ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND ct.id=c.t_class_type_id AND t_class_type_id=#{type} " +
            "AND c.t_status=0 " +
            "AND FIND_IN_SET(#{showStageId},c.`t_level`) "+//AND c.t_level=#{showStageId}\n" +
            "ORDER BY c.t_update_date DESC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseShowStageNew(@Param("type") int type, @Param("showStageId") Integer showStageId);

    /**
     * 班型+最热
     * @param type
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num,c.t_price, c.t_img_url,c.t_class_type_id ," +
            "c.t_update_date, c.t_learn_count, " +
            "t.t_type_name,ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND ct.id=c.t_class_type_id AND t_class_type_id=#{type} AND c.t_status=0\n" +
            "ORDER BY c.t_order_no ASC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseHot(@Param("type") int type);

    /**
     * 课程类别id查询 最新
     * @param type
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num, c.t_price, c.t_class_type_id, " +
            "c.t_learn_count, c.t_img_url, " +
            "t.t_type_name, ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND c.t_course_type_id=#{type} AND c.t_status=0 AND ct.id=c.t_class_type_id " +
            //"AND c.t_level=#{showStageId}\n" +
            " AND FIND_IN_SET(#{showStageId},c.`t_level`) "+
            "ORDER BY c.t_update_date DESC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseShowStageType(@Param("type") int type, @Param("showStageId") Integer showStageId);

    /**
     * 课程类别id查询 最新
     * @param type
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num, c.t_price, c.t_class_type_id, " +
            "c.t_learn_count, c.t_img_url, " +
            "t.t_type_name, ct.t_class_name\n" +
            "FROM tb_course c, tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND c.t_course_type_id=#{type} AND c.t_status=0 AND ct.id=c.t_class_type_id \n" +
            "ORDER BY c.t_update_date DESC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseType(@Param("type") int type);

    /**
     * 课程类别id 最热
   * @param type
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num, c.t_class_type_id, c.t_price, c.t_learn_count," +
            " c.t_img_url, t.t_type_name, ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND c.t_course_type_id=#{type} AND c.t_status=0 AND ct.id=c.t_class_type_id \n" +
            "ORDER BY c.t_order_no ASC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseTypeHot(@Param("type") int type);

    /**
     * 根据课程rowguid查询课程详情 基本信息
     */
    @Select("SELECT c.row_guid,c.t_course_num,c.t_course_name,c.t_price, c.t_class_type_id, c.t_img_url, " +
            "c.t_course_introduce,c.t_learn_count,c.t_course_type_id,c.t_prompt, c.t_painting ,t.t_type_name\n" +
            "FROM tb_course c,tb_type t\n" +
            "WHERE c.row_guid=#{RowGuid} AND c.t_course_type_id=t.id")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_course_introduce", property = "tCourseIntroduce"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_prompt", property = "tPrompt"),
            @Result(column = "t_painting", property = "tPainting"),
            @Result(column = "t_class_type_id", property = "tClassTypeId"),
            @Result(column = "t_course_type_id", property = "tCourseTypeId")
    })
    CourseAll courseDetail(@Param("RowGuid") String RowGuid);

    /**
     * 根据课程rowguid查询课程评价
     */
    @Select("SELECT ec.row_guid,ec.t_content,ec.t_score,u.t_username,u.t_head_img\n" +
            "FROM tb_course c, tb_user u, tb_eval_course ec\n" +
            "WHERE c.row_guid=#{RowGuid} AND c.row_guid=ec.course_guid AND ec.t_user_guid=u.row_guid")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_content", property = "tContent"),
            @Result(column = "t_score", property = "tScore"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_head_img", property = "tImgUrl")
    })
    List<CourseAll> evaluation(@Param("RowGuid") String RowGuid);

    /**
     * 某课程有多少人评价
     */
    @Select("SELECT COUNT(*)\n" +
            "FROM tb_course c, tb_user u, tb_eval_course ec\n" +
            "WHERE c.row_guid=#{RowGuid} AND c.row_guid=ec.course_guid AND ec.t_user_guid=u.row_guid")
    int countEvaluation(@Param("RowGuid") String RowGuid);

    /**
     * 计算某课程总分数
     * SUM(ec.t_score)
     */
    @Select("SELECT ROUND( AVG(ec.t_score),2) \n" +
            "FROM tb_course c, tb_user u, tb_eval_course ec\n" +
            "WHERE c.row_guid=#{RowGuid} AND c.row_guid=ec.course_guid AND ec.t_user_guid=u.row_guid")
    Double sumScore(@Param("RowGuid") String RowGuid);

    /**
     *
     * app搜索课程
     * @param courseName
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num, c.t_class_type_id, t.t_type_name,ct.t_class_name," +
            "c.t_course_type_id,c.t_learn_count,c.t_img_url,c.t_price \n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND ct.id=c.t_class_type_id AND c.t_course_name LIKE '%${courseName}%' " +
            " AND c.t_status=0")
    List<CourseAll> search(@Param("courseName") String courseName);

    /**
     * 根据阶段 类型 查询课程
     * @param type
     * @param stageId
     * @return
     */
    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num,c.t_price, c.t_img_url\n" +
            ", c.t_update_date, c.t_learn_count, c.t_class_type_id,\n" +
            "t.t_type_name,ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND ct.id=c.t_class_type_id AND t_class_type_id=#{type} AND c.t_status=0 " +
//            "AND c.t_level=#{stageId}\n" +
            " AND FIND_IN_SET(#{stageId},c.`t_level`) "+
            "ORDER BY c.t_update_date DESC")
    List<CourseAll> courseStageNew(@Param("type") int type, @Param("stageId") Integer stageId);

    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num, c.t_price, c.t_class_type_id, c.t_learn_count, c.t_img_url,\n" +
            "t.t_type_name\n" +
            "FROM tb_course c,tb_type t\n" +
            "WHERE c.t_course_type_id=t.id AND c.t_course_type_id=#{type} AND c.t_status=0 " +
            //"AND c.t_level=#{stageId}\n" +
            " AND FIND_IN_SET(#{stageId},c.`t_level`) "+
            "ORDER BY c.t_update_date DESC")
    List<CourseAll> courseStageType(@Param("type") int type, @Param("stageId") Integer stageId);

    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num,c.t_price, c.t_img_url,c.t_class_type_id ,\n" +
            "c.t_update_date, c.t_learn_count,  t.t_type_name,ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND ct.id=c.t_class_type_id AND t_class_type_id=#{type} AND c.t_status=0 " +
            //"AND c.t_level=#{stageId} \n" +
            " AND FIND_IN_SET(#{stageId},c.`t_level`) "+
            "ORDER BY c.t_order_no ASC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseStageHot(@Param("type") int type, @Param("stageId") Integer stageId);

    @Select("SELECT c.row_guid, c.id, c.t_course_name,c.t_course_num, c.t_class_type_id, c.t_price, c.t_learn_count,\n" +
            " c.t_img_url, t.t_type_name, ct.t_class_name\n" +
            "FROM tb_course c,tb_type t, tb_course_type ct\n" +
            "WHERE c.t_course_type_id=t.id AND c.t_course_type_id=#{type} AND c.t_status=0 " +
            //"AND c.t_level=#{stageId} " +
            " AND FIND_IN_SET(#{stageId},c.`t_level`) "+
            "AND ct.id=c.t_class_type_id \n" +
            "ORDER BY c.t_order_no ASC")
    @Results({
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_course_num", property = "tCourseNum"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "t_price", property = "tPrice"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_type_name", property = "tTypeName"),
            @Result(column = "t_learn_count", property = "tLearnCount"),
            @Result(column = "t_img_url", property = "tImgUrl"),
            @Result(column = "t_class_name", property = "tClassName")
    })
    List<CourseList> courseStageTypeHot(@Param("type") int type, @Param("stageId") Integer stageId);

    @Select("SELECT COUNT(cu.t_course_guid) FROM tb_course_user cu WHERE cu.t_course_guid=#{rowGuid} GROUP BY cu.t_course_guid")
    Integer findBuyCourseCount(@Param("rowGuid") String rowGuid);

    /*@Select("SELECT LENGTH(co.class_id)-LENGTH(REPLACE(co.class_id,',',''))+1 as ClassCount\n" +
            "FROM tb_course co WHERE co.row_guid=#{rowGuid}")*/
    @Select("SELECT COUNT(*) studyClassCount\n" +
            "FROM `tb_course_package` c LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,c.t_stage_id) \n" +
            "LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,s.t_class_id)\n" +
            "WHERE c.row_guid=#{rowGuid}")
    @Results({
            @Result(column = "studyClassCount", property = "studyClassCount")
    })
    Integer findClassCount(@Param("rowGuid") String rowGuid);

    /*@Select("SELECT LENGTH(co.stage_id)-LENGTH(REPLACE(co.stage_id,',',''))+1 as StageCount\n" +
            "FROM tb_course co\n" +
            "WHERE co.row_guid=#{rowGuid}")*/
    @Select("SELECT COUNT(*) stageClassCount\n" +
            "FROM `tb_course` c LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid, c.`stage_id`)\n" +
            "LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,s.t_class_id)\n" +
            "WHERE c.row_guid=#{rowGuid} AND cl.t_status=0")
    Integer findStageCount(@Param("rowGuid") String rowGuid);

}
