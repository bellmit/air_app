package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.controller.CourseController;
import cn.stylefeng.guns.dao.*;
import cn.stylefeng.guns.pojo.*;
import cn.stylefeng.guns.pojos.*;
import cn.stylefeng.guns.service.CourseService;
import cn.stylefeng.guns.utils.IdWorker;
import com.github.pagehelper.PageHelper;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseTypeDao courseTypeDao;

    @Autowired
    private CourseTypesDao courseTypesDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StageDao stageDao;

    @Autowired
    private TClassDao classDao;

    @Autowired
    private StageClassDao stageClassDao;

    @Autowired
    private ClassDedailsDao classDedailsDao;

    @Autowired
    private CoursePackageDao coursePackageDao;

    @Autowired
    private PackageDetailsDao packageDetailsDao;

    @Autowired
    private CourseAllDao courseAllDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<CourseType> courseList() {
        return courseTypeDao.courseList();
    }

    @Override
    public String creatClassType(Course course,String userId, HttpServletRequest request) {
        String id = (String) request.getSession().getAttribute("id");
        String rowGuid = idWorker.nextId()+"";
        if (userId!=null) {
            SimpleDateFormat classNo = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date1 = new Date();
            // 设置班型id 长期班 短期班 免费班
            Course cours = new Course();
            cours.setRowGuid(rowGuid);
            // 课程编号
            cours.settCourseNum(classNo.format(date1));
            // 课程名称
            cours.settCourseName(course.gettCourseName());
            // 类别
            cours.settCourseTypeId(3);
            // 级别
            cours.settLevel("1");
            // 班级类型
            cours.settClassTypeId(course.gettClassTypeId());
            cours.settUpdateMan(userId);
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);
            cours.settUpdateDate(date);
            cours.settStatus(2); // 课程状态 0:正常 已发布 1:下架 2:保存 未发布
            courseDao.insert(cours);
        } else {
            // 设置班型id 长期班 短期班 免费班
            Course cours = new Course();
            cours.setRowGuid(rowGuid);
            SimpleDateFormat classNo = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date1 = new Date();
            classNo.format(date1);
            // 课程编号
            cours.settCourseNum(classNo.format(date1));
            // 类别
            cours.settCourseTypeId(1);
            // 级别
            cours.settLevel("1");
            // 课程名称
            cours.settCourseName(course.gettCourseName());
            // 班级类型
            cours.settClassTypeId(course.gettClassTypeId());
            cours.settUpdateMan(id);
            cours.settStatus(2); // 课程状态 0:正常 已发布 1:下架 2:保存 未发布
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);
            cours.settUpdateDate(date);
            courseDao.insert(cours);
        }
        return rowGuid;
    }

    @Override
    public CourseTypesResponse findTypesId(String rowGuid) {
        CourseTypes byId = courseTypesDao.findById(rowGuid);

        CourseTypesResponse courseTypesssss = new CourseTypesResponse();

        if (byId.gettTeacherId() != null) {
            String tTeacherId = byId.gettTeacherId();
            tTeacherId.replaceAll("[\\[\"\\]]", "");
            String[] splitteacher = tTeacherId.replaceAll("[\\[\"\\]]", "").split(",");
            courseTypesssss.settTeacherId(splitteacher);
        }
        String tServiceId = byId.gettServiceId();
        /*if (tServiceId!=null) {
            tServiceId.replaceAll("[\\[\\]]", "");
            String[] splitservice = tServiceId.replaceAll("[\\[\"\\]]", "").split(",");
            courseTypesssss.setTServiceId(splitservice);
        }*/
        courseTypesssss.settServiceId(tServiceId);
        String tLevel = byId.gettLevel();
        if (tLevel!=null){
            tLevel.replaceAll("[\\[\\]]", "");
            String[] splitlevel = tLevel.replaceAll("[\\[\"\\]]", "").split(",");
            Integer[] dosageArray = (Integer[]) ConvertUtils.convert(splitlevel, Integer.class);
            courseTypesssss.settLevel(dosageArray);
        }
        courseTypesssss.setRowGuid(byId.getRowGuid());
        courseTypesssss.setId(byId.getId());
        courseTypesssss.settPrice(byId.gettPrice());
        courseTypesssss.settCourseTypeId(byId.gettCourseTypeId());
        courseTypesssss.settClassName(byId.gettClassName());
        courseTypesssss.settClassTypeId(byId.gettClassTypeId());
        courseTypesssss.settCourseIntroduce(byId.gettCourseIntroduce());
        courseTypesssss.settCourseName(byId.gettCourseName());
        courseTypesssss.settTypeName(byId.gettTypeName());
        courseTypesssss.settPrompt(byId.gettPrompt());
        courseTypesssss.settImgUrl(byId.gettImgUrl());
        courseTypesssss.settPainting(byId.gettPainting());
        courseTypesssss.settTryListen(byId.gettTryListen());
        courseTypesssss.settStatus(byId.gettStatus());
        return courseTypesssss;
    }

    /**
     * 保存课程
     * @param RowGuid
     * @param course
     */
    @Override
    public void editCourseInfo(String RowGuid, Course course) {
        Course cours = courseDao.findById(RowGuid);
        // 课程名称
        cours.settCourseName(course.gettCourseName());
        // 课程分类
        cours.settCourseTypeId(course.gettCourseTypeId());
        // 适合级别  "[3,2]"
        String level = course.gettLevel();
        level = level.replaceAll("[\\[\\]]", "");
        cours.settLevel(level);
        // 授课老师
        cours.settTeacherId(course.gettTeacherId());
        // 客服老师
        String serviceId = course.gettServiceId();
        //serviceId = serviceId.replaceAll("[\\[\"\\]]", "");
        cours.settServiceId(serviceId);
        // 封面图片
        cours.settImgUrl(course.gettImgUrl());
        // 画材准备
        cours.settPainting(course.gettPainting());
        // 课程介绍
        cours.settCourseIntroduce(course.gettCourseIntroduce());
        // 温馨提示
        cours.settPrompt(course.gettPrompt());
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        cours.settUpdateDate(date);
        // cours.settStatus(2);// 课程状态 0:正常 已发布 1:下架 2:保存
        courseDao.updateById(cours);
    }

    /**
     * 保存并发布课程
     * @param RowGuid
     * @param course
     */
    @Override
    public void saveCourseInfo(String RowGuid, Course course) {
        Course cours = courseDao.findById(RowGuid);
        // 课程名称
        cours.settCourseName(course.gettCourseName());
        // 课程分类
        cours.settCourseTypeId(course.gettCourseTypeId());
        // 适合级别
        cours.settLevel(course.gettLevel());
        // 授课老师
        cours.settTeacherId(course.gettTeacherId());
        // 客服老师
        cours.settServiceId(course.gettServiceId());
        // 封面图片
        cours.settImgUrl(course.gettImgUrl());
        // 画材准备
        cours.settPainting(course.gettPainting());
        // 课程介绍
        cours.settCourseIntroduce(course.gettCourseIntroduce());
        // 温馨提示
        cours.settPrompt(course.gettPrompt());
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        cours.settUpdateDate(date);
        cours.settStatus(0);// 课程状态 0:正常 已发布 1:下架 2:保存
        courseDao.updateById(cours);
    }

    /**
     * 添加阶段
     * @param stage
     */
    @Override
    public String addStage(Stage stage, HttpServletRequest request) {
        String id = idWorker.nextId()+"";
        String userId = request.getHeader("userId");
        stage.setId(id);
        stage.setRowGuid(id);
        stage.settUpdateMan(userId);
        stage.settName(stage.gettName());
        stage.settStatus("0");// 状态 0 已发布 1 未发布 2 已下架
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        stage.settUpdateDate(date);
        stageDao.insert(stage);
        return id;
    }

    /**
     * 修改课程关联阶段id
     */
    @Override
    public void updateCourseStage(String RowGuid, String stageId) {
        Course course = courseDao.findById(RowGuid);
        if (course.getStageId()==null) {
            course.setStageId(stageId);
        } else {
            String stage = course.getStageId();
            String stageNewId = stage.concat(","+stageId);
            course.setStageId(stageNewId);
        }
        courseDao.updateById(course);
    }

    @Override
    public void updateStage(String RowGuid, Stage stage) {
        Stage sta = stageDao.findById(RowGuid);
        sta.settName(stage.gettName());
        stageDao.updateById(sta);
    }

    @Override
    public void classAddImage(String rowGuid, String userId,String imgurl) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        TClass tClass = new TClass();
        tClass.setUpdateDate(date);
        tClass.setUpdateMan(userId);
        tClass.setImgUrl(imgurl);
        tClass.setRowGuid(rowGuid);
        classDedailsDao.classAddImage(tClass);
    }

    /**
     * 查询阶段 包括对应的课节 长期班
     */
    @Override
    public List<StageClass> findStage(int page,int size, String RowGuid) {
        PageHelper.startPage(page, size);
        List<StageClass> stage = stageClassDao.findStage(RowGuid);
        System.out.println(stage);
        return stage;
    }

    public List<ClassDedailsResponse> findByRowGuidClass(String rowGuid) {
        List<ClassDedails> freeClass = classDedailsDao.findByRowGuidClass(rowGuid);
        System.out.println(freeClass);
        List list = new ArrayList();
        for (ClassDedails aClass : freeClass) {
            ClassDedailsResponse classDedailsResponse = new ClassDedailsResponse();
            classDedailsResponse.settName(aClass.getClassName());
            classDedailsResponse.setClassRowGuid(aClass.getClassRowGuid());
            classDedailsResponse.setImgUrl(aClass.getImgUrl());
            String tCreationName = aClass.gettCreationName();
            if (tCreationName!=null) {
                String[] splitCreation = tCreationName.split(",");
                Integer[] creation = (Integer[]) ConvertUtils.convert(splitCreation, Integer.class);
                classDedailsResponse.settCreationName(creation);
            }
            String tKnowledgeName = aClass.gettKnowledgeName();
            if (tKnowledgeName!=null) {
                String[] splitKnowledge = tKnowledgeName.split(",");
                Integer[] knowledge = (Integer[]) ConvertUtils.convert(splitKnowledge, Integer.class);
                classDedailsResponse.settKnowledgeName(knowledge);
            }
            String tTeacher = aClass.gettTeacher();
            if (tTeacher!=null) {
                String[] splitTeacher = tTeacher.split(",");
                classDedailsResponse.settTeacher(splitTeacher);
            }
            classDedailsResponse.settUpdateDate(aClass.gettUpdateDate());
            classDedailsResponse.settStatus(aClass.gettStatus());
            list.add(classDedailsResponse);
        }
        return list;
    }

    /**
     * 根据课程rowguid免费班查询课节
     */
    @Override
    public List<ClassDedailsResponse> findFreeClass(String rowGuid) {
        List<ClassDedails> freeClass = classDedailsDao.findFreeClass(rowGuid);
        System.out.println(freeClass);
        List list = new ArrayList();
        for (ClassDedails aClass : freeClass) {
            ClassDedailsResponse classDedailsResponse = new ClassDedailsResponse();
            classDedailsResponse.settName(aClass.getClassName());
            classDedailsResponse.setClassRowGuid(aClass.getClassRowGuid());
            classDedailsResponse.setImgUrl(aClass.getImgUrl());
            classDedailsResponse.settCourseName(aClass.gettCourseName());
            String tCreationName = aClass.gettCreationName();
            if (tCreationName!=null) {
                String[] splitCreation = tCreationName.split(",");
                Integer[] creation = (Integer[]) ConvertUtils.convert(splitCreation, Integer.class);
                classDedailsResponse.settCreationName(creation);
            }
            String tKnowledgeName = aClass.gettKnowledgeName();
            if (tKnowledgeName!=null) {
                String[] splitKnowledge = tKnowledgeName.split(",");
                Integer[] knowledge = (Integer[]) ConvertUtils.convert(splitKnowledge, Integer.class);
                classDedailsResponse.settKnowledgeName(knowledge);
            }
            String tTeacher = aClass.gettTeacher();
            if (tTeacher!=null) {
                String[] splitTeacher = tTeacher.split(",");
                classDedailsResponse.settTeacher(splitTeacher);
            }
            classDedailsResponse.settUpdateDate(aClass.gettUpdateDate());
            classDedailsResponse.settUsername(aClass.gettUsername());
            classDedailsResponse.settStatus(aClass.gettStatus());
            classDedailsResponse.settIstest(aClass.gettIstest());
            list.add(classDedailsResponse);
        }
        return list;
    }

    /**
     * 根据课程rowguid短期班查询课节
     */
    @Override
    public List<ClassDedailsResponse> findShortClass(String rowGuid) {
        List<ClassDedails> shortClass = classDedailsDao.findShortClass(rowGuid);
        System.out.println(shortClass);
        List list = new ArrayList();
        for (ClassDedails aClass : shortClass) {
            ClassDedailsResponse classDedailsResponse = new ClassDedailsResponse();
            classDedailsResponse.settName(aClass.getClassName());
            classDedailsResponse.setClassRowGuid(aClass.getClassRowGuid());
            classDedailsResponse.setImgUrl(aClass.getImgUrl());
            classDedailsResponse.settCourseName(aClass.gettCourseName());
            String tCreationName = aClass.gettCreationName();
            if (tCreationName!=null) {
                String[] splitCreation = tCreationName.split(",");
                Integer[] creation = (Integer[]) ConvertUtils.convert(splitCreation, Integer.class);
                classDedailsResponse.settCreationName(creation);
            }
            String tKnowledgeName = aClass.gettKnowledgeName();
            if (tKnowledgeName!=null) {
                String[] splitKnowledge = tKnowledgeName.split(",");
                Integer[] knowledge = (Integer[]) ConvertUtils.convert(splitKnowledge, Integer.class);
                classDedailsResponse.settKnowledgeName(knowledge);
            }
            String tTeacher = aClass.gettTeacher();
            if (tTeacher!=null) {
                String[] splitTeacher = tTeacher.split(",");
                classDedailsResponse.settTeacher(splitTeacher);
            }
            classDedailsResponse.settUpdateDate(aClass.gettUpdateDate());
            classDedailsResponse.settUsername(aClass.gettUsername());
            classDedailsResponse.settStatus(aClass.gettStatus());
            classDedailsResponse.settIstest(aClass.gettIstest());
            list.add(classDedailsResponse);
        }
        return list;
    }

    @Override
    public void updateShortPrice(String rowGuid, Double price) {
        Course course = courseDao.findById(rowGuid);
        course.settPrice(price);
        courseDao.updateById(course);
    }

    public Integer courseCount() {
        return courseAllDao.courseCount();
    }

    public void downClass(String rowGuid) {
        classDedailsDao.downClass(rowGuid);
    }

    public void upClass(String rowGuid) {
        classDedailsDao.upClass(rowGuid);
    }

    /**
     * 试听课节
     * @param rowGuid
     * @param isTest
     */
    public void isTestClass(String rowGuid, Boolean isTest) {
        if (isTest==true) {
            classDedailsDao.isTestTrueClass(rowGuid);
        } else {
            classDedailsDao.isTestFalseClass(rowGuid);
        }
    }

    /**
     * 查询所有课程
     * @return
     */
    @Override
    public List<CourseAll> findCourseAll(int page,int size,String query,
                                         Integer classTypeId, Integer courseTypeId, Integer tStatus) {
        PageHelper.startPage(page,size);
        Course course = new Course();
        course.settCourseName(query);
        course.settClassTypeId(classTypeId);// 班级类型
        course.settCourseTypeId(courseTypeId);
        course.settStatus(tStatus);
        List<CourseAll> courseAll = courseAllDao.findCourseAll(course);
        for (CourseAll all : courseAll) {
            if (all.gettPrice()==null) { // 免费班 或 未设置价格默认为0
                all.settPrice(0.00);
            }
            CourseAll courseCount = courseAllDao.buyCourseCount(all.getRowGuid());
            if (courseCount==null) {
                all.setBuyCount(0);
            } else if (all.getRowGuid().equals(courseCount.gettCourseGuid())) {
                all.setBuyCount(courseCount.getBuyCount());
            } else {
                all.setBuyCount(0);
            }
        }
        System.out.println(courseAll);
        return courseAll;
    }

    /**
     * 报名课程人数
     */
    public CourseAll buyCourseCount(String tCourseGuid) {

        return courseAllDao.buyCourseCount(tCourseGuid);
    }


    /**
     * 下架课包
     */
    public void downCoursePackage(String rowGuid) {
        CoursePackage coursePackage = coursePackageDao.findById(rowGuid);
        coursePackage.settStatus(2);// 课包状态 0未发布 1已发布 2已下架
        coursePackageDao.updateDownByRowGuid(coursePackage);
    }

    public void deleteCoursePackage(String rowGuid) {
        coursePackageDao.deleteCoursePackage(rowGuid);
    }

    /**
     * 编辑课包
     * @param rowGuid
     * @param coursePackage
     */
    public void editCoursePackage(String rowGuid, CoursePackage coursePackage) {
        CoursePackage cp = coursePackageDao.findById(rowGuid);
        cp.setPackageName(coursePackage.getPackageName());
        // 待激活时长
        cp.settActivateDate(coursePackage.gettActivateDate());
        // 可学习时长
        cp.settStudyDate(coursePackage.gettStudyDate());
        // 阶段id stageId="["7","8"]"
        String stageId = coursePackage.gettStageId();
        stageId = stageId.replaceAll("[\\[\"\\]]", "");
//        String substring = stageId.substring(1, stageId.length() - 1);
        cp.settStageId(stageId);
        // 价格
        cp.settPrice(coursePackage.gettPrice());
        coursePackageDao.updateByRowGuid(cp);
    }

    public Integer packageCount(String rowGuid) {
        return packageDetailsDao.packageCount(rowGuid);
    }

    /**
     * 查询当前课程下的所有课包
     * @param rowGuid
     * @return
     */
    public Map findCPAll(String rowGuid,int page,int size) {
        PageHelper.startPage(page,size);
        List<PackageDetails> cpAll = packageDetailsDao.findCPAll(rowGuid);
        Double totalPrice=0.00;// 课程总价格

        List list = new ArrayList();
        for (PackageDetails packageDetails : cpAll) {
            PackageDetails packageStatus = packageDetailsDao.findPackageStatus(packageDetails.getCprowGuid());
            Double tPrice = 0.00;
            if (packageStatus!=null) {
                tPrice = packageStatus.gettPrice();
                totalPrice += tPrice; // 课程总价
            }

            String stageId = "["+packageDetails.getStageId()+"]";// 2,3 -> [2,3] "stageId": "1,3",
            stageId.replaceAll("[\\[\"\\]]", "");
            String[] splitStageId = stageId.replaceAll("[\\[\"\\]]", "").split(",");
            //Integer[] dosageArray = (Integer[]) ConvertUtils.convert(splitStageId, Integer.class);

            PackageDetailsResponse packageDetailsResponse = new PackageDetailsResponse();
            packageDetailsResponse.setCpuRowGuid(packageDetails.getCpuRowGuid());
            packageDetailsResponse.setClassCount(packageDetails.getClassCount());
            packageDetailsResponse.settPrice(tPrice);// 课包价格
            packageDetailsResponse.setPackageName(packageDetails.getPackageName());
            packageDetailsResponse.settStageId(splitStageId);
            packageDetailsResponse.settStudyDate(packageDetails.gettStudyDate());
            packageDetailsResponse.setStageName(packageDetails.getStageName());
            packageDetailsResponse.settActivateDate(packageDetails.gettActivateDate());
            packageDetailsResponse.setCprowGuid(packageDetails.getCprowGuid());
            packageDetailsResponse.settStatus(packageDetails.gettStatus());
            packageDetailsResponse.settPackageGuid(packageDetails.gettPackageGuid());
            list.add(packageDetailsResponse);
        }
        Course course = courseDao.findById(rowGuid);
        course.settPrice(totalPrice);
        courseDao.updateById(course);
        System.out.println(cpAll);
        Map map = new HashMap();
        map.put("totalPrice", totalPrice);
        map.put("packageList", list);
        return map;
    }

    /**
     * 查询某课程下的所有阶段
     */
    public List<Stage> findAll(String RowGuid) {
        return stageDao.findAll(RowGuid);
    }

    /**
     * 创建课包
     * @param coursePackage
     */
    @Override
    public String addCoursePackage(CoursePackage coursePackage) {
        String id = idWorker.nextId()+"";
        coursePackage.setRowGuid(id);
        coursePackage.setPackageName(coursePackage.getPackageName());
        // 待激活时长
        coursePackage.settActivateDate(coursePackage.gettActivateDate());
        // 可学习时长
        coursePackage.settStudyDate(coursePackage.gettStudyDate());
        // 阶段id stageId="["7","8"]"
        String stageId = coursePackage.gettStageId();
        if (stageId!=null) {
            stageId = stageId.replaceAll("[\\[\"\\]]", "");
//        String substring = stageId.substring(1, stageId.length() - 1);
            coursePackage.settStageId(stageId);
        }
        // 价格
        coursePackage.settPrice(coursePackage.gettPrice());
        // 状态 0未发布 1已发布 2已下架
        coursePackage.settStatus(0);
        coursePackageDao.insert(coursePackage);
        return id;
    }

    public void releaseCoursePackage(String rowGuid) {
        coursePackageDao.releaseCoursePackage(rowGuid);
    }

    /**
     * 课包id关联到课程的课包id
     * @param rowGuid
     * @param cpId
     */
    public void updateCourse(String rowGuid, String cpId){
        Course byId = courseDao.findById(rowGuid);
        if (byId.getCoursePackageId()==null) {
            byId.setCoursePackageId(cpId);
        } else {
            String sid = byId.getCoursePackageId();
            if (sid.equals("")) {
                String cpNewId = sid.concat(cpId);
                byId.setCoursePackageId(cpNewId);
            } else {
                String cpNewId = sid.concat(","+cpId);
                byId.setCoursePackageId(cpNewId);
            }
        }
        courseDao.updateById(byId);
    }

    /**
     * 根据id查询阶段
     * @param id
     * @return
     */
    @Override
    public Stage findIdStage(String id) {
        Stage sta = stageDao.findById(id);
        System.out.println(sta);
        return sta;
    }

    /**
     * 根据id查询课节
     * @param rowGuid
     * @return
     */
    @Override
    public Map<String,Object> findByClass(String rowGuid) {
        ClassDedails tClass1 = classDedailsDao.findByClass1(rowGuid);
        ClassDedails tClass2 = classDedailsDao.findByClass2(rowGuid);
        ClassDedails tClass3 = classDedailsDao.findByClass3(rowGuid);
        ClassDedails tClass4 = classDedailsDao.findByClass4(rowGuid);
        Map<String,Object> map = new HashMap<>();
        map.put("class_teacher", tClass1);
        map.put("class_knowledge", tClass2);
        map.put("class_creation", tClass3);
        map.put("class_update", tClass4);
        System.out.println(tClass1);
        return map;
    }

    /**
     * 修改课节
     * @param rowGuid
     * @param tClass
     */
    @Override
    public void editClass(String rowGuid, TClass tClass) {
        TClass tCla = classDao.findByClass(rowGuid);
        tCla.settName(tClass.gettName());
        String teacher = tClass.gettTeacher();
        if (teacher!=null) {
            teacher = teacher.replaceAll("[\\[\"\\]]", "");
            tCla.settTeacher(teacher);
        }
        String methods = tClass.gettMethods();
        if (methods!=null) {
            methods = methods.replaceAll("[\\[\"\\]]", "");
            tCla.settMethods(methods);
        }
        //Integer[] dosageArray = (Integer[]) ConvertUtils.convert(splitlevel, Integer.class);
        String point = tClass.gettPoint();
        if (point!=null) {
            point = point.replaceAll("[\\[\"\\]]", "");
            tCla.settPoint(point);
        }
        tCla.setImgUrl(tClass.getImgUrl());
        tCla.setRowGuid(rowGuid);
        classDao.updateByRowGuid(tCla);
    }

    @Autowired
    private TClassResponseDao tClassResponseDao;

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);

    /**
     * 添加课节
     * @param tClass
     */
    @Override
    public String addClass(TClass tClass,String userId) {
        logger.debug("新增课节："+tClass);
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        TClassResponse tClassResponse = new TClassResponse();
        String id = idWorker.nextId()+"";
        tClassResponse.setId(id);
        tClassResponse.setRowGuid(id);
        tClassResponse.settName(tClass.gettName());
        // 授课老师
        String teacher = tClass.gettTeacher();
        if (teacher!=null) {
            teacher = teacher.replaceAll("[\\[\"\\]]", "");
            tClassResponse.settTeacher(teacher);
        }
        // 关联知识点
        String point = tClass.gettPoint();
        if (point!=null) {
            point = point.replaceAll("[\\[\"\\]]", "");
            tClassResponse.settPoint(point);
        }
        // 创作方式
        String methods = tClass.gettMethods();
        if (methods!=null) {
            methods = methods.replaceAll("[\\[\"\\]]", "");
            tClassResponse.settMethods(methods);
        }
        // 试听 不可试听
        tClassResponse.settIstest(false);
        tClassResponse.setStudyStatus(0); // 上课学习状态 0未开始 1已开始
        tClassResponse.settStatus(0); // 状态 0 已发布 1 未发布 2 已下架
        tClassResponse.setUpdateMan(userId);
        tClassResponse.setUpdateDate(date);
        tClassResponseDao.insert(tClassResponse);
        return id;
    }

    /**
     * 课程与课节关联
     */
    public void updateCourseClass(String courseRowGuid,String classId) {
        Course byId = courseDao.findById(courseRowGuid);
        if (byId.getClassId()==null) {
            byId.setClassId(classId);
        } else {
            String sid = byId.getClassId();
            if (sid.equals("")) {
                String classNewId = sid.concat(classId);
                byId.setClassId(classNewId);
            } else {
                String classNewId = sid.concat(","+classId);
                byId.setClassId(classNewId);
            }
        }
        courseDao.updateById(byId);
    }

    /**
     * 修改阶段关联课节RowGuid
     */
    public void updateStageClass(String RowGuid, String classId) {
        Stage stage = stageDao.findById(RowGuid);
        if (stage.gettClassId()==null) {
            stage.settClassId(classId);
        } else {
            String sid = stage.gettClassId();
            if (sid.equals("")) {
                String stageNewId = sid.concat(classId);
                stage.settClassId(stageNewId);
            } else {
                String stageNewId = sid.concat("," + classId);
                stage.settClassId(stageNewId);
            }
        }
        stageDao.updateById(stage);
    }

    @Override
    public Course findById(String RowGuid) {
        Course course = courseDao.findById(RowGuid);
        System.out.println(course);
        return course;
    }

    /**
     * 发布课程 根据id查询 发布【长期班】
     * @param RowGuid
     */
    @Override
    public void releaseLongCourse(String RowGuid) {
        Course course = courseDao.findById(RowGuid);
        course.settStatus(0);// 课程状态 0:正常 已发布 1:下架 2:保存 未发布
        courseDao.updateById(course);
    }

    /**
     * 发布课程 根据id查询 发布【短期班】
     * @param rowGuid
     */
    @Override
    public void releaseShortCourse(String rowGuid) {
        Course course = courseDao.findById(rowGuid);
        course.settStatus(0);// 课程状态 0:正常 已发布 1:下架 2:保存 未发布
        courseDao.updateById(course);
    }

    /**
     * 下架课程
     * @param RowGuid
     */
    @Override
    public void takenCourse(String RowGuid) {
        Course course = courseDao.findById(RowGuid);
        course.settStatus(1);// 课程状态 0:正常 已发布 1:下架 2:保存
        courseDao.updateById(course);
    }

    /**
     * 删除课程
     * @param RowGuid
     */
    @Override
    public void deleteCourse(String RowGuid) {
        courseDao.deleteByRowGuid(RowGuid);
    }

    /**
     * 设置课程排序号
     * @param RowGuid
     * @param orderNo
     */
    @Override
    public void orderNoCourse(String RowGuid, Integer orderNo) {
        Course course = courseDao.findById(RowGuid);
        course.settOrderNo(orderNo);
        courseDao.updateById(course);
    }

}
