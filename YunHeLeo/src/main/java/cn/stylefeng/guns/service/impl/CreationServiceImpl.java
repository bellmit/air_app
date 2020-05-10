package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.CreationDao;
import cn.stylefeng.guns.dao.EmpCreationDao;
import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.pojo.Creation;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojos.EmpCreation;
import cn.stylefeng.guns.service.CreationService;
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
public class CreationServiceImpl implements CreationService {

    @Autowired
    private CreationDao creationDao;

    @Autowired
    private EmpCreationDao empCreationDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public Page<EmpCreation> findAll(int page, int size, String query) {
        PageHelper.startPage(page,size);
        EmpCreation creation = new EmpCreation();
        creation.settCreationName(query);
        Page<EmpCreation> all = empCreationDao.findAll(creation);
        System.out.println(all);
        return all;
    }

    @Override
    public int count() {
        return empCreationDao.count();
    }

    @Override
    public EmpCreation findById(String id) {
        return empCreationDao.findById(id);
    }

    @Override
    public void save(String userId, Creation creation, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        String uid = (String) request.getSession().getAttribute("id");

        if (userId==null) {
            creation.settCreationName(creation.gettCreationName());
            creation.settUpdateDate(date);
            creation.settUpdateMan(uid);
            creationDao.insert(creation);
        } else {
            creation.settCreationName(creation.gettCreationName());
            creation.settUpdateDate(date);
            creation.settUpdateMan(userId);
            creationDao.insert(creation);
        }

        try {
            if (userId==null) {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(uid);
                log.settOperatorRecord("保存创作方式");
                logDao.insertLog(log);
            } else {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("保存创作方式");
                logDao.insertLog(log);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer id, String userId, Creation creation, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        String uid = (String) request.getSession().getAttribute("id");

        if (userId==null) {
            Creation empCreation = creationDao.findById(id);
            empCreation.settCreationName(creation.gettCreationName());
            empCreation.settUpdateMan(uid);
            empCreation.settUpdateDate(date);
            empCreation.setId(id);
            creationDao.update(empCreation);
        } else {
            Creation empCreation = creationDao.findById(id);
            empCreation.settCreationName(creation.gettCreationName());
            empCreation.settUpdateMan(uid);
            empCreation.settUpdateDate(date);
            empCreation.setId(id);
            creationDao.update(empCreation);
        }

        try {
            if (userId==null) {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(uid);
                log.settOperatorRecord("修改创作方式");
                logDao.insertLog(log);
            } else {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("修改创作方式");
                logDao.insertLog(log);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer id, String userId, HttpServletRequest request) {
        creationDao.deleteById(id);
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        try {
            String uid = (String) request.getSession().getAttribute("id");
            if (userId==null) {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(uid);
                log.settOperatorRecord("删除创作方式");
                logDao.insertLog(log);
            } else {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("删除创作方式");
                logDao.insertLog(log);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Creation> findCreationList() {
        return creationDao.findCreationList();
    }
}
