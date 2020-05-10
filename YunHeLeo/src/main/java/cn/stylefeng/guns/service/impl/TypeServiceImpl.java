package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.dao.TypeDao;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojo.Type;
import cn.stylefeng.guns.service.TypeService;
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
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public int countType() {
        return typeDao.countType();
    }

    @Override
    public Page<Type> findAll(int page, int size, String query) {
        PageHelper.startPage(page, size);
        Type type = new Type();
        type.settTypeName(query);
        Page<Type> all = typeDao.findAll(type);
        return all;
    }

    @Override
    public void addType(String userId,Type type, HttpServletRequest request) {
        String uid = (String) request.getSession().getAttribute("id");

        type.setRowGuid(idWorker.nextId()+"");
        type.settTypeName(type.gettTypeName());
        type.settOrder(type.gettOrder());
        type.settStatus(1);
        typeDao.insert(type);

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
            if (userId!=null) {
                log.settOperatorMan(userId);
            } else {
                log.settOperatorMan(uid);
            }
            log.settOperatorRecord("保存课程类别");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String userId,String id, HttpServletRequest request) {
        //typeDao.deleteById(id);
        typeDao.updateByRowguid(id);

        String uid = (String) request.getSession().getAttribute("id");
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
            if (userId!=null) {
                log.settOperatorMan(userId);
            } else {
                log.settOperatorMan(uid);
            }
//            log.settOperatorMan(userId);
            log.settOperatorRecord("删除课程类别");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Type> findType() {
        return typeDao.findType();
    }

    @Override
    public Type findById(String rowGuid) {
        return typeDao.findById(rowGuid);
    }

    @Override
    public void updateType(String rowGuid, String userId, Type type, HttpServletRequest request) {
        Type typeId = typeDao.findById(rowGuid);
        typeId.settTypeName(type.gettTypeName());
        typeId.settOrder(type.gettOrder());
        typeDao.updateById(typeId);

        String uid = (String) request.getSession().getAttribute("id");
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
            if (userId!=null) {
                log.settOperatorMan(userId);
            } else {
                log.settOperatorMan(uid);
            }
            log.settOperatorRecord("修改课程类别");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Type findByCourseTypeId(String id) {
        return typeDao.findByCourseTypeId(id);
    }
}
