package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.dao.WorkUCDao;
import cn.stylefeng.guns.dao.WorksDao;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojo.Works;
import cn.stylefeng.guns.pojos.WorkUC;
import cn.stylefeng.guns.service.WorkUCService;
import cn.stylefeng.guns.utils.IdWorker;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class WorkUCServiceImpl implements WorkUCService {

    @Autowired
    private WorkUCDao workUCDao;

    @Autowired
    private WorksDao worksDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<WorkUC> findAll(int page,int size,String query) {
        PageHelper.startPage(page, size);
        WorkUC workUC = new WorkUC();
        workUC.settMobile(query);
        workUC.settUsername(query);
        workUC.settWorksNum(query);
        List<WorkUC> all = workUCDao.findAll(workUC);
        System.out.println(all);
        return all;
    }

    @Override
    public int count() {
        return workUCDao.count();
    }

    @Override
    public WorkUC findById(Integer id) {
        return workUCDao.findById(id);
    }

    @Override
    public void save(String userId, Integer id, Works works) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        Works work = worksDao.findById(id);
        // 当前登录的用户id
        work.settTeacherGuid(userId);
        work.settContent(works.gettContent());
        work.settUploadDate(date);
        worksDao.updateById(work);

        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("评价作品");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
