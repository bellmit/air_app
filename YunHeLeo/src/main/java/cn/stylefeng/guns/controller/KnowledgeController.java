package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Knowledge;
import cn.stylefeng.guns.pojo.KnowledgeNode;
import cn.stylefeng.guns.pojos.EmpImg;
import cn.stylefeng.guns.service.KnowledgeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
@CrossOrigin
@Api(value = "知识点管理",description = "知识点管理")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @GetMapping("/findAll")
    @ApiOperation("查询知识点")
    public Result findAll() {
        try {
            List<KnowledgeNode> knowledgeNodes = knowledgeService.selectList();
            return new Result(true, ResultStatusCode.SUCCESS, "查询知识点成功!",knowledgeNodes);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询知识点失败!");
        }
    }

    @GetMapping("/findById")
    @ApiOperation("根据id查询知识点")
    public Result findById(Integer id) {
        try {
            Knowledge knowledgeNodes = knowledgeService.findById(id);
            return new Result(true, ResultStatusCode.SUCCESS, "查询知识点成功!",knowledgeNodes);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询知识点失败!");
        }
    }

    @PostMapping("/save")
    @ApiOperation("保存知识点")
    public Result save(Integer id, String userId, @RequestBody Knowledge knowledge, HttpServletRequest request) {
        try {
            knowledgeService.saveKnowedge(id,userId,knowledge,request);
            return new Result(true, ResultStatusCode.SUCCESS, "保存知识点成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "保存知识点失败!");
        }
    }

    @PutMapping("/update")
    @ApiOperation("编辑知识点")
    public Result update(Integer id, String userId, @RequestBody Knowledge knowledge, HttpServletRequest request) {
        try {
            knowledgeService.update(id,userId,knowledge,request);
            return new Result(true, ResultStatusCode.SUCCESS, "编辑知识点成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "编辑知识点失败!");
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除知识点(暂时逐一节点删除)")
    public Result delete(Integer id, String userId, HttpServletRequest request) {
        try {
            knowledgeService.delete(id,userId,request);
            return new Result(true, ResultStatusCode.SUCCESS, "编辑知识点成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "编辑知识点失败!");
        }
    }
}
