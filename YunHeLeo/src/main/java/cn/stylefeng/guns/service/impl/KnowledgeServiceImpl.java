package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.KnowledgeDao;
import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.pojo.Knowledge;
import cn.stylefeng.guns.pojo.KnowledgeNode;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.service.KnowledgeService;
import cn.stylefeng.guns.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeDao knowledgeDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 知识点下拉列表
     * @return
     */
    @Override
    public List<Knowledge> findKnowledgeList() {
        return knowledgeDao.findKnowledgeList();
    }

    @Override
    public void saveKnowedge(Integer id, String userId, Knowledge knowledge, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        String idWork = idWorker.nextId()+"";

        String uid = (String) request.getSession().getAttribute("id");

        if (uid==null) {
            knowledge.setRowGuid(idWork);
            knowledge.settKnowledgeName(knowledge.gettKnowledgeName());
            knowledge.settKnowledgeMan(userId);
            knowledge.settUpdateDate(date);
            if (id == null) {
                knowledge.settParentId(0);// 一级知识点
            } else {
                knowledge.settParentId(id);// 子知识点父级id
            }
            knowledgeDao.insert(knowledge);
        } else {
            knowledge.setRowGuid(idWork);
            knowledge.settKnowledgeName(knowledge.gettKnowledgeName());
            knowledge.settKnowledgeMan(uid);
            knowledge.settUpdateDate(date);
            if (id == null) {
                knowledge.settParentId(0);// 一级知识点
            } else {
                knowledge.settParentId(id);// 子知识点父级id
            }
            knowledgeDao.insert(knowledge);
        }

        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWork);
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("保存知识点");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<KnowledgeNode> selectList() {
        return knowledgeDao.selectList();
    }

    @Override
    public Knowledge findById(Integer id) {
        Knowledge knowledge = knowledgeDao.findById(id);
        System.out.println(knowledge);
        return knowledge;
    }

    @Override
    public void update(Integer id, String userId, Knowledge knowledge, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        String uid = (String) request.getSession().getAttribute("id");

        if (userId==null) {
            Knowledge knowedgeId = knowledgeDao.findById(id);
            knowedgeId.settKnowledgeName(knowledge.gettKnowledgeName());
            knowedgeId.settKnowledgeMan(uid);
            knowedgeId.settUpdateDate(date);
            knowedgeId.setId(id);
            knowledgeDao.update(knowedgeId);
        } else {
            Knowledge knowedgeId = knowledgeDao.findById(id);
            knowedgeId.settKnowledgeName(knowledge.gettKnowledgeName());
            knowedgeId.settKnowledgeMan(userId);
            knowedgeId.settUpdateDate(date);
            knowedgeId.setId(id);
            knowledgeDao.update(knowedgeId);
        }

        try {
            if (userId==null) {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(uid);
                log.settOperatorRecord("编辑知识点");
                logDao.insertLog(log);
            } else {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("编辑知识点");
                logDao.insertLog(log);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id, String userId, HttpServletRequest request) {
        String uid = (String) request.getSession().getAttribute("id");
        // 删除父级知识点 pid -- id
        // 先删除子知识点
        List<Knowledge> knowledge = knowledgeDao.deleteFindById(id);
        System.out.println(knowledge);

        // 再删除父级知识点
        // 删除子知识点
        knowledgeDao.deleteById(id);

        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        try {
            if (userId==null) {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(uid);
                log.settOperatorRecord("编辑知识点");
                logDao.insertLog(log);
            } else {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("编辑知识点");
                logDao.insertLog(log);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
