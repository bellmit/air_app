package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojo.Knowledge;
import cn.stylefeng.guns.pojo.KnowledgeNode;
import cn.stylefeng.guns.pojos.EmpKnowledge;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface KnowledgeService {
    List<Knowledge> findKnowledgeList();
    void saveKnowedge(Integer id, String userId, Knowledge knowledge, HttpServletRequest request);
    List<KnowledgeNode> selectList();
    Knowledge findById(Integer id);
    void update(Integer id, String userId, Knowledge knowledge, HttpServletRequest request);
    void delete(Integer id, String userId, HttpServletRequest request);
}
