package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.TClass;
import cn.stylefeng.guns.pojos.ClassDedails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassDedailsDao extends BaseMapper<ClassDedails> {

    @Select("SELECT cla.id, cla.row_guid, cla.t_name, CONVERT(GROUP_CONCAT(e.t_username) USING utf8) tearcher_username," +
            "cla.t_istest,cla.t_img_url,cla.t_update_date \n" +
            "FROM tb_class cla LEFT JOIN tb_employee e ON FIND_IN_SET(e.id,cla.t_teacher)\n" +
            "WHERE cla.row_guid=#{rowGuid} \n" +
            "GROUP BY cla.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "tearcher_username", property = "tUsername"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_img_url",property = "imgUrl")
    })
    ClassDedails findByClass1(@Param("rowGuid") String rowGuid);

    @Select("SELECT cla.id, cla.row_guid, cla.t_name class_name, cla.t_istest, CONVERT(GROUP_CONCAT(k.t_knowledge_name) USING utf8) knowledge_name,cla.t_img_url,cla.t_update_date\n" +
            "FROM tb_class cla LEFT JOIN tb_knowledge k ON FIND_IN_SET(k.id,cla.t_point)\n" +
            "WHERE cla.row_guid=#{rowGuid}\n" +
            "GROUP BY cla.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "knowledge_name", property = "tKnowledgeName"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_img_url",property = "imgUrl")
    })
    ClassDedails findByClass2(@Param("rowGuid") String rowGuid);

    @Select("SELECT cla.id, cla.row_guid, cla.t_name, cla.t_istest, CONVERT(GROUP_CONCAT(cr.t_creation_name) USING utf8) creation_name,cla.t_img_url,cla.t_update_date\n" +
            "FROM tb_class cla LEFT JOIN tb_creation cr ON FIND_IN_SET(cr.id,cla.t_methods)\n" +
            "WHERE cla.row_guid=#{rowGuid}\n" +
            "GROUP BY cla.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_name", property = "className"),
            @Result(column = "creation_name", property = "tCreationName"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_img_url",property = "imgUrl")
    })
    ClassDedails findByClass3(@Param("rowGuid") String rowGuid);

    @Select("SELECT cla.id, cla.row_guid, e.`t_username` as t_update_man\n" +
            "FROM tb_class cla, tb_employee e WHERE cla.row_guid=#{rowGuid} AND cla.t_update_man=e.id")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_update_man", property = "tUpdateMan"),
            @Result(column = "row_guid", property = "rowGuid")
    })
    ClassDedails findByClass4(@Param("rowGuid") String rowGuid);

    /**
     * 根据课程rowguid免费班查询课节
     */
    @Select("SELECT cla.id ClassId,cla.row_guid ClassRowGuid,cla.t_name ClassName,cla.t_istest IsTest,cla.t_img_url,\n" +
            "e.t_username UserName,co.t_course_name CourseName,cla.t_update_date UpdateDate, " +
            " cla.t_point,cla.t_methods,cla.t_teacher,cla.t_status \n" +
            "FROM tb_course co LEFT JOIN tb_class cla ON FIND_IN_SET(cla.row_guid,co.class_id),tb_employee e\n" +
            "WHERE co.row_guid=#{rowGuid} AND co.t_class_type_id=0 AND cla.t_update_man=e.id")
    @Results({
            @Result(column = "ClassId", property = "id"),
            @Result(column = "ClassRowGuid", property = "classRowGuid"),
            @Result(column = "ClassName", property = "className"),
            @Result(column = "IsTest", property = "tIstest"),
            @Result(column = "UserName", property = "tUsername"),
            @Result(column = "CourseName", property = "tCourseName"),
            @Result(column = "UpdateDate", property = "tUpdateDate"),
            @Result(column = "t_img_url",property = "imgUrl"),
            @Result(column = "t_methods", property = "tCreationName"),
            @Result(column = "t_point", property = "tKnowledgeName"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_teacher", property = "tTeacher")
    })
    List<ClassDedails> findFreeClass(@Param("rowGuid") String rowGuid);

    /**
     * 根据课程rowguid短期班查询课节
     */
    @Select("SELECT cla.id ClassId,cla.row_guid ClassRowGuid,cla.t_name ClassName,cla.t_istest IsTest,cla.t_img_url,\n" +
            "e.t_username UserName,co.t_course_name CourseName,cla.t_update_date UpdateDate, " +
            " cla.t_point,cla.t_methods,cla.t_teacher,cla.t_status \n" +
            "FROM tb_course co LEFT JOIN tb_class cla ON FIND_IN_SET(cla.row_guid,co.class_id),tb_employee e\n" +
            "WHERE co.row_guid=#{rowGuid} AND co.t_class_type_id=2 AND cla.t_update_man=e.id")
    @Results({
            @Result(column = "ClassId", property = "id"),
            @Result(column = "ClassRowGuid", property = "classRowGuid"),
            @Result(column = "ClassName", property = "className"),
            @Result(column = "IsTest", property = "tIstest"),
            @Result(column = "UserName", property = "tUsername"),
            @Result(column = "CourseName", property = "tCourseName"),
            @Result(column = "UpdateDate", property = "tUpdateDate"),
            @Result(column = "t_methods", property = "tCreationName"),
            @Result(column = "t_point", property = "tKnowledgeName"),
            @Result(column = "t_teacher", property = "tTeacher"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_img_url",property = "imgUrl")
    })
    List<ClassDedails> findShortClass(@Param("rowGuid") String rowGuid);

    /**
     * 查询当前课程下的总课节数量
     * @param rowGuid
     * @return
     */
    @Select("SELECT COUNT(*)\n" +
            "FROM tb_course c LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,c.class_id)\n" +
            "WHERE c.row_guid=#{rowGuid}")
    Integer findClassTotalCount(@Param("rowGuid") String rowGuid);

    /**
     * 查询当前课程下的课节已学课节数量
     * @param rowGuid
     * @return
     */
    @Select("SELECT COUNT(*)\n" +
            "FROM tb_course c LEFT JOIN tb_class cl ON FIND_IN_SET(cl.row_guid,c.class_id)\n" +
            "WHERE cl.study_status=1 AND c.row_guid=#{rowGuid}")
    Integer findClassStudyCount(@Param("rowGuid") String rowGuid);

    /**
     * 更新范画
     * @param tClass
     */
    @Update("UPDATE tb_class SET t_img_url=#{imgUrl}, t_update_date=#{updateDate}, t_update_man=#{updateMan} " +
            "WHERE row_guid=#{rowGuid}")
    void classAddImage(TClass tClass);

    /**
     * 根据rowguid查询课节
     * @param rowGuid
     * @return
     */
    @Select("SELECT cla.id ClassId,cla.row_guid ClassRowGuid,cla.t_name ClassName,cla.t_istest IsTest,cla.t_img_url," +
            "cla.t_update_date UpdateDate,cla.t_status,\n" +
            "cla.t_point,cla.t_methods,cla.t_teacher FROM tb_class cla WHERE cla.row_guid=#{rowGuid}")
    @Results({
            @Result(column = "ClassId", property = "id"),
            @Result(column = "ClassRowGuid", property = "classRowGuid"),
            @Result(column = "ClassName", property = "className"),
            @Result(column = "IsTest", property = "tIstest"),
            @Result(column = "UpdateDate", property = "tUpdateDate"),
            @Result(column = "t_img_url",property = "imgUrl"),
            @Result(column = "t_methods", property = "tCreationName"),
            @Result(column = "t_point", property = "tKnowledgeName"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_teacher", property = "tTeacher")
    })
    List<ClassDedails> findByRowGuidClass(@Param("rowGuid") String rowGuid);

    // 状态 0 已发布 1 未发布 2 已下架
    @Update("UPDATE tb_class SET t_status=2 WHERE row_guid=#{rowGuid}")
    void downClass(@Param("rowGuid") String rowGuid);

    // 状态 0 已发布 1 未发布 2 已下架
    @Update("UPDATE tb_class SET t_status=0 WHERE row_guid=#{rowGuid}")
    void upClass(String rowGuid);

    @Update("UPDATE tb_class SET t_istest=1 WHERE row_guid=#{rowGuid}")
    void isTestTrueClass(String rowGuid);

    @Update("UPDATE tb_class SET t_istest=0 WHERE row_guid=#{rowGuid}")
    void isTestFalseClass(String rowGuid);
}
