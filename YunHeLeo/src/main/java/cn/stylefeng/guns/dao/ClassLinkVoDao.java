package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojos.ClassDedails;
import cn.stylefeng.guns.pojos.StageClass;
import cn.stylefeng.guns.vo.ClassLinkVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClassLinkVoDao extends BaseMapper<ClassLinkVo> {

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

}
