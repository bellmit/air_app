package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Message;
import cn.stylefeng.guns.pojos.EmpMess;
import cn.stylefeng.guns.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/message")
@CrossOrigin
@Api(value = "消息通知管理",description = "消息通知")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("查询消息推送(含根据消息标题查询)")
    public Result findAll(int page,int size,String query) {
        try {
            int total = messageService.count();
            List<EmpMess> list = messageService.findAll(page,size,query);
            return new Result(true, ResultStatusCode.SUCCESS, total, "查询成功!",list);
        } catch (Exception e) {
            return new Result(true, ResultStatusCode.SUCCESS, "查询失败!");
        }
    }

    @GetMapping("/findById")
    @ResponseBody
    @ApiOperation("根据id查询消息推送")
    public Result findById(String id) {
        try {
            EmpMess empMess = messageService.findById(id);
            return new Result(true, ResultStatusCode.SUCCESS, "查询成功!",empMess);
        } catch (Exception e) {
            return new Result(true, ResultStatusCode.SUCCESS, "查询失败!");
        }
    }

    @PostMapping("/addMessage")
    @ResponseBody
    @ApiOperation("新增消息推送")
    public Result addMessage(String userId, @RequestBody Message message, HttpServletRequest request) {
        try {
            messageService.addMessage(userId,message,request);
            return new Result(true, ResultStatusCode.SUCCESS, "保存成功!");
        } catch (Exception e) {
            return new Result(true, ResultStatusCode.FAIL, "保存失败!");
        }
    }

    @DeleteMapping("/deleteById")
    @ResponseBody
    @ApiOperation("新增消息推送")
    public Result deleteByIdMessage(String userId, String id) {
        try {
            messageService.deleteByIdMessage(userId,id);
            return new Result(true, ResultStatusCode.SUCCESS, "删除成功!");
        } catch (Exception e) {
            return new Result(true, ResultStatusCode.FAIL, "删除失败!");
        }
    }
}
