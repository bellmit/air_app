package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojos.UserDetails;
import cn.stylefeng.guns.pojos.UserLogLevel;
import cn.stylefeng.guns.pojos.UserOrder;
import cn.stylefeng.guns.pojos.WorkUC;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserLogLevel> findAll(int page, int size, String query);
    int count();
    UserOrder userOrderCount(String RowGuid);
    UserOrder userWorkCount(String RowGuid);
    UserOrder userCourseCount(String RowGuid);
    Map<String,Object> findByIdUser(String RowGuid, int page, int size);
    UserDetails findByIdAddress(String RowGuid);
}
