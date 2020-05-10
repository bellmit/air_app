package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Role;
import cn.stylefeng.guns.pojos.UserDetails;
import cn.stylefeng.guns.pojos.UserLogLevel;
import cn.stylefeng.guns.pojos.UserOrder;
import cn.stylefeng.guns.pojos.WorkUC;
import cn.stylefeng.guns.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
@Api(value = "学生管理",description = "学生管理")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("学生管理列表")
    public Result findAll(int page, int size, String query) {
        try {
            int total = userService.count();
            List<Object> list = new ArrayList<>();
            List<UserLogLevel> all = userService.findAll(page, size, query);
            List<Object> userOrderList = new ArrayList<>();
            List<Object> userWorksList = new ArrayList<>();
            List<Object> userCourseList = new ArrayList<>();
            Map<String,Object> userOrderCount = new HashMap<>();
            Map<String,Object> userWorksCount = new HashMap<>();
            Map<String,Object> userCourseCount = new HashMap<>();
            for (UserLogLevel userLogLevel : all) {
                // 查询每个学员订单量
                Map<String,Object> stuOrderMap = new HashMap<>();
                String id = userLogLevel.getRowGuid();
                UserOrder userOrder = userService.userOrderCount(id);
                if (userOrder!=null) { // 该用户有订单
                    stuOrderMap.put(userOrder.getRowGuid(),userOrder.getCount());
                    userOrderList.add(stuOrderMap);
                } else {
                    stuOrderMap.put(id,0);
                    userOrderList.add(stuOrderMap);
                }
                // 查询每个学员作品量
                Map<String,Object> stuWorkMap = new HashMap<>();
                UserOrder workCount = userService.userWorkCount(id);
                if (workCount!=null) {
                    stuWorkMap.put(workCount.getRowGuid(), workCount.getCount());
                    userWorksList.add(stuWorkMap);
                } else {
                    stuWorkMap.put(id, 0);
                    userWorksList.add(stuWorkMap);
                }
                // 学员试学课程数
                Map<String,Object> stuCourseMap = new HashMap<>();
                UserOrder courseCount = userService.userCourseCount(id);
                if (courseCount!=null) {
                    stuCourseMap.put(courseCount.getRowGuid(), courseCount.getCount());
                    userCourseList.add(stuCourseMap);
                } else {
                    stuCourseMap.put(id, 0);
                    userCourseList.add(stuCourseMap);
                }
            }
            userOrderCount.put("userOrderList",userOrderList);
            userWorksCount.put("userWorksList",userWorksList);
            userCourseCount.put("userCourseList",userCourseList);
            list.add(all);
            list.add(userOrderCount);
            list.add(userWorksCount);
            list.add(userCourseCount);
            return new Result(true, ResultStatusCode.SUCCESS, total, "查询成功!",list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询失败!");
        }
    }

    @GetMapping("/findById")
    @ResponseBody
    @ApiOperation("根据RowGuid查看学生详情 基本信息")
    public Result findById(String RowGuid,int page,int size) {
        try {
            Map<String, Object> details = userService.findByIdUser(RowGuid,page,size);
            return new Result(true, ResultStatusCode.SUCCESS, "查询成功!",details);
        }catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询失败!");
        }
    }

}
