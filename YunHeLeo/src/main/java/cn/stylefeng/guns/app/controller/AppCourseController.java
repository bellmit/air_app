package cn.stylefeng.guns.app.controller;

import cn.stylefeng.guns.app.service.AppCourseService;
import cn.stylefeng.guns.app.service.StudentService;
import cn.stylefeng.guns.entity.R;
import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultCode;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.*;
import cn.stylefeng.guns.pojos.*;
import cn.stylefeng.guns.service.CourseService;
import cn.stylefeng.guns.utils.CreateFileUtil;
import cn.stylefeng.guns.utils.IdWorker;
import cn.stylefeng.guns.vo.ClassLinkVo;
import cn.stylefeng.guns.vo.WorkResponse;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.util.StringUtil;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@RestController
@RequestMapping("/app/api/course")
@CrossOrigin
@Api(value = "app首页", description = "app首页，随机获取课程")
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public class AppCourseController {

    private static final Logger logger = LoggerFactory.getLogger(AppCourseController.class);

    @Autowired
    private AppCourseService appCourseService;

    @GetMapping("/free")
    @ResponseBody
    @ApiOperation("随机获取6个免费课(当前登录用户rowguid)")
    public Result freeCourse(HttpServletRequest request) {
        try {
            String rowguid = request.getHeader("token");
            //String mobile = (String) request.getSession().getAttribute("mobile");
            if (rowguid == null) {
                List<Course> courseList = appCourseService.freeCourse();
                for (Course course : courseList) {
                    // 多少人购买该课程
                    Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                    // 查询课节数
                    //Integer classCount = appCourseService.findClassCount(course.getRowGuid());
                    if (buyCountCourse == null) {
                        buyCountCourse = 0;
                        course.setBuyCount(buyCountCourse);
                    } else {
                        course.setBuyCount(buyCountCourse);
                    }
                    if (course.gettLearnCount() == null) {
                        course.settLearnCount(0);
                    }
                    /*if (classCount == null) {
                        classCount = 0;
                        course.setClassCount(classCount);
                    } else {
                        course.setClassCount(classCount);
                    }*/
                    course.setBuyCount(buyCountCourse);
                }
                return new Result(true, 0, "随机获取6个免费课成功!", courseList);
            } else {
                User user = studentService.findByGuid(rowguid);
                List<Course> courseList = appCourseService.freeShowStageCourse(user.getShowStageId());
                for (Course course : courseList) {
                    // 多少人购买该课程
                    Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                    // 查询课节数
                    //Integer classCount = appCourseService.findClassCount(course.getRowGuid());
                    if (buyCountCourse == null) {
                        buyCountCourse = 0;
                        course.setBuyCount(buyCountCourse);
                    } else {
                        course.setBuyCount(buyCountCourse);
                    }
                    if (course.gettLearnCount() == null) {
                        course.settLearnCount(0);
                    }
                    /*if (classCount == null) {
                        classCount = 0;
                        course.setClassCount(classCount);
                    } else {
                        course.setClassCount(classCount);
                    }*/
                    course.setBuyCount(buyCountCourse);
                }
                return new Result(true, 0, "随机获取6个免费课成功!", courseList);
            }
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "随机获取6个免费课失败!");
        }
    }

    @Autowired
    private StudentService studentService;

    @GetMapping("/charge")
    @ResponseBody
    @ApiOperation("随机获取6个收费课(当前登录用户rowguid)")
    public Result chargeCourse(HttpServletRequest request) {
        try {
            String rowguid = request.getHeader("token");
            String mobile = (String) request.getSession().getAttribute("mobile");
            if (rowguid == null) {
                List<Course> courseList = appCourseService.chargeCourse();
                for (Course course : courseList) {
                    // 多少人购买该课程
                    Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                    // 查询课节数
                    //Integer classCount = appCourseService.findClassCount(course.getRowGuid());
                    if (buyCountCourse == null) {
                        buyCountCourse = 0;
                        course.setBuyCount(buyCountCourse);
                    } else {
                        course.setBuyCount(buyCountCourse);
                    }
                    if (course.gettLearnCount() == null) {
                        course.settLearnCount(0);
                    }
                    /*if (classCount == null) {
                        classCount = 0;
                        course.setClassCount(classCount);
                    } else {
                        course.setClassCount(classCount);
                    }*/
                }
                return new Result(true, 0, "随机获取6个收费课成功!", courseList);
            } else { // 根据用户选择的阶段筛选课程
                User user = studentService.findByGuid(rowguid);
                List<Course> courseList = appCourseService.chargeShowStageCourse(user.getShowStageId());
                for (Course course : courseList) {
                    // 多少人购买该课程
                    Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                    // 查询课节数
                    //Integer classCount = appCourseService.findClassCount(course.getRowGuid());
                    if (buyCountCourse == null) {
                        buyCountCourse = 0;
                        course.setBuyCount(buyCountCourse);
                    } else {
                        course.setBuyCount(buyCountCourse);
                    }
                    if (course.gettLearnCount() == null) {
                        course.settLearnCount(0);
                    }
                }
                return new Result(true, 0, "随机获取6个收费课成功!", courseList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "随机获取6个收费课失败!");
        }
    }

    @GetMapping("/branner")
    @ResponseBody
    @ApiOperation("获取branner")
    public Result branner() {
        try {
            List<Img> branner = appCourseService.branner();
            return new Result(true, 0, "获取branner成功!", branner);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "获取branner失败!", null, "0", null);
        }
    }

    @GetMapping("/search")
    @ResponseBody
    @ApiOperation("全局搜索课程")
    public Result search(String courseName) {
        try {
            List<CourseAll> courseAllList = appCourseService.search(courseName);
            return new Result(true, 0, "全局搜索课程成功!", courseAllList);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "全局搜索课程失败!", null, "0", null);
        }
    }

    /**
     * 根据课程类型 和 最新1 最热0 查询数据
     */
    @GetMapping("/course")
    @ResponseBody
    @ApiOperation("查询课程数据列表 type班型/类别分类 NewHot最新1与最热0")
    public Result course(int page, int size, int type, int NewHot, HttpServletRequest request) {
        try {
            String rowguid = request.getHeader("token");
            String mobile = (String) request.getSession().getAttribute("mobile");
            if (rowguid == null) {
                // type 免费班 0  长期班 1  短期班 2
                if (NewHot == 1) { // 最新
                    List<CourseList> courseAll = appCourseService.courseNew(page, size, type);
                    for (CourseList course : courseAll) {
                        // 多少人购买该课程
                        Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                        if (buyCountCourse == null) {
                            buyCountCourse = 0;
                            course.setBuyCount(buyCountCourse);
                        } else {
                            course.setBuyCount(buyCountCourse);
                        }
                        if (course.gettLearnCount() == null) {
                            course.settLearnCount(0);
                        }
                    }
                    return new Result(true, 0, "查询课程数据列表成功!", courseAll);
                } else {  // 最热
                    List<CourseList> courseAll = appCourseService.courseHot(page, size, type);
                    for (CourseList course : courseAll) {
                        // 多少人购买该课程
                        Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                        if (buyCountCourse == null) {
                            buyCountCourse = 0;
                            course.setBuyCount(buyCountCourse);
                        } else {
                            course.setBuyCount(buyCountCourse);
                        }
                        if (course.gettLearnCount() == null) {
                            course.settLearnCount(0);
                        }
                    }
                    return new Result(true, 0, "查询课程数据列表成功!", courseAll);
                }
            } else { // 根据用户选择的阶段筛选课程
                User user = studentService.findByGuid(rowguid);
                // type 免费班 0  长期班 1  短期班 2
                if (NewHot == 1) { // 最新
                    List<CourseList> courseAll = appCourseService.courseShowStageNew(page, size, type, user.getShowStageId());
                    for (CourseList course : courseAll) {
                        // 多少人购买该课程
                        Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                        if (buyCountCourse == null) {
                            buyCountCourse = 0;
                            course.setBuyCount(buyCountCourse);
                        } else {
                            course.setBuyCount(buyCountCourse);
                        }
                        if (course.gettLearnCount() == null) {
                            course.settLearnCount(0);
                        }
                    }
                    return new Result(true, 0, "查询课程数据列表成功!", courseAll);
                } else {  // 最热
                    List<CourseList> courseAll = appCourseService.courseStageHot(page, size, type, user.getShowStageId());
                    for (CourseList course : courseAll) {
                        // 多少人购买该课程
                        Integer buyCountCourse = appCourseService.findBuyCourseCount(course.getRowGuid());
                        if (buyCountCourse == null) {
                            buyCountCourse = 0;
                            course.setBuyCount(buyCountCourse);
                        } else {
                            course.setBuyCount(buyCountCourse);
                        }
                        if (course.gettLearnCount() == null) {
                            course.settLearnCount(0);
                        }
                    }
                    return new Result(true, 0, "查询课程数据列表成功!", courseAll);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询课程数据列表失败!");
        }
    }

    @GetMapping("/courseStage")
    @ResponseBody
    @ApiOperation("根据课程rowguid查询[阶段]+课节")
    public Result courseStage(String RowGuid, HttpServletRequest request) {
        try {
            Map<String, Object> map = new HashMap<>();
            CourseAll courseAlls = appCourseService.courseDetail(RowGuid);
            // 根据RowGuid查询课程阶段+课节
            if (courseAlls.gettClassTypeId() == 1) { // 长期班
                List<StageClass1Node> classList = appCourseService.stageClassList1(RowGuid, request);
                map.put("classList", classList);
                map.put("classTotalCount", appCourseService.ClassTotalCount(RowGuid));
                map.put("studyClassCount", appCourseService.ClassStudyCount(RowGuid));
                map.put("huacai", courseAlls.gettPainting());
                return new Result(true, 0, "根据课程rowguid课程阶段+课节成功!", map);
            } else if (courseAlls.gettClassTypeId() == 0) {  // 免费班
                List<Class1Dedails> freeClass = appCourseService.findFreeClass1(RowGuid, request);
                map.put("classList", freeClass);
                map.put("classTotalCount", appCourseService.findClassTotalCount(RowGuid));
                map.put("studyClassCount", appCourseService.findClassStudyCount(RowGuid));
                map.put("huacai", courseAlls.gettPainting());
                return new Result(true, 0, "根据课程rowguid课程阶段+课节成功!", map);
            } else if (courseAlls.gettClassTypeId() == 2) { // 短期班
                List<Class1Dedails> shortClass = appCourseService.findShortClass1(RowGuid, request);
                map.put("classList", shortClass);
                map.put("classTotalCount", appCourseService.findClassTotalCount(RowGuid));
                map.put("studyClassCount", appCourseService.findClassStudyCount(RowGuid));
                map.put("huacai", courseAlls.gettPainting());
                return new Result(true, 0, "根据课程rowguid课程阶段+课节成功!", map);
            } else {
                return new Result(true, 0, "根据课程rowguid课程类型不存在!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课程rowguid查询[阶段]+课节失败!");
        }
    }

    @GetMapping("/courseDetail")
    @ResponseBody
    @ApiOperation("根据课程rowguid课程详情(userguid当前登录用户rowguid)")
    public Result courseDetail(String RowGuid, String userguid) {
        try {
            Map<String, Object> map = new HashMap<>();
            CourseAll courseAlls = appCourseService.courseDetail(RowGuid);
            if (courseAlls != null) {
                // 根据RowGuid查询课程阶段+课节
                if (courseAlls.gettClassTypeId() == 1) { // 长期班
                    List<StageClassNode> classList = appCourseService.stageClassList(RowGuid);
                    map.put("courseDetails", courseAlls);
                    map.put("classList", classList);
                } else if (courseAlls.gettClassTypeId() == 0) {  // 免费班
                    List<ClassDedails> freeClass = appCourseService.findFreeClass(RowGuid);
                    map.put("courseDetails", courseAlls);
                    map.put("classList", freeClass);
                } else if (courseAlls.gettClassTypeId() == 2) { // 短期班
                    List<ClassDedails> shortClass = appCourseService.findShortClass(RowGuid);
                    map.put("courseDetails", courseAlls);
                    map.put("classList", shortClass);
                }
            } else {
                map.put("classList", "无数据");
                map.put("courseDetails", "无数据");
            }
            // 查询课节数
            Integer classCount = appCourseService.findClassCount(RowGuid);
            // map.put("classCount", classCount);
            // 查询阶段数
            Integer stageCount = appCourseService.findStageCount(RowGuid);
            //map.put("stageCount", stageCount);
            // 多少人购买该课程
            Integer buyCountCourse = appCourseService.findBuyCourseCount(RowGuid);
            // map.put("buyCount", buyCountCourse);
            // 根据课程rowguid查询课程评价
            map.put("evaluation", appCourseService.evaluation(RowGuid));
            // 标签数
            Map countLabel = appCourseService.countLabel(RowGuid);
            if (!countLabel.isEmpty())
                map.put("labelCount", countLabel);
            else
                map.put("labelCount", new HashMap<>());
            // 某课程有多少人评价
            map.put("countEvaluation", appCourseService.countEvaluation(RowGuid));
            // 计算某课程总分数
            map.put("sumScore", appCourseService.sumScore(RowGuid));
            // 是否购买过该课程
            boolean buyCourse = appCourseService.isBuyCourse(userguid, RowGuid);
            // 此课程是否加入课表
            boolean isInClassTable = appCourseService.isInClassTable(userguid, RowGuid);
            map.put("isInClassTable", isInClassTable);
            map.put("isBuyCourse", buyCourse);
            return new Result(true, 0, "根据课程rowguid课程成功!", map);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课程rowguid课程详情失败!");
        }
    }

    @PostMapping("/saveEvaluation")
    @ResponseBody
    @ApiOperation("根据课程rowguid评价课程")
    public Result saveEvaluation(String RowGuid, @RequestBody EvalCourse evalCourse, HttpServletRequest request) {
        try {
            appCourseService.saveEvaluation(RowGuid, evalCourse, request);
            return new Result(true, 0, "根据课程rowguid评价课程成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课程rowguid评价课程失败!");
        }
    }

    @GetMapping("/coursePackage")
    @ResponseBody
    @ApiOperation("根据课程rowguid查询课包 （userguid当前登录用户rowguid）")
    public Result coursePackage(String RowGuid, String userGuid, HttpServletRequest request) {
        try {
            String userId = request.getHeader("token");
            Map<String, Object> map = new HashMap<>();
            CourseAll courseAlls = appCourseService.courseDetail(RowGuid);
            // 检测课包的状态
            //List<PackageDetails> packageDetails = appCourseService.findUserPackage(userGuid);
            if (courseAlls.gettClassTypeId() == 1) {
                List<PackageDetails> coursePackage = appCourseService.findCoursePackage(RowGuid, userId);
//                map.put("coursePackage",coursePackage);
//                map.put("findUserPackageStatus",packageDetails);
                //map.put("totalPrice",courseAlls.getTPrice());
                return new Result(true, 0, "根据课程rowguid查询课包成功!", coursePackage);
            } else {
                return new Result(true, 0, "不是长期班 暂无课包!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课程rowguid查询课包失败!");
        }
    }

/*    @GetMapping("/findUserPackage")
    @ResponseBody
    @ApiOperation("课程详情-更多-查询课包用户购买状态（当前登录用户rowguid）")
    public Result findUserPackage(String rowGuid) {
        try {
            List<PackageDetails> packageDetails = appCourseService.findUserPackage(rowGuid);
            return new Result(true, 0, "查询课包用户购买状态成功!", packageDetails);
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "查询课包用户购买状态失败!");
        }
    }*/

    @GetMapping("/address")
    @ResponseBody
    @ApiOperation("选择收货地址(可忽略)")
    public Result address() {
        try {
            List<AddressXz> address = appCourseService.findAddress();
//            JSONArray jsonArray = JSONArray.fromObject(address);
//            String json = jsonArray.toString();
//            CreateFileUtil.createJsonFile(json, "C:\\Users\\baidu\\Desktop\\","agency");
            return new Result(true, 0, "查询地址成功!", address);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询地址失败!");
        }
    }

    @PostMapping("/editAddress")
    @ResponseBody
    @ApiOperation("编辑收货地址信息")
    public R editAddress(String people_username, String mobile, String address_detail, Long province_dic, Long city_dic,
                         Long area_dic, HttpServletRequest request) {
        try {
            String rowguid = request.getHeader("token");
            String address_id = appCourseService.editAddress(rowguid, people_username, mobile, address_detail, province_dic, city_dic, area_dic, request);
            Map<String, String> map = new HashMap<>();
            map.put("address_id", address_id);
            return new R(true, 0, map);
        } catch (Exception e) {
            return R.FAIL();
        }
    }

    @GetMapping("/findByIdAddress")
    @ResponseBody
    @ApiOperation("根据地址的id查看用户新增的收货地址信息")
    public R findByIdAddress(String addressId) {
        try {
            AddressDetails addressDetails = appCourseService.findByIdAddress(addressId);
            return new R(true, 0, addressDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/findAddress")
    @ResponseBody
    @ApiOperation("查看用户新增的收货地址信息")
    public Result findUserAddress(HttpServletRequest request) {
        try {
            String userId = request.getHeader("token");
            AddressDetails addressDetails = appCourseService.findUserAddress(userId);
            return new Result(true, 0, "查看用户新增的收货地址信息成功!", addressDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查看用户新增的收货地址信息失败!");
        }
    }

    @PostMapping("/saveOrder")
    @ResponseBody
    @ApiOperation("提交订单")
    public R saveOrder(String tCourseGuid, String tClassPackageGuid, String pay_type, HttpServletRequest request) {
        try {
            Map map = appCourseService.saveOrder(tCourseGuid, tClassPackageGuid, pay_type, request);
            return new R(true, 0, map);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @PostMapping("/secondOrderPay")
    @ResponseBody
    @ApiOperation("已有未支付订单 二次支付")
    public R secondOrderPay(String out_order_no, HttpServletRequest request) {
        try {
            Map map = appCourseService.secondOrderPay(out_order_no, request);
            return new R(true, 0, "", map);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/myOrder")
    @ResponseBody
    @ApiOperation("我的订单(当前登录用户的rowguid)")
    public R myOrder(HttpServletRequest request) {
        try {
            //Map<String, Object> order = appCourseService.myOrder(rowguid);
            List<MyOrderResponse> myOrder = appCourseService.findMyOrder(request);
            return new R(true, 0, myOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/findOrderDetail")
    @ResponseBody
    @ApiOperation("查询订单详情")
    public R findOrderDetail(@RequestParam("orderNo") String orderNo) {
        try {
            MyOrderResponse list = appCourseService.findOrderDetail(orderNo);
            return new R(true, 0, list);
        } catch (Exception e) {
            return R.FAIL();
        }
    }

    @GetMapping("/cancelOrder")
    @ResponseBody
    @ApiOperation("取消订单")
    public R cancelOrder(@RequestParam("orderNo") String orderNo) {
        try {
            Map map = appCourseService.cancelOrder(orderNo);
            return new R(true, 0, "取消订单成功！", map);
        } catch (Exception e) {
            return R.FAIL();
        }
    }

    @Autowired
    private CourseService courseService;

    @GetMapping("/selectByCourseInfo")
    @ResponseBody
    @ApiOperation("根据课程id OR 课包id查询课程信息")
    public R payOkSelect(String courseguid, String packageguid) {
        try {
            if (packageguid == null) {
                Course course = courseService.findById(courseguid);
                if (course.gettClassTypeId() == 1) {// 默认所有课包
                    String coursePackageId = course.getCoursePackageId();
                    String[] split = coursePackageId.split(",");
                    Map map = new HashMap();
                    List list = new ArrayList();
                    for (String packageguidsplit : split) {
                        CoursePackageResponse coursePackageResponse = appCourseService.findCoursePackageById(packageguidsplit);
                        if (coursePackageResponse == null) {
                            continue;
                        }
                        list.add(coursePackageResponse);
                    }
                    map.put("package", list);
                    map.put("course", course);
                    return new R(true, 0, "成功！", map);
                } else { // 免费和短期
                    Map map = new HashMap();
                    map.put("course", course);
                    return new R(true, 0, "成功！", map);
                }
            } else {
                Course course = courseService.findById(courseguid);
                String[] split = packageguid.split(",");
                Map map = new HashMap();
                List list = new ArrayList();
                Double price = 0.0;
                for (String packageguidsplit : split) {
                    CoursePackageResponse coursePackageResponse = appCourseService.findCoursePackageById(packageguidsplit);
                    Double packageResponsePrice = coursePackageResponse.getPrice();
                    price += packageResponsePrice;
                    list.add(coursePackageResponse);
                }
                price = (double) Math.round(price * 100) / 100;
                course.settPrice(price);
                map.put("package", list);
                map.put("course", course);
                return new R(true, 0, "成功！", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/myCourse")
    @ResponseBody
    @ApiOperation("我的课程(当前登录用户的rowguid)")
    public Result myCourse(HttpServletRequest request, Integer type) {
        try {
            String rowguid = request.getHeader("token");
            Set<CourseAll> order = appCourseService.myCourse(rowguid, type);
            return new Result(true, 0, "查询我的课程成功!", order);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询我的课程失败!");
        }
    }

    @GetMapping("/table_user")
    @ResponseBody
    @ApiOperation("根据当前登录用户查询课表 (当前登录用户rowguid)")
    public Result timetableUser(String rowguid, int page, int size, int condition, HttpServletRequest request) {
        try {
            // condition=1 查询当前时间之前
            // condition=2 查询当前时间之后
            // String rowguid = request.getHeader("token");
            List<TimetableDetailsResponse> beforeList = appCourseService.findTimetableBAUser(rowguid, page, size, condition, request);
            return new Result(true, 0, "根据当前登录用户查询课表成功!", beforeList);
            // List<TimetableDetailsResponse> list = appCourseService.findTimetableUser(rowguid, page, size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据当前登录用户查询课表失败!", null);
        }
    }

    @GetMapping("/findClassLink")
    @ResponseBody
    @ApiOperation("根据课节rowguid查询环节")
    public Result findClassLink(String rowguid, HttpServletRequest request) {
        try {
            List<ClassLinkVo> list = appCourseService.findClassLink(rowguid, request);
            return new Result(true, 0, "根据课节rowguid查询环节成功!", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据课节rowguid查询环节失败!", null);
        }
    }

    @GetMapping("/findType")
    @ResponseBody
    @ApiOperation("查询课程类别")
    public Result findType() {
        try {
            List<Type> list = appCourseService.findAll();
            return new Result(true, 0, "查询课程类别成功!", list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询课程类别失败!", null);
        }
    }

    @PostMapping("/goClass")
    @ResponseBody
    @ApiOperation("去上课 选择日期 加入课表(当前登录用户rowguid，课程courseguid：rowguid，星期week)")
    public Result goClass(String courseguid, String packagguid, Integer week, HttpServletRequest request) {
        try {
            String rowguid = request.getHeader("token");
            appCourseService.selectDate(rowguid, courseguid, packagguid, week);
            return new Result(true, 0, "去上课 加入课包成功!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "去上课 加入课表失败!", null);
        }
    }

    @PostMapping("/studyVideo")
    @ResponseBody
    @ApiOperation("进入环节学习看课（环节rowguid）")
    public R studyVideo(String rowguid, HttpServletRequest request) {
        try {
            appCourseService.studyVideo(rowguid, request);
            return R.SUCCESS();
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @PostMapping("/uploadWork")
    @ResponseBody
    @ApiOperation("根据课节rowguid上传作品[课节rowguid, 课程courseguid=rowguid]")
    public R uploadWork(@RequestParam("course_rowguid") String courseguid,
                        @RequestParam("class_rowguid") String classguid,
                        @RequestParam("link_rowguid") String linkguid,
                        @RequestParam("work_url") String url, HttpServletRequest request) {
        try {
            List<Link> list = appCourseService.uploadWork(classguid, courseguid, linkguid, url, request);
            return new R(true, 0, list);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/findMyWork")
    @ResponseBody
    @ApiOperation("查看我的作品 全课")
    public R findMyWork(String courseguid, HttpServletRequest request) {
        try {
            List<WorkResponse> workResponse = appCourseService.findMyWork(courseguid, request);
            return new R(true, 0, workResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/findMyClassWork")
    @ResponseBody
    @ApiOperation("查看我的作品 本节")
    public R findMyClassWork(String classguid, HttpServletRequest request) {
        try {
            List<WorkResponse> workResponse = appCourseService.findMyClassWork(classguid, request);
            return new R(true, 0, workResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/findWorkDetail")
    @ResponseBody
    @ApiOperation("查看我的作品详情")
    public R findWorkDetail(String rowguid) {
        try {
            WorkResponse workResponse = appCourseService.findWorkDetail(rowguid);
            return new R(true, 0, workResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/findMyWorkAll")
    @ResponseBody
    @ApiOperation("查看我的作品 全部")
    public R findMyWorkAll(HttpServletRequest request) {
        try {
            List<MyWorkResponse> workResponse = appCourseService.findMyWorkAll(request);
            return new R(true, 0, workResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/findMyCoursePackage")
    @ApiOperation("查看我的课包")
    public R findMyCoursePackage(String courseguid, HttpServletRequest request) {
        try {
            List<CoursePackageResponse> workResponse = appCourseService.findMyCoursePackage(courseguid, request);
            return new R(true, 0, workResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/twoCode")
    @ResponseBody
    @ApiOperation("查看二维码 课程的rowguid")
    public R twoCode(String rowguid) {
        try {
            TwoCode list = appCourseService.twoCode(rowguid);
            if (list!=null)
                return new R(true, 0, list);
            else {
                Map<String, String> strlist = new HashMap<>();
                strlist.put("id", null);
                strlist.put("imgUrl", "");
                return new R(true, 0, strlist);
            }
        } catch (Exception e) {
            return R.FAIL();
        }
    }

    @GetMapping("/findClassImage")
    @ResponseBody
    @ApiOperation("课节 范画")
    public R findClassImage(@RequestParam("class_rowguid") String rowguid) {
        try {
            ClassImageResponse list = appCourseService.findClassImage(rowguid);
            return new R(true, 0, list);
        } catch (Exception e) {
            return R.FAIL();
        }
    }

    @GetMapping("/getFreeCourse")
    @ResponseBody
    @ApiOperation("领取免费课")
    public R getFreeCourse(String courseguid, HttpServletRequest request) {
        try {
            String courseid = appCourseService.getFreeCourse(courseguid, request);
            return new R(true,0,"成功",courseid);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @GetMapping("/queryPayStatus")
    @ResponseBody
    @ApiOperation("查询支付结果 微信")
    public R queryPayStatus(String out_trade_no, HttpServletRequest request) {
        try {
            R result = null;

            Map queryMap = appCourseService.queryPayStatus(out_trade_no);
            if (queryMap == null) {
                result = new R(false, -1, "支付失败！");
            }

            if (queryMap.get("trade_state").equals("SUCCESS")) {
                Order order = appCourseService.findOrder(out_trade_no);
                logger.debug("订单信息：", order);
                CourseAll courseService = appCourseService.findCourseService(order.gettCourseGuid());
                appCourseService.updateWxOrderStatus(out_trade_no, order, request);
                result = new R(true, 0, "支付成功！", courseService);
            } else {
                result = new R(true, 1, "订单未支付！");
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @RequestMapping("/notify/url")
    @ResponseBody
    @ApiOperation("查询支付结果通知回调 微信")
    public R getWeChatReturn(HttpServletResponse response,
                             HttpServletRequest request) {
        try {
            // 获取网络输入流
            ServletInputStream is = request.getInputStream();
            // 缓冲区
            // 创建一个OutputStream输入文件中
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len=is.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            // 微信支付结果的字节数组
            byte[] bytes = baos.toByteArray();
            String xmlResult = new String(bytes, "UTF-8");
            logger.debug("xmlResult:"+xmlResult);
            System.out.println("xmlResult:"+xmlResult);
            // xml->map
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xmlResult);
            logger.debug("resultMap:"+resultMap);
            System.out.println("resultMap:"+resultMap);
            // 通信标识 return_code
            String return_code = resultMap.get("return_code");
            if (return_code.equals("SUCCESS")) {
                // 业务结果 result_code
                String result_code = resultMap.get("result_code");
                // 订单号 out_trade_no
                String out_trade_no = resultMap.get("out_trade_no");
                logger.debug("out_trade_no:"+out_trade_no);
                // 支付成功
                if (result_code.equals("SUCCESS")) {
                    logger.debug("支付成功！"+result_code);
                    // 微信支付交易流水号 transaction_id
                    // 订单号
                    Order order = appCourseService.findOrder(out_trade_no);
                    if (order.gettOrderStatus()!=2) { // 如果订单未完成支付  处理支付状态
                        CourseAll courseService = appCourseService.findCourseService(order.gettCourseGuid());
                        appCourseService.updateWxOrderStatus(order.gettOrderNo(), order, request);
                        return new R(true,0,"支付成功！", courseService);
                    }
                } else {
                    // 支付失败，关闭支付，取消订单
                    Map map = appCourseService.cancelOrder(out_trade_no);
                    return new R(true,1,"支付失败!关闭订单！", map);
                }

            }

            String result = "<xml><return_code><![CDATA[SUCCESS]]></return_code>" +
                    "<return_msg><![CDATA[OK]]></return_msg></xml>";
            return new R(true,0,"成功", result);
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL();
        }
    }

    @Value("${ali.pay.gateway-url}")
    private String gatewayUrl;

    @Value("${ali.pay.app-id}")
    private String appId;

    @Value("${ali.pay.alipay-public-key}")
    private String alipayPublicKey;

    @Value("${ali.pay.merchant-private-key}")
    private String merchantPrivateKey;

    @Value("${ali.pay.charset}")
    private String charset;

    @Value("${ali.pay.sign-type}")
    private String signType;

    @Value("${ali.pay.return-url}")
    private String returnUrl;

    @Value("${aliyun.notifyurl}")
    private String notifyUrl;

    @RequestMapping("/alipay")
    @ResponseBody
    @ApiOperation("查询支付结果通知回调 支付宝")
    public R getAliPayReturn(HttpServletResponse response,
                             HttpServletRequest request) {

        System.out.println("开始进入支付宝回调通知...");
        logger.debug("开始进入支付宝回调通知...");
        String payNotify = this.payNotify(request, response);
        logger.debug("payNotify:"+payNotify);

        CourseAll courseService = appCourseService.findCourseService(payNotify);
        logger.debug("courseService:"+courseService);
        return new R(true, 0, "支付宝回调成功", courseService);
    }

    /**
     * 支付宝 支付成功后回调支付结果
     *
     * @param request
     * @param response
     * @return
     */
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("支付宝支付成功后回调...");
        System.out.println("支付宝支付成功后回调...");
        Map<String, String> params = new HashMap<String, String>();
        System.out.println("params...");
        //1.从支付宝回调的request域中取值
        Map<String, String[]> requestParams = request.getParameterMap();
        logger.debug("requestParams...");
        System.out.println("requestParams...");
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = iter.next();
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }
        //2.封装必须参数
        String out_trade_no = request.getParameter("out_trade_no");            // 商户订单号
        String orderType = request.getParameter("body");                    // 订单内容
        String tradeStatus = request.getParameter("trade_status");            //交易状态

        logger.debug("支付宝异步通知-商户订单号out_trade_no:" + out_trade_no);
        logger.debug("支付宝异步通知-订单内容Body:" + orderType);
        logger.debug("支付宝异步通知-交易状态tradeStatus:" + tradeStatus);

        //3.签名验证(对支付宝返回的数据验证，确定是支付宝返回的)
        boolean signVerified = false;
        try {
            //3.1调用SDK验证签名
            signVerified = AlipaySignature.rsaCheckV1(params, alipayPublicKey,
                    "UTF-8", "RSA2");
            logger.debug("signVerified...");
            System.out.println("signVerified...");
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        //4.对验签进行处理
        if (signVerified) {    //验签通过
            if (tradeStatus.equals("TRADE_SUCCESS")) {    //只处理支付成功的订单: 修改交易表状态,支付成功
                Order order = appCourseService.findOrder(out_trade_no);// 根据订单号号查询
                Integer orderStatus = order.gettOrderStatus();
                System.out.println("orderStatus:"+orderStatus);
                if (orderStatus!=2) {// 订单未支付状态
                    appCourseService.updateWxOrderStatus(out_trade_no, order, request);// 修改订单状态
                }
                int s = this.payBack(out_trade_no);//根据订单编号去查询更改其数据订单格式
                if (s > 0) {
                    return order.gettCourseGuid();
                } else {
                    return "fail";
                }
            } else {
                return "fail";
            }
        } else {  //验签不通过
            System.err.println("验签失败");
            return "fail";
        }
    }

    @Transactional
    public int payBack(String notifyData) {
        try {
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
