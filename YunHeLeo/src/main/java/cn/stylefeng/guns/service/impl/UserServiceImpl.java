package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.UserDao;
import cn.stylefeng.guns.dao.UserDetailsDao;
import cn.stylefeng.guns.dao.UserOrderDao;
import cn.stylefeng.guns.dao.WorkUCDao;
import cn.stylefeng.guns.pojos.UserDetails;
import cn.stylefeng.guns.pojos.UserLogLevel;
import cn.stylefeng.guns.pojos.UserOrder;
import cn.stylefeng.guns.pojos.WorkUC;
import cn.stylefeng.guns.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userLoginLogDao;

    @Autowired
    private UserOrderDao userOrderDao;

    @Autowired
    private UserDetailsDao userDetailsDao;

    @Override
    public List<UserLogLevel> findAll(int page, int size, String query) {
        PageHelper.startPage(page, size);
        UserLogLevel user = new UserLogLevel();
        user.settUsername(query);
        user.settMobile(query);
        List<UserLogLevel> all = userLoginLogDao.findAll(user);
        return all;
    }

    @Override
    public int count() {
        return userLoginLogDao.count();
    }

    @Override
    public UserOrder userOrderCount(String RowGuid) {
        UserOrder count = userOrderDao.userOrderCount(RowGuid);
        System.out.println(count);
        return count;
    }

    @Override
    public UserOrder userWorkCount(String RowGuid) {
        UserOrder count = userOrderDao.userWorkCount(RowGuid);
        System.out.println(count);
        return count;
    }

    @Override
    public UserOrder userCourseCount(String RowGuid) {
        UserOrder count = userOrderDao.userCourseCount(RowGuid);
        System.out.println(count);
        return count;
    }

    @Override
    public Map<String,Object> findByIdUser(String RowGuid,int page,int size) {
        // 学生基本信息
        UserDetails userInfo = userDetailsDao.findByIdUser(RowGuid);
        // 查询收货地址
        UserDetails address = userDetailsDao.findByIdAddress(RowGuid);
        System.out.println(userInfo);
        // 根据id查询用户购课信息
        // 1.1查询长期包 包含课包
        PageHelper.startPage(page,size);
        List<UserDetails> longClass = userDetailsDao.findLongClass(RowGuid);
        // 1.2查询短期班 不包含课包
        PageHelper.startPage(page,size);
        List<UserDetails> shortClass = userDetailsDao.findShortClass(RowGuid);
        // 1.3查询免费班 不包含课包
        PageHelper.startPage(page,size);
        List<UserDetails> freeClass = userDetailsDao.findFreeClass(RowGuid);
        // 2.查询长期班课包 包含阶段
        List<UserDetails> packageStage = userDetailsDao.findPackageStage(RowGuid);
        // 3.查询购买日期
        List<UserDetails> buyDate = userDetailsDao.findBuyDate(RowGuid);
        // 4.查询课程状态
        List<UserDetails> status = userDetailsDao.findStatus(RowGuid);
        Map<String,Object> map = new HashMap<>();
        map.put("userInfo",userInfo);
        map.put("address",address);
        map.put("longClass",longClass);
        map.put("shortClass",shortClass);
        map.put("freeClass",freeClass);
        map.put("packageStage",packageStage);
        map.put("buyDate",buyDate);
        map.put("status",status);
        return map;
    }

    @Override
    public UserDetails findByIdAddress(String RowGuid) {
        UserDetails address = userDetailsDao.findByIdAddress(RowGuid);
        System.out.println(address);
        return address;
    }
}
