package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Works;
import cn.stylefeng.guns.pojos.WorkUC;
import cn.stylefeng.guns.service.WorkUCService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/works")
@CrossOrigin
@Api(value = "作品管理",description = "作品管理")
public class WorkUCController {

    @Autowired
    private WorkUCService workUCService;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("查询作品列表")
    public Result findAll(int page, int size, String query) {
        try {
            int total = workUCService.count();
            List<WorkUC> workUCList = workUCService.findAll(page, size,query);
            return new Result(true, ResultStatusCode.SUCCESS, total, "查询成功!",workUCList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询失败!");
        }
    }

    @GetMapping("/findById")
    @ResponseBody
    @ApiOperation("根据id查询作品列表")
    public Result findById(Integer id) {
        try {
            WorkUC workUC = workUCService.findById(id);
            return new Result(true, ResultStatusCode.SUCCESS, "查询成功!",workUC);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询失败!");
        }
    }

    @PutMapping("/save")
    @ResponseBody
    @ApiOperation("老师作品评价")
    public Result save(String userId, Integer id, @RequestBody Works works) {
        try {
            workUCService.save(userId, id, works);
            return new Result(true, ResultStatusCode.SUCCESS, "保存评价成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "保存评价失败!");
        }
    }

}
