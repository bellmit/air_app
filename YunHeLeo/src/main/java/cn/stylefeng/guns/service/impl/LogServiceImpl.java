package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojos.EmpLog;
import cn.stylefeng.guns.service.LogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public List<Map<String,String>> findLogList(int page, int size, String query) {
        PageHelper.startPage(page,size);
        EmpLog empLog = new EmpLog();
        empLog.settIpAddress(query);
        empLog.settOperatorRecord(query);
        empLog.settUsername(query);
        return logDao.findLogList(empLog);
    }

    @Override
    public int countLog() {
        return logDao.countLog();
    }
}
