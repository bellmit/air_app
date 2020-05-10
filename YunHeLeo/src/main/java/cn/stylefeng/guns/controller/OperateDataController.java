package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.service.OperateDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/operate")
@CrossOrigin
@Api(value = "运营数据统计",description = "运营数据统计")
public class OperateDataController {
    @Autowired
    private OperateDataService operateDataService;

    @GetMapping("/operateData")
    @ApiOperation("数据概览")
    public Result operateData() {
        try {
            Map<String,Object> map = new HashMap<>();
            Map<String, Object> operateData = operateDataService.operateData();
            List<Map<String, String>> buyClass = operateDataService.buyClass();
            List<Map<String, String>> user = operateDataService.addUser();
            map.put("data_statistics",operateData);
            //近七日购课数
            map.put("sevenBuyCourse",buyClass);
            //近七日新增用户数
            map.put("sevenAddUser",user);
            return new Result(true, ResultStatusCode.SUCCESS, "查询成功!",map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询失败!");
        }
    }
}
