package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.OperateDataDao;
import cn.stylefeng.guns.service.OperateDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OperateDataServiceImpl implements OperateDataService {

    @Autowired
    private OperateDataDao operateDataDao;

    @Override
    public Map<String, Object> operateData() {
        int addTodayUserCount = operateDataDao.addTodayUserCount();
        int addTodayWorks = operateDataDao.addTodayWorks();
        int buyTodayClass = operateDataDao.buyTodayClass();
        int countUser = operateDataDao.countUser();
        int loginTodayUserCount = operateDataDao.loginTodayUserCount();
        // 数据统计
        Map<String,Object> map = new HashMap<>();
        map.put("loginTodayUserCount",loginTodayUserCount);
        map.put("countUser",countUser);
        map.put("buyTodayClass",buyTodayClass);
        map.put("addTodayWorks",addTodayWorks);
        map.put("addTodayUserCount",addTodayUserCount);
        return map;
    }

    @Override
    public List<Map<String,String>> buyClass() {
        // 折线图
        List<Map<String,String>> buyClass = operateDataDao.buyClass();
        System.out.println(buyClass);
        return buyClass;
    }

    @Override
    public List<Map<String, String>> addUser() {
        List<Map<String, String>> user = operateDataDao.addUser();
        System.out.println(user);
        return user;
    }

}
