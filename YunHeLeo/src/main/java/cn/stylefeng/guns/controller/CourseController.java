package cn.stylefeng.guns.controller;

import cn.stylefeng.guns.app.controller.AppCourseController;
import cn.stylefeng.guns.config.AliyunConfig;
import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.*;
import cn.stylefeng.guns.pojos.*;
import cn.stylefeng.guns.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/course")
@CrossOrigin
@Api(value = "课程管理",description = "课程管理")
public class CourseController {

    @Autowired
    private LevelService levelService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private LinkService linkService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CreationService creationService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/courseType")
    @ApiOperation("创作方式/知识点/类别/老师/客服/级别下拉列表&班型单选按钮")
    public Result courseType() {
        try {
            // 创作方式列表
            List<Creation> creationList = creationService.findCreationList();
            // 知识点下拉列表
            List<Knowledge> knowledgeList = knowledgeService.findKnowledgeList();
            // 课程类别下拉列表
            List<Type> typeList = typeService.findType();
            // 课程授课老师下拉列表
            List<Employee> teacherList = employeeService.teacher();
            // 课程适合级别下拉列表
            List<Level> levelList = levelService.findLevel();
            // 客服老师下拉列表
            List<Employee> serviceList = employeeService.service();
            // 班型 长期班、短期班、免费班
            List<CourseType> courseList = courseService.courseList();
            Map<String,Object> map = new HashMap<>();
            map.put("creationList", creationList);
            map.put("knowledge", knowledgeList);
            map.put("type", typeList);
            map.put("teacher",teacherList);
            map.put("service",serviceList);
            map.put("levelList", levelList);
            map.put("courseList", courseList);
            return new Result(true, ResultStatusCode.SUCCESS, "课程类别下拉列表&单选按钮成功!",map);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "课程类别下拉列表&单选按钮失败!");
        }
    }

    @PostMapping("/createClassType")
    @ApiOperation("创建课程-设置班型")
    public Result createCourseClassType(@RequestBody Course course, String userId, HttpServletRequest request) {
        try {
            // 设置班型
            String rowGuid = courseService.creatClassType(course, userId, request);
            Map<String,String> map = new HashMap<>();
            map.put("RowGuid",rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "创建课程设置班型成功!",map);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "创建课程设置班型失败!");
        }
    }

    @GetMapping("/findAll")
    @ApiOperation("查询课程列表")
    public Result findAll(int page, int size, String query, Integer classTypeId, Integer courseTypeId, Integer tStatus) {
        try {
            Integer total = courseService.courseCount();
            List<CourseAll> list = courseService.findCourseAll(page,size,query,classTypeId,courseTypeId,tStatus);
            //List<CourseAll> courseCount = courseService.buyCourseCount();
            /*Map<String,Object> map = new HashMap<>();
            map.put("course",list);
            map.put("count",courseCount);*/
            return new Result(true, ResultStatusCode.SUCCESS, total,"查询课程列表成功!", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询课程列表失败!");
        }
    }

    @GetMapping("/findClassType")
    @ApiOperation("根据RowGuid查询课程信息 查询班型")
    public Result findIdClass(String rowGuid) {
        try {
            CourseTypesResponse courseTypes = courseService.findTypesId(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据id查询课程信息 查询班型成功!", courseTypes);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据id查询课程信息 查询班型失败!");
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    @PutMapping("/editCourseInfo")
    @ApiOperation("根据RowGuid查询班型后 编辑课程信息并保存")
    public Result editCourseInfo(String RowGuid, @RequestBody Course course) {
        try {
            System.out.println("course:"+course);
            logger.debug("课程RowGuid:"+RowGuid);
            logger.debug("Course请求json:"+course);
            courseService.editCourseInfo(RowGuid,course);
            return new Result(true, ResultStatusCode.SUCCESS, "编辑课程信息保存成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "编辑课程信息保存失败!");
        }
    }

    /*@PutMapping("/saveCourseInfo")
    @ApiOperation("编辑课程信息(发布)")
    public Result saveCourseInfo(Integer id, @RequestBody Course course) {
        try {
            courseService.saveCourseInfo(id,course);
            return new Result(true, ResultStatusCode.SUCCESS, "编辑课程信息发布成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "编辑课程信息发布失败!");
        }
    }*/

    @GetMapping("/findIdStage")
    @ApiOperation("根据rowguid查询阶段")
    public Result findIdStage(String rowguid){
        try {
            Stage idStage = courseService.findIdStage(rowguid);
            return new Result(true, ResultStatusCode.SUCCESS, "编辑课程信息成功!", idStage);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "编辑课程信息失败!");
        }
    }

    @PostMapping("/addStage")
    @ApiOperation("根据课程的RowGuid添加阶段")
    public Result addStage(String RowGuid ,@RequestBody Stage stage, HttpServletRequest request) {
        try {

            String id = courseService.addStage( stage, request);
            courseService.updateCourseStage(RowGuid,id);
            return new Result(true, ResultStatusCode.SUCCESS, "添加阶段成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "添加阶段失败!");
        }
    }

    @PutMapping("/editStage")
    @ApiOperation("修改阶段(阶段的RowGuid)")
    public Result editStage(String RowGuid ,@RequestBody Stage stage) {
        try {
            courseService.updateStage(RowGuid,stage);
            return new Result(true, ResultStatusCode.SUCCESS, "修改阶段成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "修改阶段失败!");
        }
    }

    @GetMapping("/findByClass")
    @ApiOperation("根据RowGuid查询课节详情")
    public Result findByClass(String rowGuid) {
        try {
            Map<String, Object> tClass = courseService.findByClass(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据rowGuid查询课节成功!", tClass);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据rowGuid查询课节失败!");
        }
    }

    @PutMapping("/editClass")
    @ApiOperation("修改课节(课节的rowGuid)")
    public Result editClass(String rowGuid, @RequestBody TClass tClass) {
        try {
            courseService.editClass(rowGuid,tClass);
            return new Result(true, ResultStatusCode.SUCCESS, "修改课节成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "修改课节失败!");
        }
    }

    @PostMapping("/addClass")
    @ApiOperation("添加课节 (长期班 阶段RowGuid，短期班/免费班 courseRowGuid)")
    public Result addClass(String courseRowGuid,String stageRowGuid, String userId, @RequestBody TClass tClass) {
        try {
            String id = courseService.addClass(tClass,userId);
            if (stageRowGuid!=null) {
                courseService.updateStageClass(stageRowGuid, id);
            } else if (courseRowGuid!=null) {
                courseService.updateCourseClass(courseRowGuid,id);
            }
            return new Result(true, ResultStatusCode.SUCCESS, "添加课节成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "添加课节失败!");
        }
    }

    @PutMapping("/classDetail")
    @ApiOperation("课节详情 添加范画(课节的rowGuid")
    public Result classAddImage(String rowGuid, String userId, String imgurl) {
        try {
            courseService.classAddImage(rowGuid,userId,imgurl);
            return new Result(true, ResultStatusCode.SUCCESS, "添加课节返回成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "添加课节返回失败!");
        }
    }

    @PutMapping("/downClass")
    @ApiOperation("下架课节(课节的rowGuid)")
    public Result downClass(String rowGuid) {
        try {
            courseService.downClass(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "下架课节返回成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "下架课节返回失败!");
        }
    }

    @PutMapping("/upClass")
    @ApiOperation("发布课节(课节的rowGuid)")
    public Result upClass(String rowGuid) {
        try {
            courseService.upClass(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "下架课节返回成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "下架课节返回失败!");
        }
    }

    @PutMapping("/isTestClass")
    @ApiOperation("是否试听课节(课节的rowGuid)")
    public Result isTestClass(String rowGuid, Boolean isTest) {
        try {
            courseService.isTestClass(rowGuid,isTest);
            return new Result(true, ResultStatusCode.SUCCESS, "是否试听课节返回成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "是否试听课节返回失败!");
        }
    }

    @GetMapping("/findStage")
    @ApiOperation("根据课程RowGuid查询阶段 包含 课节[长期班]")
    public Result findStage(int page,int size,String RowGuid) {
        try {
            List<StageClass> stage = courseService.findStage(page, size, RowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "查询阶段 包含 课节成功!", stage);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询阶段 包含 课节失败!");
        }
    }

    @GetMapping("/findFreeClass")
    @ApiOperation("根据课程rowguid查询课节[免费班]")
    public Result findFreeClass(String RowGuid) {
        try {
            List<ClassDedailsResponse> classDedailsList = courseService.findFreeClass(RowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据课程rowguid查询课节[免费班]成功!", classDedailsList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课程rowguid查询课节[免费班]失败!");
        }
    }

    @GetMapping("/findByRowGuidClass")
    @ApiOperation("根据课节rowguid查询课节")
    public Result findByRowGuidClass(String RowGuid) {
        try {
            List<ClassDedailsResponse> classDedailsList = new ArrayList<>();//= courseService.findByRowGuidClass(RowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据课节rowguid查询课节成功!", classDedailsList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课节rowguid查询课节失败!");
        }
    }

    @GetMapping("/findShortClass")
    @ApiOperation("根据课程rowguid查询课节[短期班]")
    public Result findShortClass(String RowGuid) {
        try {
            List<ClassDedailsResponse> classDedailsList = courseService.findShortClass(RowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据课程rowguid查询课节[短期班]成功!", classDedailsList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课程rowguid查询课节[短期班]失败!");
        }
    }

    @GetMapping("/findStageAll")
    @ApiOperation("查询某课程下的所有阶段[课程的rowGuid]")
    public Result findStageAll(String rowGuid) {
        try {
            List<Stage> all = courseService.findAll(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "查询某课程下的所有阶段成功!",all);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "查询某课程下的所有阶段失败!");
        }
    }

    @GetMapping("/findCPAll")
    @ApiOperation("查询当前课程下的课包[课程的rowGuid]")
    public Result findCPAll(String rowGuid,int page,int size) {
        try {
            Integer packageCount = courseService.packageCount(rowGuid);
            Map packageDetailsList = courseService.findCPAll(rowGuid,page,size);
            return new Result(true, ResultStatusCode.SUCCESS, packageCount,"查询当前课程下的课包成功!", packageDetailsList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询当前课程下的课包失败!");
        }
    }

    @PostMapping("/shortPrice")
    @ApiOperation("设置短期班课程总价[课程的rowGuid]")
    public Result updateShortPrice(String rowGuid, Double price) {
        try {
            courseService.updateShortPrice(rowGuid, price);
            return new Result(true, ResultStatusCode.SUCCESS, "设置短期班课程总价成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "设置短期班课程总价失败!");
        }
    }

    @PostMapping("/addCoursePackage")
    @ApiOperation("创建课包[课程的rowGuid]")
    public Result addCoursePackage(String rowGuid,@RequestBody CoursePackage coursePackage) {
        try {
            if (coursePackage.gettPrice()==null) {
                return new Result(true, ResultStatusCode.SUCCESS, "课包价格不可以为空!");
            }
            String cpId = courseService.addCoursePackage(coursePackage);
            courseService.updateCourse(rowGuid, cpId);
            return new Result(true, ResultStatusCode.SUCCESS, "创建课包成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "创建课包失败!");
        }
    }

    @PutMapping("/releaseCoursePackage")
    @ApiOperation("发布课包[rowGuid课包的cprowGuid]")
    public Result releaseCoursePackage(String rowGuid) {
        try {
            courseService.releaseCoursePackage(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "发布课包成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "发布课包失败!");
        }
    }

    @PutMapping("/downCoursePackage")
    @ApiOperation("下架课包[课包的rowGuid]")
    public Result downCoursePackage(String rowGuid) {
        try {
            courseService.downCoursePackage(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "下架课包成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "下架课包失败!");
        }
    }

    @PutMapping("/deleteCoursePackage")
    @ApiOperation("删除课包[课包的rowGuid]")
    public Result deleteCoursePackage(String rowGuid) {
        try {
            courseService.deleteCoursePackage(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "删除课包成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "删除课包失败!");
        }
    }

    @PutMapping("/editCoursePackage")
    @ApiOperation("编辑课包[课包的rowGuid]")
    public Result editCoursePackage(String rowGuid,@RequestBody CoursePackage coursePackage) {
        try {
            courseService.editCoursePackage(rowGuid, coursePackage);
            return new Result(true, ResultStatusCode.SUCCESS, "编辑课包成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "编辑课包失败!");
        }
    }

    @PostMapping("/addVideo")
    @ApiOperation("环节 添加视频 (classRowGuid为课节的RowGuid)")
    public Result addVideo(String classRowGuid, String userId, @RequestBody Link link, HttpServletRequest request) {
        try {
            linkService.addVideo(classRowGuid, userId, link,request);
            return new Result(true, ResultStatusCode.SUCCESS, "添加视频成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "添加视频失败!");
        }
    }

    @GetMapping("/findByVideo")
    @ApiOperation("根据环节rowguid查询环节/视频 (环节的RowGuid)")
    public Result findByVideo(String rowguid) {
        try {
            LinkDetails link = linkService.findByVideo(rowguid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据环节rowguid查询环节成功!", link);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据环节rowguid查询环节失败!");
        }
    }

    @PutMapping("/updateVideo")
    @ApiOperation("根据环节rowguid修改环节/视频 (环节的RowGuid)")
    public Result updateVideo(String rowguid,String userId,String videoUrl) {
        try {
            linkService.updateVideo(rowguid, userId, videoUrl);
            return new Result(true, ResultStatusCode.SUCCESS, "根据环节rowguid修改环节成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据环节rowguid修改环节失败!");
        }
    }

    @PutMapping("/downVideo")
    @ApiOperation("下架环节/视频 (环节的RowGuid)")
    public Result downVideo(String rowguid) {
        try {
            linkService.downVideo(rowguid);
            return new Result(true, ResultStatusCode.SUCCESS, "下架环节成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "下架环节失败!");
        }
    }

    @PutMapping("/upVideo")
    @ApiOperation("发布环节/视频 (环节的RowGuid)")
    public Result upVideo(String rowguid) {
        try {
            linkService.upVideo(rowguid);
            return new Result(true, ResultStatusCode.SUCCESS, "发布环节成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "发布环节失败!");
        }
    }

    @GetMapping("/findLinkId")
    @ApiOperation("根据课节rowguid查询环节")
    public Result findLinkId(String rowGuid) {
        try {
            List<LinkDetails> linkId = linkService.findLinkId(rowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "根据课节rowguid查询环节成功!", linkId);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "根据课节rowguid查询环节失败!");
        }
    }

    @PutMapping("/releaseCourse")
    @ApiOperation("发布课程")
    public Result releaseCourse(String RowGuid) {
        try {
            Course course = courseService.findById(RowGuid);
            if (course.gettClassTypeId()==1) { // 长期班
                if (course.getCoursePackageId()!=null && !course.getCoursePackageId().equals("")) {
                    courseService.releaseLongCourse(RowGuid);
                    return new Result(true, ResultStatusCode.SUCCESS, "长期班发布课程成功!");
                }
                else
                    return new Result(false, ResultStatusCode.FAIL, "该课程还没有课包,请创建课包后再发布!");
            } else if (course.gettClassTypeId()==2) { // 短期班
                if (course.gettPrice()!=null && !course.gettPrice().equals("")) {
                    courseService.releaseShortCourse(RowGuid);
                    return new Result(true, ResultStatusCode.SUCCESS, "短期班发布课程成功!");
                } else {
                    return new Result(false, ResultStatusCode.FAIL, "您还没有设置课程总价格，请先设置课程总价格!");
                }
            } else { // 免费班
                courseService.releaseShortCourse(RowGuid);
                return new Result(true, ResultStatusCode.SUCCESS, "发布课程成功!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "发布课程失败!");
        }
    }

    @PutMapping("/takenCourse")
    @ApiOperation("下架课程")
    public Result takenCourse(String RowGuid) {
        try {
            courseService.takenCourse(RowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "下架课程成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "下架课程失败!");
        }
    }

    @DeleteMapping("/deleteCourse")
    @ApiOperation("根据RowGuid删除课程")
    public Result deleteCourse(String RowGuid) {
        try {
            courseService.deleteCourse(RowGuid);
            return new Result(true, ResultStatusCode.SUCCESS, "删除课程成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "删除课程失败!");
        }
    }

    @PutMapping("/orderNo")
    @ApiOperation("设置课程排序号")
    public Result orderNoCourse(String RowGuid,Integer orderNo) {
        try {
            courseService.orderNoCourse(RowGuid,orderNo);
            return new Result(true, ResultStatusCode.SUCCESS, "设置课程排序号成功!");
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "设置课程排序号失败!");
        }
    }

}
