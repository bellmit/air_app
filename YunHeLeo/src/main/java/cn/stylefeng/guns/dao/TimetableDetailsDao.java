package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Timetable;
import cn.stylefeng.guns.pojo.UserLinkStatus;
import cn.stylefeng.guns.pojos.TimetableDetails;
import cn.stylefeng.guns.pojos.TimetableDetailsResponse;
import cn.stylefeng.guns.vo.ClassLinkVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TimetableDetailsDao {

    /**
     * 课表
     * @param userGuid
     * @return
     */
    /*@Select("SELECT t.row_guid TableRowGuid,cl.row_guid ClassRowGuid, t.t_weeks, unix_timestamp( t.t_create_date ) t_create_date, " +
            "c.t_course_name, cp.t_name PackageName, s.t_name StageName, cl.t_name ClassName ,c.t_img_url, l.study_status\n" +
            "FROM tb_timetable t, tb_course c, tb_course_package cp LEFT JOIN tb_stage s\n" +
            "ON FIND_IN_SET(s.row_guid,cp.t_stage_id) LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,s.t_class_id), tb_link l\n" +
            "WHERE t.user_guid=#{userGuid} AND t.t_course_guid=c.row_guid AND t.t_package_guid=cp.row_guid" +
            " AND l.t_class_id=cl.row_guid")*/
    @Select("SELECT t.row_guid TableRowGuid,cl.row_guid ClassRowGuid, t.t_weeks, unix_timestamp( t.t_create_date ) t_create_date,\n" +
            "c.t_course_name, cl.t_name ClassName ,c.t_img_url\n" +
            "FROM tb_timetable t, tb_course c, tb_class cl\n" +
            "WHERE t.user_guid=#{userGuid} AND t.t_course_guid=c.row_guid AND cl.id=t.t_class_id AND DATE_FORMAT(t.t_create_date,'%Y-%m')=date_format(now(),'%Y-%m')")
    @Results({
            @Result(column = "TableRowGuid", property = "TableRowGuid"),
            @Result(column = "ClassRowGuid", property = "ClassRowGuid"),
            @Result(column = "t_weeks", property = "tWeeks"),
            @Result(column = "t_create_date", property = "tStudyDate"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "ClassName", property = "tClassName"),
            @Result(column = "t_img_url", property = "tImgUrl")
    })
    public List<TimetableDetailsResponse> findTimetableUser(@Param("userGuid") String userGuid);

    /**
     * 根据课节rowguid查询环节
     * @return
     */
    @Select("SELECT l.row_guid LinkRowGuid, l.t_link_name LinkName,cl.row_guid classRowguid, cl.t_name ClassName, " +
            "l.t_video_url VideoUrl, l.t_video_id, l.study_status\n" +
            "FROM tb_class cl, tb_link l WHERE cl.row_guid=l.t_class_id AND cl.row_guid=#{rowGuid}")
    @Results({
            @Result(column = "LinkRowGuid", property = "linkRowGuid"),
            @Result(column = "LinkName", property = "tLinkName"),
            @Result(column = "classRowguid", property = "classRowGuid"),
            @Result(column = "ClassName", property = "tClassName"),
            @Result(column = "study_status", property = "studyStatus"),
            @Result(column = "VideoUrl", property = "videoUrl"),
            @Result(column = "t_video_id", property = "videoId")
    })
    public List<ClassLinkVo> findClassLink(@Param("rowGuid") String rowGuid);

    /**
     * 判断环节是否已学习
     */
    @Select("SELECT uls.`user_guid`,uls.`class_guid`,uls.`link_guid`\n" +
            "FROM user_link_status uls WHERE uls.`user_guid`=#{userguid} " +
            "AND uls.`link_guid`=#{linkguid} limit 1")
    UserLinkStatus findLinkStudyStatus(@Param("userguid") String userguid, @Param("linkguid") String linkguid);

    /**
     * 当前时间之前
     * @param rowguid
     * @return
     */
    @Select("SELECT t.row_guid TableRowGuid,cl.row_guid ClassRowGuid, t.t_weeks, unix_timestamp( t.t_create_date ) t_create_date,\n" +
            "c.t_course_name, cl.t_name ClassName ,cl.t_img_url, cl.study_status, c.`row_guid` courseRowGuid \n" +
            "FROM tb_timetable t, tb_course c, tb_class cl \n" +
            "WHERE t.user_guid=#{userGuid} AND t.t_course_guid=c.row_guid AND cl.id=t.t_class_id" +
            " AND t.t_create_date<NOW() ORDER BY t.t_create_date DESC")
    @Results({
            @Result(column = "TableRowGuid", property = "TableRowGuid"),
            @Result(column = "ClassRowGuid", property = "ClassRowGuid"),
            @Result(column = "courseRowGuid", property = "courseRowGuid"),
            @Result(column = "t_weeks", property = "tWeeks"),
            @Result(column = "t_create_date", property = "tStudyDate"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "ClassName", property = "tClassName"),
            @Result(column = "study_status", property = "studyStatus"),
            @Result(column = "t_img_url", property = "tImgUrl")
    })
    List<TimetableDetailsResponse> findTimetableBeforeUser(String rowguid);

    /**
     * 当前时间之后
     * @param rowguid
     * @return
     */
    @Select("SELECT t.row_guid TableRowGuid,cl.row_guid ClassRowGuid, t.t_weeks, c.`row_guid` courseRowGuid," +
            " unix_timestamp( t.t_create_date ) t_create_date,\n" +
            "c.t_course_name, cl.t_name ClassName ,cl.t_img_url, cl.study_status\n" +
            "FROM tb_timetable t, tb_course c, tb_class cl \n" +
            "WHERE t.user_guid=#{userGuid} AND t.t_course_guid=c.row_guid AND cl.id=t.t_class_id" +
            " AND t.t_create_date>=NOW() ORDER BY t.t_create_date")
    @Results({
            @Result(column = "TableRowGuid", property = "TableRowGuid"),
            @Result(column = "ClassRowGuid", property = "ClassRowGuid"),
            @Result(column = "courseRowGuid", property = "courseRowGuid"),
            @Result(column = "t_weeks", property = "tWeeks"),
            @Result(column = "t_create_date", property = "tStudyDate"),
            @Result(column = "t_course_name", property = "tCourseName"),
            @Result(column = "ClassName", property = "tClassName"),
            @Result(column = "t_img_url", property = "tImgUrl")
    })
    List<TimetableDetailsResponse> findTimetableAfterUser(String rowguid);

    /**
     * 判断课节是否已学习
     */
    @Select("SELECT uls.`user_guid`,uls.`class_guid`\n" +
            "FROM user_link_status uls WHERE uls.`user_guid`=#{userguid} AND uls.`class_guid`=#{classguid} LIMIT 1")
    UserLinkStatus findUserLinkStatus(@Param("userguid") String userguid, @Param("classguid") String classguid);

    /**
     * 修改学习状态 0未开始课节 1开始学习课节
     * @param rowguid
     */
    @Update("UPDATE tb_class SET study_status=1 WHERE row_guid=#{rowguid}")
    void updateStudyStatus(@Param("rowguid") String rowguid);

    /**
     * 某用户 某课程是否加入课表
     * @param userguid
     * @param rowGuid
     * @return
     */
    @Select("SELECT t.`row_guid` rowGuid ,t.`user_guid` userGuid,t.`t_course_guid` tCourseGuid,t.`t_weeks` tWeeks " +
            "FROM `tb_timetable` t WHERE t.`user_guid`=#{userguid} AND `t_course_guid`=#{rowGuid} LIMIT 1")
    @Results({
            @Result(column = "rowGuid", property = "rowGuid"),
            @Result(column = "userGuid", property = "userGuid"),
            @Result(column = "tCourseGuid", property = "tCourseGuid"),
            @Result(column = "tWeeks", property = "tWeeks")
    })
    Timetable isInClassTable(@Param("userguid") String userguid, @Param("rowGuid") String rowGuid);
}
