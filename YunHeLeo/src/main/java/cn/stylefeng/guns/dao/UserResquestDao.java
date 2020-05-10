package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.User;
import cn.stylefeng.guns.pojo.UserResquest;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserResquestDao extends BaseMapper<UserResquest> {

    @Select("select id,row_guid,t_username,t_mobile,t_password,t_status,t_children_date,t_register_date,t_student_no,t_head_img" +
            " from tb_user where t_mobile=#{tMobile}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "row_guid", property = "rowGuid"),
            @Result(column = "t_username", property = "tUsername"),
            @Result(column = "t_mobile", property = "tMobile"),
            @Result(column = "t_status", property = "tStatus"),
            @Result(column = "t_children_date", property = "tChildrenDate"),
            @Result(column = "t_register_date", property = "tRegisterDate"),
            @Result(column = "t_student_no", property = "tStudentNo"),
            @Result(column = "t_head_img", property = "tHeadImg"),
            @Result(column = "t_password", property = "tPassword")
    })
    UserResquest findByMobile(@Param("tMobile") String tMobile);

    @Update("UPDATE tb_user SET show_stage_id=#{showStageId} WHERE row_guid=#{rowguid}")
    void updateShowStageId(@Param("showStageId") Integer showStageId, @Param("rowguid") String rowguid);
}
