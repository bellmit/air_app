package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Type;
import cn.stylefeng.guns.pojos.EmpImg;
import cn.stylefeng.guns.service.TypeService;

import com.github.pagehelper.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping( "/type" )
@CrossOrigin
@Api( value = "课程类别管理", description = "课程类别，增删改查" )
public class TypeController {

    @Autowired
    private TypeService typeService;

    @GetMapping( "/findAll" )
    @ApiOperation( "查询类别" )
    public Result findAll(int page, int size, String query) {
        try {
            int total = typeService.countType();
            Page<Type> empImg = typeService.findAll(page, size, query);
            return new Result(true, ResultStatusCode.SUCCESS, total, "查询成功!", empImg);
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询失败!");
        }
    }

    @PostMapping( "/addType" )
    @ApiOperation( "保存类别" )
    public Result addType(String userId, @RequestBody Type type, HttpServletRequest request) {
        try {
            typeService.addType(userId, type, request);
            return new Result(true, ResultStatusCode.SUCCESS, "保存类别成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "保存类别失败!");
        }
    }

    @PutMapping( "/updateType" )
    @ApiOperation( "根据rowGuid修改类别" )
    public Result updateType(String rowGuid, String userId, @RequestBody Type type, HttpServletRequest request) {
        try {
            typeService.updateType(rowGuid, userId, type, request);
            return new Result(true, ResultStatusCode.SUCCESS, "根据rowGuid修改类别成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据rowGuid修改类别失败!");
        }
    }

    @GetMapping( "/findById" )
    @ApiOperation( "根据rowGuid查询类别" )
    public Result findById(String rowGuid) {
        try {
            Type type = typeService.findById(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据rowGuid查询类别成功!", type);
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据rowGuid查询类别失败!");
        }
    }

    @DeleteMapping( "/delete" )
    @ApiOperation( "删除类别" )
    public Result delete(String userId, String id, HttpServletRequest request) {
        try {
            Type courseTypeId = typeService.findByCourseTypeId(id);
            if (courseTypeId==null) {
                typeService.delete(userId, id, request);
                return new Result(true, ResultStatusCode.SUCCESS, "删除类别成功!");
            } else {
                return new Result(true, ResultStatusCode.SUCCESS, "类别已在课程中存在，无法删除!");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "删除类别失败!");
        }
    }
}
