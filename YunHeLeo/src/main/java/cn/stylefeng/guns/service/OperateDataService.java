package cn.stylefeng.guns.service;

import java.util.List;
import java.util.Map;

public interface OperateDataService {

    Map<String,Object> operateData();
    List<Map<String,String>> buyClass();
    List<Map<String,String>> addUser();
}
