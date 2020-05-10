package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Creation;
import cn.stylefeng.guns.pojos.EmpCreation;
import cn.stylefeng.guns.service.CreationService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/creation")
@CrossOrigin
@Api(value = "创作方式管理",description = "创作方式管理")
public class CreationController {

    @Autowired
    private CreationService creationService;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation("查询创作方式")
    public Result findAll(int page,int size,String query) {
        try {
            int total = creationService.count();
            Page<EmpCreation> all = creationService.findAll(page, size, query);
            return new Result(true, ResultStatusCode.SUCCESS, total,"查询成功！",all);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL,"查询失败！");
        }
    }

    @GetMapping("/findById")
    @ResponseBody
    @ApiOperation("根据id查询创作方式")
    public Result findById(String id) {
        try {
            int total = creationService.count();
            EmpCreation creationId = creationService.findById(id);
            return new Result(true, ResultStatusCode.SUCCESS, total,"查询成功！",creationId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL,"查询失败！");
        }
    }

    @PostMapping("/save")
    @ResponseBody
    @ApiOperation("保存创作方式")
    public Result save(String userId, @RequestBody Creation creation, HttpServletRequest request) {
        try {
            creationService.save(userId, creation, request);
            return new Result(true, ResultStatusCode.SUCCESS,"保存成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL,"保存失败！");
        }
    }

    @PutMapping("/update")
    @ResponseBody
    @ApiOperation("编辑创作方式")
    public Result update(Integer id, String userId, @RequestBody Creation creation, HttpServletRequest request) {
        try {
            creationService.update(id, userId, creation, request);
            return new Result(true, ResultStatusCode.SUCCESS,"修改成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL,"修改失败！");
        }
    }

    @DeleteMapping("/delete")
    @ResponseBody
    @ApiOperation("删除创作方式")
    public Result delete(Integer id, String userId, HttpServletRequest request) {
        try {
            creationService.delete(id, userId,request);
            return new Result(true, ResultStatusCode.SUCCESS,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL,"删除失败！");
        }
    }

}
