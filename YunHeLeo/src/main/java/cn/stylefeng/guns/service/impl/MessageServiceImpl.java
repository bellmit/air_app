package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.EmpMessDao;
import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.dao.MessageDao;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojo.Message;
import cn.stylefeng.guns.pojos.EmpMess;
import cn.stylefeng.guns.service.MessageService;
import cn.stylefeng.guns.utils.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private EmpMessDao empMessDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Page<EmpMess> findAll(int page, int size, String query) {
        PageHelper.startPage(page,size);
        EmpMess empMess = new EmpMess();
        empMess.settMessageTitle(query);
        Page<EmpMess> all = empMessDao.findAll(empMess);
        return all;
    }

    @Override
    public int count() {
        return messageDao.count();
    }

    @Override
    public void addMessage(String userId, Message message, HttpServletRequest request) {
        String uid = (String) request.getSession().getAttribute("id");
        String id = idWorker.nextId()+"";
        message.setId(id);
        message.setRowGuid(id);
        message.settMessageContent(message.gettMessageContent());
        message.settMessageTitle(message.gettMessageTitle());
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        // 消息发布日期
        message.settUpdateDate(date);
        // 消息发送日期
        message.settSendDate(date);
        // 是否推送 1是 2否
        message.settStatus(1);
        if (userId==null) {
            // 创建人
            message.settUpdateMan(uid);
        } else {
            // 创建人
            message.settUpdateMan(userId);
        }
        messageDao.insert(message);

        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            if (userId==null) {
                log.settOperatorMan(uid);
            } else {
                log.settOperatorMan(userId);
            }
            log.settOperatorRecord("新增消息");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public EmpMess findById(String id) {
        return empMessDao.findById(id);
    }

    @Override
    public void deleteByIdMessage(String userId, String id) {
        messageDao.deleteById(id);
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("删除消息");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
