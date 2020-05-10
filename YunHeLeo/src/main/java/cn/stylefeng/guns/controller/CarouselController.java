package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Img;
import cn.stylefeng.guns.pojos.EmpImg;
import cn.stylefeng.guns.service.CarouselSerivce;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/carousel")
@CrossOrigin
@Api(value = "轮播图管理",description = "轮播图管理")
public class CarouselController {

    @Autowired
    private CarouselSerivce carouselSerivce;

    @GetMapping("/findById")
    @ApiOperation("根据id查询轮播图")
    public Result findById(String id) {
        try {
            EmpImg empImg = carouselSerivce.findById(id);
            return new Result(true, ResultStatusCode.SUCCESS, "根据id查询轮播图成功!",empImg);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据id查询轮播图失败!");
        }
    }

    @GetMapping("/findAll")
    @ApiOperation("查询轮播图")
    public Result findAll(int page,int size,String query) {
        try {
            int total = carouselSerivce.countImg();
            Page<EmpImg> empImg = carouselSerivce.findAll(page,size,query);
            return new Result(true, ResultStatusCode.SUCCESS,total, "查询轮播图成功!",empImg);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询轮播图失败!");
        }
    }

    @PostMapping("/save")
    @ApiOperation("保存轮播图")
    public Result addCarouse(String userId, @RequestBody Img img, HttpServletRequest request) {
        try {
            carouselSerivce.addCarouse(userId, img,request);
            return new Result(true, ResultStatusCode.SUCCESS, "保存轮播图成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "保存轮播图失败!");
        }
    }

    @PutMapping("/update")
    @ApiOperation("修改轮播图")
    public Result updateCarouse(String userId, String id, @RequestBody Img img, HttpServletRequest request) {
        try {
            carouselSerivce.updateCarouse(userId, id, img,request);
            return new Result(true, ResultStatusCode.SUCCESS, "修改轮播图成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "修改轮播图失败!");
        }
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除轮播图")
    public Result delete(String id, String userId, HttpServletRequest request) {
        try {
            carouselSerivce.deleteById(id, userId, request);
            return new Result(true, ResultStatusCode.SUCCESS, "删除轮播图成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "删除轮播图失败!");
        }
    }

}
