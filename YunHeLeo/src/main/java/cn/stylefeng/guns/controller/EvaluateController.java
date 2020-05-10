package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojos.Evaluate;
import cn.stylefeng.guns.service.EvaluateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/evaluate")
@CrossOrigin
@Api(value = "评价管理",description = "评价管理，列表查询、条件查询")
public class EvaluateController {

    @Autowired
    private EvaluateService evaluateService;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("评价管理")
    public Result findAll(int page, int size, String query) {
        try {
            int total = evaluateService.count();
            List<Evaluate> all = evaluateService.findAll(page, size, query);
            return new Result(true, ResultStatusCode.SUCCESS, total, "查询成功!",all);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询失败!");
        }
    }
}
