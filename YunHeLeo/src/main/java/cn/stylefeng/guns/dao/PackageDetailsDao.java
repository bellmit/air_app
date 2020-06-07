package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.PackageDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PackageDetailsDao extends BaseMapper<PackageDetails> {

    @Select("SELECT COUNT(cp.row_guid) FROM tb_course c LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.row_guid,c.course_package_id)\n" +
            "WHERE cp.t_status!=3 AND c.row_guid=#{rowGuid}")
    Integer packageCount(@Param("rowGuid") String rowGuid);

    /**
     * 查询某课程下所有的课包 包含阶段
     * @param rowGuid
     * @return
     */
    @Select("SELECT cp.row_guid packageRowGuid,cp.t_name packageName,cp.t_stage_id," +
            "CONVERT(GROUP_CONCAT(s.t_name) USING utf8) stageName,\n" +
            "cp.t_activate_date,cp.t_price,cp.t_study_date,cp.t_status\n" +
            "FROM tb_course c LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.row_guid,c.course_package_id) \n" +
            "LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,cp.t_stage_id)\n" +
            "WHERE c.row_guid=#{rowGuid} AND cp.t_status!=3 \n" +
            "GROUP BY cp.id")
    @Results({
            @Result(column = "packageRowGuid", property = "cprowGuid"),
            @Result(column = "packageName", property = "packageName"),
            @Result(column = "stageName", property = "stageName"),
            @Result(column = "t_activate_date", property = "tActivateDate"),
            @Result(column = "t_study_date", property = "tStudyDate"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_stage_id", property = "stageId"),
            @Result(column = "t_price", property = "tPrice")
    })
    List<PackageDetails> findCPAll(@Param("rowGuid") String rowGuid);

    /**
     * 查询某课程下所有的课包 包含阶段 app查询已发布的课包
     * @param rowGuid
     * @return
     */
    @Select("SELECT cp.row_guid packageRowGuid,cp.t_name packageName,cp.t_stage_id," +
            "CONVERT(GROUP_CONCAT(s.t_name) USING utf8) stageName,\n" +
            "cp.t_activate_date,cp.t_price,cp.t_study_date,cp.t_status\n" +
            "FROM tb_course c LEFT JOIN tb_course_package cp ON FIND_IN_SET(cp.row_guid,c.course_package_id) \n" +
            "LEFT JOIN tb_stage s ON FIND_IN_SET(s.row_guid,cp.t_stage_id)\n" +
            "WHERE c.row_guid=#{rowGuid} AND cp.t_status=1 \n" +
            "GROUP BY cp.id")
    @Results({
            @Result(column = "packageRowGuid", property = "cprowGuid"),
            @Result(column = "packageName", property = "packageName"),
            @Result(column = "stageName", property = "stageName"),
            @Result(column = "t_activate_date", property = "tActivateDate"),
            @Result(column = "t_study_date", property = "tStudyDate"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_stage_id", property = "stageId"),
            @Result(column = "t_price", property = "tPrice")
    })
    List<PackageDetails> findAppCPAll(@Param("rowGuid") String rowGuid);

    /**
     * 查询课包状态  查询已发布课包的价格
     * @param rowGuid
     * @return
     */
    @Select("SELECT cp.row_guid packageRowGuid,cp.t_name packageName,cp.t_stage_id," +
            "cp.t_activate_date,cp.t_price,cp.t_study_date,cp.t_status " +
            "FROM tb_course_package cp " +
            "WHERE cp.row_guid=#{rowGuid} AND cp.t_status=1 ")
    @Results({
            @Result(column = "packageRowGuid", property = "cprowGuid"),
            @Result(column = "packageName", property = "packageName"),
            @Result(column = "t_activate_date", property = "tActivateDate"),
            @Result(column = "t_study_date", property = "tStudyDate"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_stage_id", property = "stageId"),
            @Result(column = "t_price", property = "tPrice")
    })
    PackageDetails findPackageStatus(@Param("rowGuid") String rowGuid);

    /**
     * 查询判断当前用户是否购买过课包  购买过的课包
     * 课包状态 0未开始 1正在学 2已到期 3未激活
     */
    /*@Select("SELECT cpu.row_guid, cpu.t_package_guid,cpu.t_status\n" +
            "FROM tb_course_package_user cpu WHERE cpu.user_guid=#{userGuid} AND cpu.t_course_guid=#{rowGuid}")
    @Results({
            @Result(column = "row_guid", property = "cpuRowGuid"),
            @Result(column = "t_package_guid", property = "tPackageGuid"),
            @Result(column = "t_status", property = "tStatus")
    })*/

    @Select("SELECT CONVERT(cpu.`t_class_package_guid` USING utf8) t_package_guid \n" +
            "FROM `tb_order` cpu WHERE cpu.`t_user_guid`=#{userGuid} AND cpu.`t_course_guid`=#{rowGuid} AND cpu.`t_order_status`!=3 ")
    @Results({
            @Result(column = "t_package_guid", property = "tPackageGuid")
    })
    List<PackageDetails> findUserPackage(@Param("userGuid") String userGuid, @Param("rowGuid") String rowGuid);

}
