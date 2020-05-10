package cn.stylefeng.guns.dao;

import cn.stylefeng.guns.pojo.Knowledge;
import cn.stylefeng.guns.pojo.KnowledgeNode;
import cn.stylefeng.guns.pojos.EmpKnowledge;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KnowledgeDao extends BaseMapper<Knowledge> {

    List<KnowledgeNode> selectList();

    @Select("select k.id, k.t_knowledge_name, k.t_update_date, k.t_knowledge_man, k.t_parent_id \n" +
            " from tb_knowledge k " +
            " where k.id=#{id}")
    @Results({
            @Result(column = "id", property = "id"),
            @Result(column = "t_update_date", property = "tUpdateDate"),
            @Result(column = "t_knowledge_name", property = "tKnowledgeName"),
            @Result(column = "t_parent_id", property = "tParentId"),
            @Result(column = "t_knowledge_man", property = "tKnowledgeMan")
    })
    Knowledge findById(@Param("id") Integer id);

    @Update("update tb_knowledge set t_knowledge_name=#{tKnowledgeName}, t_knowledge_man=#{tKnowledgeMan}, t_update_date=#{tUpdateDate} where id=#{id}")
    void update(Knowledge knowledge);

    List<Knowledge> deleteFindById(Integer id);

    // 知识点下拉列表
    @Select("SELECT k.id,k.t_knowledge_name FROM tb_knowledge k")
    List<Knowledge> findKnowledgeList();

}
