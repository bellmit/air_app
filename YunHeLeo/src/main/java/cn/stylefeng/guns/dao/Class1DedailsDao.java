package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.Class1Dedails;
import cn.stylefeng.guns.pojos.ClassDedails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface Class1DedailsDao extends BaseMapper<Class1Dedails> {

    @Select("SELECT cla.id, cla.row_guid, cla.t_name, CONVERT(GROUP_CONCAT(e.t_username) USING utf8) tearcher_username\n" +
            "FROM tb_class cla LEFT JOIN tb_employee e ON FIND_IN_SET(e.id,cla.t_teacher)\n" +
            "WHERE cla.row_guid=#{rowGuid}\n" +
            "GROUP BY cla.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "tearcher_username", property = "tUsername"),
            @Result(column = "row_guid", property = "rowGuid")
    })
    Class1Dedails findByClass1(@Param("rowGuid") String rowGuid);

    @Select("SELECT cla.id, cla.row_guid, cla.t_name class_name, cla.t_istest, CONVERT(GROUP_CONCAT(k.t_knowledge_name) USING utf8) knowledge_name\n" +
            "FROM tb_class cla LEFT JOIN tb_knowledge k ON FIND_IN_SET(k.id,cla.t_point)\n" +
            "WHERE cla.row_guid=#{rowGuid}\n" +
            "GROUP BY cla.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "knowledge_name", property = "tKnowledgeName"),
            @Result(column = "row_guid", property = "rowGuid")
    })
    Class1Dedails findByClass2(@Param("rowGuid") String rowGuid);

    @Select("SELECT cla.id, cla.row_guid, cla.t_name, cla.t_istest, CONVERT(GROUP_CONCAT(cr.t_creation_name) USING utf8) creation_name\n" +
            "FROM tb_class cla LEFT JOIN tb_creation cr ON FIND_IN_SET(cr.id,cla.t_methods)\n" +
            "WHERE cla.row_guid=#{rowGuid}\n" +
            "GROUP BY cla.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "creation_name", property = "tCreationName"),
            @Result(column = "row_guid", property = "rowGuid")
    })
    Class1Dedails findByClass3(@Param("rowGuid") String rowGuid);

    /**
     * 根据课程rowguid免费班查询课节
     */
    @Select("SELECT cla.id ClassId,cla.row_guid ClassRowGuid,cla.t_name ClassName,cla.t_istest IsTest,cla.t_img_url,\n" +
            "e.t_username UserName,co.t_course_name CourseName, co.t_painting ,cla.t_update_date UpdateDate\n" +
            "FROM tb_course co LEFT JOIN tb_class cla ON FIND_IN_SET(cla.row_guid,co.class_id),tb_employee e\n" +
            "WHERE co.row_guid=#{rowGuid} AND co.t_class_type_id=0 AND cla.t_update_man=e.id AND cla.`t_status`=0 ")
    @Results({
            @Result(column = "ClassId", property = "id"),
            @Result(column = "ClassRowGuid", property = "classRowGuid"),
            @Result(column = "ClassName", property = "className"),
            @Result(column = "IsTest", property = "tIstest"),
            @Result(column = "UserName", property = "tUsername"),
            @Result(column = "CourseName", property = "tCourseName"),
            @Result(column = "t_img_url", property = "imgUrl"),
            @Result(column = "t_painting", property = "tPainting"),
            @Result(column = "UpdateDate", property = "tUpdateDate")
    })
    List<Class1Dedails> findFreeClass(@Param("rowGuid") String rowGuid);

    /**
     * 根据课程rowguid短期班查询课节
     */
    @Select("SELECT cla.id ClassId,cla.row_guid ClassRowGuid,cla.t_name ClassName,cla.t_istest IsTest,cla.t_img_url,\n" +
            "e.t_username UserName,co.t_course_name CourseName, co.t_painting ,cla.t_update_date UpdateDate\n" +
            "FROM tb_course co LEFT JOIN tb_class cla ON FIND_IN_SET(cla.row_guid,co.class_id),tb_employee e\n" +
            "WHERE co.row_guid=#{rowGuid} AND co.t_class_type_id=2 AND cla.t_update_man=e.id AND cla.`t_status`=0 ")
    @Results({
            @Result(column = "ClassId", property = "id"),
            @Result(column = "ClassRowGuid", property = "classRowGuid"),
            @Result(column = "ClassName", property = "className"),
            @Result(column = "IsTest", property = "tIstest"),
            @Result(column = "UserName", property = "tUsername"),
            @Result(column = "CourseName", property = "tCourseName"),
            @Result(column = "t_img_url", property = "imgUrl"),
            @Result(column = "t_painting", property = "tPainting"),
            @Result(column = "UpdateDate", property = "tUpdateDate")
    })
    List<Class1Dedails> findShortClass(@Param("rowGuid") String rowGuid);

    /**
     * 查询当前课程下的总课节数量
     * @param rowGuid
     * @return
     */
    @Select("SELECT COUNT(cl.study_status)\n" +
            "FROM tb_course c LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,c.class_id)\n" +
            "WHERE c.row_guid=#{rowGuid}")
    Integer findClassTotalCount(@Param("rowGuid") String rowGuid);

    /**
     * 查询当前课程下的课节已学课节数量
     * @param rowGuid
     * @return
     */
    @Select("SELECT COUNT(cl.study_status)\n" +
            "FROM tb_course c LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,c.class_id)\n" +
            "WHERE cl.study_status=1 AND c.row_guid=#{rowGuid}")
    Integer findClassStudyCount(@Param("rowGuid") String rowGuid);

}
