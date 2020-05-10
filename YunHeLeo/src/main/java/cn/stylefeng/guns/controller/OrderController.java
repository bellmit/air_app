package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojos.EmpMess;
import cn.stylefeng.guns.pojos.UOCCP;
import cn.stylefeng.guns.service.OrderService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin
@Api(value = "订单管理",description = "订单管理")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("订单查询")
    public Result findAll(int page, int size, String query) {
        try {
            int total = orderService.count();
            Page<UOCCP> list = orderService.findAll(page,size,query);
            return new Result(true, ResultStatusCode.SUCCESS, total, "查询成功!",list);
        } catch (Exception e) {
            return new Result(true, ResultStatusCode.SUCCESS, "查询失败!");
        }
    }
}
