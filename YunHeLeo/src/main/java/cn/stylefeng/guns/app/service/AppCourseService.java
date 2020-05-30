package cn.stylefeng.guns.app.service;

import cn.stylefeng.guns.app.controller.AppCourseController;
import cn.stylefeng.guns.config.AlipayConfig;
import cn.stylefeng.guns.dao.*;
import cn.stylefeng.guns.pojo.*;
import cn.stylefeng.guns.pojos.*;
import cn.stylefeng.guns.utils.HttpClient;
import cn.stylefeng.guns.utils.IdWorker;
import cn.stylefeng.guns.utils.TimeUtils;
import cn.stylefeng.guns.utils.VideoUtil;
import cn.stylefeng.guns.vo.ClassLinkVo;
import cn.stylefeng.guns.vo.WorkResponse;
//import cn.stylefeng.roses.core.util.MD5Util;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayDataDataserviceBillDingstaffbizorderQueryRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDingstaffbizorderQueryResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.github.pagehelper.PageHelper;
import com.github.wxpay.sdk.WXPayUtil;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.transaction.Transactional;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class AppCourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseAllDao courseAllDao;

    @Autowired
    private StageClassDao stageClassDao;

    @Autowired
    private ClassDedailsDao classDedailsDao;

    @Autowired
    private EvalCourseDao evalCourseDao;

    @Autowired
    private AppOrderDao appOrderDao;

    @Autowired
    private ImgDao imgDao;

    @Autowired
    private PackageDetailsDao packageDetailsDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 随机获取6个免费课
     *
     * @return
     */
    public List<Course> freeCourse() {
        List<Course> courses = courseDao.freeCourse();
        for (Course cours : courses) {
            Integer classCount = classDedailsDao.findClassTotalCount(cours.getRowGuid());
            cours.setClassCount(classCount);
            if (cours.gettPrice() == null) {
                cours.settPrice(0.00);
            }
        }
        return courses;
    }

    /**
     * 随机获取6个免费课 根据用户选择的阶段
     *
     * @return
     */
    public List<Course> freeShowStageCourse(Integer showStageId) {
        List<Course> courses = courseDao.freeShowStageCourse(showStageId);
        for (Course cours : courses) {
            Integer classCount = classDedailsDao.findClassTotalCount(cours.getRowGuid());
            cours.setClassCount(classCount);
            if (cours.gettPrice() == null) {
                cours.settPrice(0.00);
            }
        }
        return courses;
    }

    /**
     * 随机获取6个收费课
     *
     * @return
     */
    public List<Course> chargeCourse() {
        List<Course> courses = courseDao.chargeCourse();
        for (Course cours : courses) {
            Integer classCount = 0;
            if (cours.gettClassTypeId() == 1) {
                classCount = stageClassDao.findClassTotalCount(cours.getRowGuid());
                cours.setClassCount(classCount);
                //courseAllDao.findClassCount(courseAll.getRowGuid());
            } else {
                classCount = classDedailsDao.findClassTotalCount(cours.getRowGuid());
                cours.setClassCount(classCount);
            }
            if (cours.gettPrice() == null) {
                cours.settPrice(0.00);
            }
        }
        return courses;
    }

    /**
     * 随机获取6个收费课 根据用户选择的阶段
     *
     * @return
     */
    public List<Course> chargeShowStageCourse(Integer showStageId) {
        List<Course> courses = courseDao.chargeShowStageCourse(showStageId);
        for (Course cours : courses) {
            Integer classCount = 0;
            if (cours.gettClassTypeId() == 1) {
                classCount = stageClassDao.findClassTotalCount(cours.getRowGuid());
                cours.setClassCount(classCount);
                //courseAllDao.findClassCount(courseAll.getRowGuid());
            } else {
                classCount = classDedailsDao.findClassTotalCount(cours.getRowGuid());
                cours.setClassCount(classCount);
            }
            if (cours.gettPrice() == null) {
                cours.settPrice(0.00);
            }
        }
        return courses;
    }

    public List<Img> branner() {
        List<Img> branner = imgDao.branner();
        for (Img img : branner) {
            Course byId = courseDao.findById(img.getLinkId());
            if (byId!=null)
                img.setCourseTypeId(byId.gettClassTypeId());
        }
        return branner;
    }

    public List<Course> findByLevel(Integer levelId, Integer type) {
        return courseDao.findByLevel(levelId, type);
    }

    public List<Course> findLevelFree(Integer levelId) {
        return courseDao.findLevelFree(levelId);
    }

    public List<Course> findLeveCharge(Integer levelId) {
        return courseDao.findLeveCharge(levelId);
    }

    /**
     * 最新
     *
     * @param page
     * @param size
     * @param type
     * @return
     */
    public List<CourseList> courseNew(int page, int size, int type) {
        // 判断班型 0免费 1长期 2短期
        if (type == 0 || type == 1 || type == 2) {
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseNew(type);
            for (CourseList courseAll : courseAlls) {
                //Integer buyCourseCount = courseAllDao.findBuyCourseCount(courseAll.getRowGuid());
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                Integer stageCount = courseAllDao.findStageCount(courseAll.getRowGuid());
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (classCount == null) {
                    courseAll.setClassCount(0);
                } else {
                    courseAll.setClassCount(classCount);
                }
                if (stageCount == null) {
                    courseAll.setStageCount(0);
                } else {
                    courseAll.setStageCount(stageCount);
                }
            }
            return courseAlls;
        } else { // 课程类别id查询
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseType(type);
            for (CourseList courseAll : courseAlls) {
                Integer buyCourseCount = courseAllDao.findBuyCourseCount(courseAll.getRowGuid());
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                Integer stageCount = courseAllDao.findStageCount(courseAll.getRowGuid());
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (classCount == null) {
                    courseAll.setClassCount(0);
                } else {
                    courseAll.setClassCount(classCount);
                }
                if (stageCount == null) {
                    courseAll.setStageCount(0);
                } else {
                    courseAll.setStageCount(stageCount);
                }
            }
            return courseAlls;
        }
    }

    /**
     * 最新 根据用户选择的阶段
     *
     * @param page
     * @param size
     * @param type
     * @return
     */
    public List<CourseList> courseShowStageNew(int page, int size, int type, Integer showStageId) {
        // 判断班型 0免费 1长期 2短期
        if (type == 0 || type == 1 || type == 2) {
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseShowStageNew(type, showStageId);
            for (CourseList courseAll : courseAlls) {
                //Integer buyCourseCount = courseAllDao.findBuyCourseCount(courseAll.getRowGuid());
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                Integer stageCount = courseAllDao.findStageCount(courseAll.getRowGuid());
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (classCount == null) {
                    courseAll.setClassCount(0);
                } else {
                    courseAll.setClassCount(classCount);
                }
                if (stageCount == null) {
                    courseAll.setStageCount(0);
                } else {
                    courseAll.setStageCount(stageCount);
                }
            }
            return courseAlls;
        } else { // 课程类别id查询
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseShowStageType(type, showStageId);
            for (CourseList courseAll : courseAlls) {
                Integer buyCourseCount = courseAllDao.findBuyCourseCount(courseAll.getRowGuid());
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                //Integer classCount = courseAllDao.findClassCount(courseAll.getRowGuid());
                Integer stageCount = courseAllDao.findStageCount(courseAll.getRowGuid());
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (classCount == null) {
                    courseAll.setClassCount(0);
                } else {
                    courseAll.setClassCount(classCount);
                }
                if (stageCount == null) {
                    courseAll.setStageCount(0);
                } else {
                    courseAll.setStageCount(stageCount);
                }
            }
            return courseAlls;
        }
    }

    /**
     * 最热
     *
     * @param page
     * @param size
     * @param type
     * @return
     */
    public List<CourseList> courseHot(int page, int size, int type) {
        // 判断班型 0免费 1长期 2短期
        if (type == 0 || type == 1 || type == 2) {
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseHot(type);
            for (CourseList courseAll : courseAlls) {
                Integer buyCourseCount = courseAllDao.findBuyCourseCount(courseAll.getRowGuid());
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                //Integer classCount = courseAllDao.findClassCount(courseAll.getRowGuid());
                Integer stageCount = courseAllDao.findStageCount(courseAll.getRowGuid());
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
                if (classCount == null) {
                    courseAll.setClassCount(0);
                } else {
                    courseAll.setClassCount(classCount);
                }
                if (stageCount == null) {
                    courseAll.setStageCount(0);
                } else {
                    courseAll.setStageCount(stageCount);
                }
            }
            return courseAlls;
        } else {
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseTypeHot(type);
            for (CourseList courseAll : courseAlls) {
                Integer buyCourseCount = courseAllDao.findBuyCourseCount(courseAll.getRowGuid());
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                //Integer classCount = courseAllDao.findClassCount(courseAll.getRowGuid());
                Integer stageCount = courseAllDao.findStageCount(courseAll.getRowGuid());
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
                if (classCount == null) {
                    courseAll.setClassCount(0);
                } else {
                    courseAll.setClassCount(classCount);
                }
                if (stageCount == null) {
                    courseAll.setStageCount(0);
                } else {
                    courseAll.setStageCount(stageCount);
                }
            }
            return courseAlls;
        }
    }

    public List<CourseAll> courseStageNew(int page, int size, int type, Integer stageId) {
        // 判断班型 0免费 1长期 2短期
        if (type == 0 || type == 1 || type == 2) {
            PageHelper.startPage(page, size);
            List<CourseAll> courseAlls = courseAllDao.courseStageNew(type, stageId);
            for (CourseAll courseAll : courseAlls) {
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                courseAll.setClassCount(classCount);
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
            }
            return courseAlls;
        } else { // 课程类别id查询
            PageHelper.startPage(page, size);
            List<CourseAll> courseAlls = courseAllDao.courseStageType(type, stageId);
            for (CourseAll courseAll : courseAlls) {
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                courseAll.setClassCount(classCount);
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
            }
            return courseAlls;
        }
    }

    public List<CourseList> courseStageHot(int page, int size, int type, Integer stageId) {
        // 判断班型 0免费 1长期 2短期
        if (type == 0 || type == 1 || type == 2) {
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseStageHot(type, stageId);
            for (CourseList courseAll : courseAlls) {
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                courseAll.setClassCount(classCount);
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
            }
            return courseAlls;
        } else {
            PageHelper.startPage(page, size);
            List<CourseList> courseAlls = courseAllDao.courseStageTypeHot(type, stageId);
            for (CourseList courseAll : courseAlls) {
                Integer classCount = 0;
                if (courseAll.gettClassTypeId() == 1) {
                    classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                    //courseAllDao.findClassCount(courseAll.getRowGuid());
                } else {
                    classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                }
                courseAll.setClassCount(classCount);
                if (courseAll.gettPrice() == null) {
                    courseAll.settPrice(0.00);
                }
                if (courseAll.gettScore() == null) {
                    courseAll.settScore(0);
                }
                if (courseAll.gettLearnCount() == null) {
                    courseAll.settLearnCount(0);
                }
            }
            return courseAlls;
        }
    }

    /*
     * 当前课程下已学习课节数量
     */
    public int ClassStudyCount(String RowGuid) {
        return stageClassDao.findClassStudyCount(RowGuid);
    }

    /**
     * 当前课程下总课节数量
     */
    public int ClassTotalCount(String RowGuid) {
        return stageClassDao.findClassTotalCount(RowGuid);
    }

    /**
     * 免费班和短期班 当前课程下总课节数量
     */
    public int findClassTotalCount(String RowGuid) {
        Integer classTotalCount = classDedailsDao.findClassTotalCount(RowGuid);// 当前课程下课节总数
        System.out.println(classTotalCount);
        return classTotalCount;
    }

    /**
     * 免费班和短期班 当前课程下已学习的课节数量
     */
    public int findClassStudyCount(String RowGuid) {
        return classDedailsDao.findClassStudyCount(RowGuid);// 当前课程下已学习课节数量
    }

    /**
     * 根据rowguid查询课程详情
     */
    public CourseAll courseDetail(String RowGuid) {
        Integer classStudyCount = stageClassDao.findClassStudyCount(RowGuid);
        CourseAll courseAll = courseAllDao.courseDetail(RowGuid);
        if (courseAll.gettClassTypeId()==1) { // 长期班
            Integer classTotalCount = stageClassDao.findClassTotalCount(RowGuid);
            courseAll.setClassCount(classTotalCount);// 当前课程下总课节数量
            if (classTotalCount == null) {
                classTotalCount = 0;
                courseAll.setClassCount(classTotalCount);
            } else {
                courseAll.setClassCount(classTotalCount);
            }
        } else { // 免费班和短期班
            Integer classTotalCount = classDedailsDao.findClassTotalCount(RowGuid);// 当前课程下课节总数
            courseAll.setClassCount(classTotalCount);// 当前课程下总课节数量
            if (classTotalCount == null) {
                classTotalCount = 0;
                courseAll.setClassCount(classTotalCount);
            } else {
                courseAll.setClassCount(classTotalCount);
            }
        }
        courseAll.setStudyClassCount(classStudyCount);// 当前课程下已学习课节数量
        Integer buyCourseCount = courseAllDao.findBuyCourseCount(RowGuid);
        //Integer classCount = courseAllDao.findClassCount(RowGuid);
        Integer stageCount = courseAllDao.findStageCount(RowGuid);
        if (courseAll.getBuyCount() == null) {
            courseAll.setBuyCount(0);
        }
        if (courseAll.gettPrice() == null) {
            courseAll.settPrice(0.00);
        }
        if (courseAll.getCourseCount() == null) {
            courseAll.setCourseCount(0);
        }
        if (courseAll.gettScore() == null) {
            courseAll.settScore(0);
        }
        if (buyCourseCount == null) {
            buyCourseCount = 0;
            courseAll.setBuyCount(buyCourseCount);
        } else {
            courseAll.setBuyCount(buyCourseCount);
        }

        if (stageCount == null) {
            stageCount = 0;
            courseAll.setStageCount(stageCount);
        } else {
            courseAll.setStageCount(stageCount);
        }
        return courseAll;
    }

    @Autowired
    private ClassLinkVoDao classLinkVoDao;

    /**
     * 获取课程阶段+课节 长期班
     */
    public List<StageClass> stageClassList(String RowGuid) {
        Map<String, Object> map = new HashMap<>();
        List<StageClass> stage = stageClassDao.findStage(RowGuid);
        Integer classTotalCount = stageClassDao.findClassTotalCount(RowGuid);
        Integer classStudyCount = stageClassDao.findClassStudyCount(RowGuid);
        for (StageClass stageClass : stage) {
            // 某阶段下课节数
            Integer stageClassCount = stageClassDao.findStageClassCount(stageClass.getStageRowGuid());
            stageClass.setClassCount(stageClassCount);
            Integer buyCourseCount = courseAllDao.findBuyCourseCount(RowGuid);
            Integer classCount = courseAllDao.findClassCount(RowGuid);
            stageClass.setStudyClassCount(classStudyCount);// 当前课程下已学习课节数量

            if (stageClass.gettIstest() == null) {
                stageClass.settIstest(0);
            }
            if (buyCourseCount == null) {
                stageClass.setBuyCount(0);
            } else {
                stageClass.setBuyCount(buyCourseCount);
            }
            /*if ( classCount == null ) {
                stageClass.setClassCount(0);
            } else {
                stageClass.setClassCount(classTotalCount);
            }*/
            if (stageClass.getBuyCount() == null) {
                stageClass.setBuyCount(0);
            }
        }
        return stage;
    }

    @Autowired
    private StageClass1Dao stageClasDao;

    /**
     * 获取课程阶段+课节 长期班 courseStage
     */
    public List<StageClass1Node> stageClassList1(String RowGuid, HttpServletRequest request) {
        List<StageClass1Node> stage = stageClasDao.findStage(RowGuid);
        Integer classTotalCount = stageClassDao.findClassTotalCount(RowGuid);
        Integer classStudyCount = stageClassDao.findClassStudyCount(RowGuid);
        for (StageClass1Node stageClass : stage) {
            // 某阶段下课节数
            Integer stageClassCount = stageClassDao.findStageClassCount(stageClass.getStageRowGuid());
            stageClass.setStageClassCount(stageClassCount);
            Integer buyCourseCount = courseAllDao.findBuyCourseCount(RowGuid);
            //Integer classCount = courseAllDao.findClassCount(RowGuid);
            stageClass.setStudyClassCount(classStudyCount);// 当前课程下已学习课节数量
            if (stageClass.gettIstest() == null) {
                stageClass.settIstest(0);
            }
            if (buyCourseCount == null) {
                stageClass.setBuyCount(0);
            } else {
                stageClass.setBuyCount(buyCourseCount);
            }
            if (stageClassCount == null) {
                stageClass.setClassCount(0);
            } else {
                stageClass.setClassCount(stageClassCount);
            }
            if (stageClass.getBuyCount() == null) {
                stageClass.setBuyCount(0);
            }
            String userId = request.getHeader("token");
            for (int i = 0; i < stage.size(); i++) {
                String classRowGuid = stage.get(i).getClassRowGuid();
                List<StageClass1Node> children = stageClass.getChildren();
                List<ClassLinkVo> classLinkStatus = classLinkVoDao.findClassLinkStatus(classRowGuid);
                for (ClassLinkVo linkStatus : classLinkStatus) {
                    UserLinkStatus linkStudyStatus = timetableDetailsDao.findLinkStudyStatus(userId, linkStatus.getLinkRowGuid());
                    if (linkStudyStatus == null) {
                        linkStatus.setStudyStatus(0);// 未开始学习
                    } else {
                        linkStatus.setStudyStatus(1);// 已学习
                    }
                }
                for (StageClass1Node child : children) {
                    System.out.println(child);
                    if (classRowGuid==null) {
                        child.setList(classLinkStatus);
                    }
                    else if (classRowGuid.equals(child.getClassRowGuid()))
                        child.setList(classLinkStatus);
                    for (ClassLinkVo classLinkVo : classLinkStatus) {
                        if (classLinkVo.gettLinkName().equals("作品")) {
                            classLinkVo.setType(3);
                        } else if (classLinkVo.gettLinkName().equals("预习")) {
                            classLinkVo.setType(1);
                        } else if (classLinkVo.gettLinkName().equals("上课")) {
                            classLinkVo.setType(2);
                        }
                    }

                    // 课节是否可学习
                    String childClassRowGuid = child.getClassRowGuid();
                    StageClass1 studyClass = classLinkVoDao.isStudyClass(childClassRowGuid, userId);
                    // 加锁不可学习情况：1）状态为2 课包已到期，2）null 课包未购买
                    if (studyClass==null) { // 课包未购买
                        child.setIsStudy(false);
                    } else if (studyClass.getStatus()==2) { // 课包已到期
                        child.setIsStudy(false); // 该课节不可学
                    } else {
                        child.setIsStudy(true);
                    }
                }

            }
        }
        return stage;
    }

    /**
     * 获取课节 免费班
     */
    public List<ClassDedails> findFreeClass(String RowGuid) {
        List<ClassDedails> freeClass = classDedailsDao.findFreeClass(RowGuid);
        Integer classTotalCount = classDedailsDao.findClassTotalCount(RowGuid);// 当前课程下课节总数
        Integer classStudyCount = classDedailsDao.findClassStudyCount(RowGuid);// 当前课程下已学习课节数量
        for (ClassDedails aClass : freeClass) {
            Integer buyCourseCount = courseAllDao.findBuyCourseCount(RowGuid);
            Integer classCount = courseAllDao.findClassCount(RowGuid);
            aClass.setStudyClassCount(classStudyCount);

            if (aClass.gettIstest() == null) {
                aClass.settIstest(0);
            }
            if (buyCourseCount == null) {
                aClass.setBuyCount(0);
            } else {
                aClass.setBuyCount(buyCourseCount);

            }
            if (classTotalCount == null) {
                aClass.setClassCount(0);
            } else {
                aClass.setClassCount(classTotalCount);
            }
            if (aClass.getBuyCount() == null) {
                aClass.setBuyCount(0);
            }
        }
        return freeClass;
    }

    @Autowired
    private Class1DedailsDao class1DedailsDao;

    /**
     * 获取课节 免费班 courseStage
     */
    public List<Class1Dedails> findFreeClass1(String RowGuid, HttpServletRequest request) {
        List<Class1Dedails> freeClass = class1DedailsDao.findFreeClass(RowGuid);
        Integer classTotalCount = classDedailsDao.findClassTotalCount(RowGuid);// 当前课程下课节总数
        Integer classStudyCount = classDedailsDao.findClassStudyCount(RowGuid);// 当前课程下已学习课节数量
        for (Class1Dedails aClass : freeClass) {
            Integer buyCourseCount = courseAllDao.findBuyCourseCount(RowGuid);
            Integer classCount = courseAllDao.findClassCount(RowGuid);
            aClass.setStudyClassCount(classStudyCount);

            if (aClass.gettIstest() == null) {
                aClass.settIstest(0);
            }
            if (buyCourseCount == null) {
                aClass.setBuyCount(0);
            } else {
                aClass.setBuyCount(buyCourseCount);
            }
            if (classCount == null) {
                aClass.setClassCount(0);
            } else {
                aClass.setClassCount(classTotalCount);
            }
            if (aClass.getBuyCount() == null) {
                aClass.setBuyCount(0);
            }
            String userId = request.getHeader("token");
            List<ClassLinkVo> classLinkStatus = classLinkVoDao.findClassLinkStatus(aClass.getClassRowGuid());
            for (ClassLinkVo linkStatus : classLinkStatus) {
                UserLinkStatus linkStudyStatus = timetableDetailsDao.findLinkStudyStatus(userId, linkStatus.getLinkRowGuid());
                if (linkStudyStatus == null) {
                    linkStatus.setStudyStatus(0);// 未开始学习
                } else {
                    linkStatus.setStudyStatus(1);// 已学习
                }
            }
            aClass.setList(classLinkStatus);
            for (ClassLinkVo classLinkVo : classLinkStatus) {
                if (classLinkVo.gettLinkName().equals("作品")) {
                    classLinkVo.setType(3);
                } else if (classLinkVo.gettLinkName().equals("预习")) {
                    classLinkVo.setType(1);
                } else if (classLinkVo.gettLinkName().equals("上课")) {
                    classLinkVo.setType(2);
                }
            }
        }
        return freeClass;
    }

    /**
     * 获取课节 短期班
     */
    public List<ClassDedails> findShortClass(String RowGuid) {
        List<ClassDedails> shortClass = classDedailsDao.findShortClass(RowGuid);
        Integer classTotalCount = classDedailsDao.findClassTotalCount(RowGuid);// 当前课程下课节总数
        Integer classStudyCount = classDedailsDao.findClassStudyCount(RowGuid);// 当前课程下已学习课节数量
        for (ClassDedails aClass : shortClass) {
            Integer buyCourseCount = courseAllDao.findBuyCourseCount(RowGuid);
            Integer classCount = courseAllDao.findClassCount(RowGuid);
            aClass.setStudyClassCount(classStudyCount);
            if (aClass.gettIstest() == null) {
                aClass.settIstest(0);
            }
            if (buyCourseCount == null) {
                aClass.setBuyCount(0);
            } else {
                aClass.setBuyCount(buyCourseCount);
            }
            if (classTotalCount == null) {
                aClass.setClassCount(0);
            } else {
                aClass.setClassCount(classTotalCount); // 当前课程下总课节数
            }
            if (aClass.getBuyCount() == null) {
                aClass.setBuyCount(0);
            }
        }
        return shortClass;
    }

    /**
     * 获取课节 短期班 courseStage
     */
    public List<Class1Dedails> findShortClass1(String RowGuid, HttpServletRequest request) {
        List<Class1Dedails> shortClass = class1DedailsDao.findShortClass(RowGuid);
        Integer classTotalCount = classDedailsDao.findClassTotalCount(RowGuid);// 当前课程下课节总数
        Integer classStudyCount = classDedailsDao.findClassStudyCount(RowGuid);// 当前课程下已学习课节数量
        for (Class1Dedails aClass : shortClass) {
            Integer buyCourseCount = courseAllDao.findBuyCourseCount(RowGuid);
            Integer classCount = courseAllDao.findClassCount(RowGuid);
            aClass.setStudyClassCount(classStudyCount);
            if (aClass.gettIstest() == null) {
                aClass.settIstest(0);
            }
            if (buyCourseCount == null) {
                aClass.setBuyCount(0);
            } else {
                aClass.setBuyCount(buyCourseCount);
            }
            if (classCount == null) {
                aClass.setClassCount(0);
            } else {
                aClass.setClassCount(classTotalCount); // 当前课程下总课节数
            }
            if (aClass.getBuyCount() == null) {
                aClass.setBuyCount(0);
            }
            String userId = request.getHeader("token");
            List<ClassLinkVo> classLinkStatus = classLinkVoDao.findClassLinkStatus(aClass.getClassRowGuid());
            for (ClassLinkVo linkStatus : classLinkStatus) {
                UserLinkStatus linkStudyStatus = timetableDetailsDao.findLinkStudyStatus(userId, linkStatus.getLinkRowGuid());
                if (linkStudyStatus == null) {
                    linkStatus.setStudyStatus(0);// 未开始学习
                } else {
                    linkStatus.setStudyStatus(1);// 已学习
                }
            }
            aClass.setList(classLinkStatus);
            for (ClassLinkVo classLinkVo : classLinkStatus) {
                if (classLinkVo.gettLinkName().equals("作品")) {
                    classLinkVo.setType(3);
                } else if (classLinkVo.gettLinkName().equals("预习")) {
                    classLinkVo.setType(1);
                } else if (classLinkVo.gettLinkName().equals("上课")) {
                    classLinkVo.setType(2);
                }
            }
        }
        return shortClass;
    }

    /**
     * 查询某课程所有评价
     */
    public List<CourseAll> evaluation(String RowGuid) {
        List<CourseAll> evaluation = courseAllDao.evaluation(RowGuid);
        for (CourseAll courseAll : evaluation) {
            String imgUrl = courseAll.gettImgUrl();
            if (imgUrl != null)
                courseAll.settImgUrl("http://airffter.oss-cn-beijing.aliyuncs.com/" + imgUrl);
            else
                courseAll.settImgUrl("");
        }
        return evaluation;
    }

    /**
     * 判断当前用户是否购买了该课程
     * 登录用户guid和课程guid
     */
    public boolean isBuyCourse(String userguid, String RowGuid) {
        Course courseById = courseDao.findById(RowGuid);
        Integer classTypeId = courseById.gettClassTypeId();
        if (classTypeId == 0) { // 免费课
            String course = orderDao.isBuyFreeCourse(userguid, RowGuid);
            if (course == null) { // 当前用户没有购买该课程
                return false;
            } else {
                return true;
            }
        } else { // 长期课 短期课
            UOCCP course = orderDao.isBuyCourse(userguid, RowGuid);
            if (course == null) { // 当前用户没有购买该课程
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * 某用户 某课程是否加入课表
     *
     * @param userguid
     * @param rowGuid
     * @return
     */
    public boolean isInClassTable(String userguid, String rowGuid) {
        Timetable timetable = timetableDetailsDao.isInClassTable(userguid, rowGuid);
        if (timetable == null)
            return false;
        else
            return true;
    }

    /**
     * 某课程有多少人评价
     *
     * @param RowGuid
     * @return
     */
    public int countEvaluation(String RowGuid) {
        return courseAllDao.countEvaluation(RowGuid);
    }

    /**
     * 标签数
     */
    public Map countLabel(String rowguid) {
        Map<String, Integer> map = new HashMap();
        EvalCourse evalCourses = evalCourseDao.countLabel(rowguid);
        if (evalCourses != null) {
            String labelId = evalCourses.gettLabelId();
            System.out.println(labelId);
            String[] split = labelId.split(",");
            Integer[] splitLabelId = (Integer[]) ConvertUtils.convert(split, Integer.class);
            labelId = labelId.replace(",", "");
            for (int i = 0; i < labelId.length(); i++) {
                char ch = labelId.charAt(i);
                String chstr = String.valueOf(ch);
                if (map.get(ch) != null) {
                    map.put(chstr, map.get(ch) + 1);
                } else {
                    map.put(chstr, 1);
                }
            }
        }
        return map;
    }

    /**
     * 计算某课程总分数
     */
    public Double sumScore(String RowGuid) {
        Double score = courseAllDao.sumScore(RowGuid);
        if (score == null) {
            score = 0.00;
        }
        return score;
    }

    @Autowired
    private LogDao logDao;

    /**
     * 评价某课程
     *
     * @param rowGuid
     * @param evalCourse
     */
    public void saveEvaluation(String rowGuid, EvalCourse evalCourse, HttpServletRequest request) {
        String userId = request.getHeader("token");
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        // 获取当前登录用户id
        // String userId = (String) request.getSession().getAttribute("row_guid");

        evalCourse.setRowGuid(idWorker.nextId() + "");
        // 评价标签 1,2,3,...
//        evalCourse.settLabelId(evalCourse.gettLabelId());
        // 评价内容
//        evalCourse.settContent(evalCourse.gettContent());
        // 分值
//        evalCourse.settScore(evalCourse.gettScore());
        // 日期
        evalCourse.settUpdateDate(date);
        // 用户id
        evalCourse.settUserGuid(userId);
        evalCourseDao.insert(evalCourse);

        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId() + "");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("学生评价课程");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询某课程下所有的课包
     */
    public List<PackageDetails> findCoursePackage(String rowGuid, String userGuid) {
        List<PackageDetails> cpAll = packageDetailsDao.findAppCPAll(rowGuid);
        List<PackageDetails> userPackage = packageDetailsDao.findUserPackage(userGuid, rowGuid);
        for (PackageDetails packageDetails : cpAll) {
            Integer classCount = courseAllDao.findClassCount(packageDetails.getCprowGuid());
            packageDetails.setCanChoose(true);
            if (classCount == null) {
                classCount = 0;
                packageDetails.setClassCount(classCount);
            } else {
                packageDetails.setClassCount(classCount);
            }
            for (PackageDetails details : userPackage) { // 课包
                if (packageDetails.getCprowGuid().equals(details.gettPackageGuid())) { // 课包rowguid
                    if (details.gettStatus() == 2) { // 已到期
                        packageDetails.setCanChoose(true);// 可选择
                    } else if (details.gettStatus() != 2) {
                        packageDetails.setCanChoose(false);// 不可选择
                    } else if (packageDetails != details) {
                        packageDetails.setCanChoose(true);
                    }
                }
            }
        }
        return cpAll;
    }

    /**
     * 查询判断当前用户是否购买过课包
     * 课包状态 0未开始 1正在学 2已到期 3未激活
     */
    public List<PackageDetails> findUserPackage(String userGuid, String rowGuid) {
        List<PackageDetails> userPackage = packageDetailsDao.findUserPackage(userGuid, rowGuid);
        for (PackageDetails packageDetails : userPackage) {
            if (packageDetails.gettPrice() == null) {
                packageDetails.settPrice(0.00);
            }
            if (packageDetails.gettStatus() == 2) {// 已到期
                //packageDetails.setIsDue(true);// 已到期
                packageDetails.setCanChoose(true);// 可选择
            } else {
                //packageDetails.setIsDue(false);
            }
        }
        return userPackage;
    }

    @Autowired
    private AddressXzDao addressXzDao;

    @Autowired
    private AddressDao addressDao;

    public List<AddressXz> findAddress() {
        return addressXzDao.findAddress();
    }

    public String editAddress(String rowguid, String people_username, String mobile, String address_detail,
                              Long province_dic, Long city_dic, Long area_dic, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        Address address = new Address();
        String id = idWorker.nextId() + "";
        address.setId(id);
        address.setTAddress(address_detail);// 详细地址
        address.setTProvinceDic(province_dic);// 省份编码
        address.setTCityDic(city_dic);// 市编码
        address.setTMobile(mobile);// 收货人手机号
        address.setTUsername(people_username);// 收货人姓名
        address.setTAreaDic(area_dic);// 区县编码
        address.setTUserId(rowguid);// 当前登录用户id
        address.setCreateDate(date);// 保存日期
        addressDao.insert(address);
        return id;
    }

    public AddressDetails findUserAddress(String userId) {
        return addressDao.findUserAddress(userId);
    }

    /**
     * 提交订单
     *
     * @param tCourseGuid       课程id
     * @param tClassPackageGuid 课包id
     * @param pay_type          支付方式
     * @param request
     * @return
     * @throws Exception
     */
    public Map saveOrder(String tCourseGuid, String tClassPackageGuid, String pay_type, HttpServletRequest request) throws Exception {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = dateFormat.format(date);

        // 获取当前登录用户id
        String userId = request.getHeader("token");
        AddressDetails address = addressDao.findUserAddress(userId);

        Order order = new Order();
        String id = idWorker.nextId() + "";
        order.setRowGuid(id);
        order.settUserGuid(userId);
        // 下单日期
        order.settPlaceOrderDate(date);
        // 订单状态 1待支付 2已支付 3已取消
        order.settOrderStatus(1);
        // 订单号
        SimpleDateFormat orderDate = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1 = new Date();
        order.settOrderNo(orderDate.format(date1));
        // 订单编号存入redis
        redisTemplate.opsForValue().set(orderDate.format(date1), orderDate.format(date1));
        order.settCourseGuid(tCourseGuid);
        order.settClassPackageGuid(tClassPackageGuid);// 课包
        order.settAcceptAddress(address.getStateName() + " " + address.getCityName() + " "
                + address.getAreaName() + " " + address.gettAddress());// 收货人地址
        order.settAcceptPeople(address.gettUsername());// 收货人姓名
        order.settAcceptPhone(address.gettMobile());// 收货人手机号
        Course course = courseDao.findById(tCourseGuid);
        Double price = course.gettPrice();
        Integer gettClassTypeId = course.gettClassTypeId();// 班级类型
        if (StringUtils.isEmpty(tClassPackageGuid) && gettClassTypeId != 1) {// 没有课包 短期班&免费班 课程价格
            order.settPrice(price);
        } else if (gettClassTypeId == 1 && StringUtils.isEmpty(tClassPackageGuid)) { // 长期班 没有选择课包 默认所有课包
            String coursePackageId = course.getCoursePackageId();
            String[] classPackageId = coursePackageId.split(",");
            Double cp_price = 0.00;
            String coursePackageGuid = "";// 所有已发布课包
            for (String cpid : classPackageId) {
                CoursePackage coursePackage = coursePackageDao.findById(cpid);
                Integer gettStatus = coursePackage.gettStatus(); // 课包状态 0未发布 1已发布 2已下架 3删除
                if (gettStatus == 1) {
                    coursePackageGuid = coursePackageGuid.concat(coursePackage.getRowGuid() + ",");
                }
            }
            order.settClassPackageGuid(coursePackageGuid);// 所有已发布的课包
            order.settPrice(course.gettPrice());// 课程总价
        } else {// 课包价格
            // 多个课包 "1,2,3"
            String[] classPackageId = tClassPackageGuid.split(",");
            Double cp_price = 0.00;
            for (String cpid : classPackageId) {
                CoursePackage coursePackage = coursePackageDao.findById(cpid);
                Double coursePackagePrice = coursePackage.gettPrice();
                cp_price += coursePackagePrice;
            }
            order.settPrice(cp_price);// 选择课包的价格之和
        }

        if (pay_type.equals("wx")) {
//            int total_price = order.gettPrice().multiply(new BigDecimal(100)).intValue();
            Double total_price = order.gettPrice() * 100;
            int totalPrice = total_price.intValue();
            Map<String, String> param = new HashMap();
            param.put("appid", appid);//公众号
            param.put("mch_id", partner);//商户号
            param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
            param.put("body", "air");//商品描述
            param.put("out_trade_no", order.gettOrderNo());//商户订单号
            param.put("total_fee", Integer.valueOf(totalPrice).toString());//总金额（分）
            param.put("spbill_create_ip", "127.0.0.1");//IP
            // http://a31ef7db.ngrok.io/WeChatPay/WeChatPayNotify
            param.put("notify_url", notifyurl);//回调地址(随便写)
            param.put("trade_type", "APP");//交易类型

            //2.生成要发送的xml
            String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
            System.out.println(xmlParam);
            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(xmlParam);
            httpClient.post();

            String result = httpClient.getContent();
            System.out.println(result);

            Map<String, String> map = WXPayUtil.xmlToMap(result);
            map.put("order_no", order.gettOrderNo());
            map.put("total_fee", order.gettPrice().toString());//总金额（分）
            String prepay_id = map.get("prepay_id");
            order.setPrepayId(prepay_id);// 保存预支付id

            Long d = dateFormat.parse(format, new ParsePosition(0)).getTime() / 1000;
            Map<String, String> paramSign = new HashMap();
            paramSign.put("appid", appid);//公众号
            paramSign.put("partnerid", map.get("mch_id"));//商户号
            paramSign.put("noncestr", map.get("nonce_str"));//随机字符串
            paramSign.put("prepayid", map.get("prepay_id"));//预支付交易会话ID
            paramSign.put("timestamp", d.toString());//时间戳
            paramSign.put("package", "Sign=WXPay");//扩展字段
            String stringA = "appid=" + appid + "&partnerid=" + partner + "&noncestr="
                    + WXPayUtil.generateNonceStr() + "&prepayid=" + map.get("prepay_id")
                    + "&timestamp=" + d.toString() + "&key=" + partnerkey;
            System.out.println("stringA:" + stringA);
            System.out.println("paramSign:" + paramSign);
            String xmlParamSign = WXPayUtil.generateSignedXml(paramSign, partnerkey);// 生成签名
            System.out.println("xmlParamSign:" + xmlParamSign);

            Map<String, String> map_sign = WXPayUtil.xmlToMap(xmlParamSign);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("prepay_id", map.get("prepay_id"));// 预支付id
            paramMap.put("mch_id", map.get("mch_id"));// 商户号
            paramMap.put("order_no", order.gettOrderNo());// 订单号
            paramMap.put("total_fee", order.gettPrice());//总金额（分）
            paramMap.put("nonce_str", map.get("nonce_str"));//随机字符串
            paramMap.put("sign", map_sign.get("sign"));//调起支付接口 签名 随机字符串
            paramMap.put("timestamp", d);//随机字符串

            order.settPayWay("微信");
            appOrderDao.insert(order);
            return paramMap;
        } else if (pay_type.equals("zfb")) {
            //订单名称，必填
            String subject = "课程付款";
            //商品描述，可空
            String body = "云河美术课程报名";
            // 订单金额
            Double orderPrice = order.gettPrice();

            //实例化客户端
            AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, merchantPrivateKey,
                    "json", "UTF-8", alipayPublicKey, "RSA2");
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest aliPayRequest = new AlipayTradeAppPayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody(body);
            model.setSubject(subject);
            model.setOutTradeNo(order.gettOrderNo());
            model.setTimeoutExpress("30m");
            model.setTotalAmount(String.valueOf(orderPrice));
            model.setProductCode("QUICK_MSECURITY_PAY");
            aliPayRequest.setBizModel(model);
            aliPayRequest.setNotifyUrl(notifyUrl);
            try {
                //这里和普通的接口调用不同，使用的是sdkExecute
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(aliPayRequest);

                order.settPayWay("支付宝");
                appOrderDao.insert(order);
                Map map = new HashMap();
                map.put("orderStr", response.getBody());
                map.put("total_fee", order.gettPrice());
                map.put("order_no", order.gettOrderNo());
                map.put("seller_id", AlipayConfig.SELLERID);
                return map;
            } catch (AlipayApiException e) {
                e.printStackTrace();
                throw new Exception("支付失败！");
            }
        } else {
            throw new Exception("支付类型输入错误！");
        }
    }

    /**
     * 已有订单 二次支付订单
     *
     * @return
     */
    public Map secondOrderPay(String out_order_no, HttpServletRequest request) throws Exception {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = dateFormat.format(date);
        // 获取当前登录用户id
        String userId = request.getHeader("token");
        Order orderAllRowguid = myOrderResponseDao.findByOrderNo(out_order_no);
        String orderPayWay = orderAllRowguid.gettPayWay();

        String pay_type = "";
        if (orderPayWay.equals("微信")) {
            pay_type = "wx";
        }
        if (orderPayWay.equals("支付宝")) {
            pay_type = "zfb";
        }

        if (pay_type.equals("wx")) {
            Order order = appOrderDao.findOrder(out_order_no);
            Double total_price = order.gettPrice() * 100;// 转为分
            int totalPrice = total_price.intValue();
            Map<String, String> param = new HashMap();
            param.put("appid", appid);//公众号
            param.put("mch_id", partner);//商户号
            param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
            param.put("body", "air");//商品描述
            param.put("out_trade_no", out_order_no);//商户订单号
            param.put("total_fee", Integer.valueOf(totalPrice).toString());//总金额（分）
            param.put("spbill_create_ip", "127.0.0.1");//IP
            param.put("notify_url", notifyurl);//回调地址(随便写)
            param.put("trade_type", "APP");//交易类型

            //2.生成要发送的xml
            String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
            System.out.println(xmlParam);
            String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
            HttpClient httpClient = new HttpClient(url);
            httpClient.setHttps(true);
            httpClient.setXmlParam(xmlParam);
            httpClient.post();

            String result = httpClient.getContent();
            System.out.println(result);

            Map<String, String> map = WXPayUtil.xmlToMap(result);
            map.put("order_no", order.gettOrderNo());
            map.put("total_fee", order.gettPrice().toString());//总金额（分）

            Long d = dateFormat.parse(format, new ParsePosition(0)).getTime() / 1000;
            Map<String, String> paramSign = new HashMap();
            paramSign.put("appid", appid);//公众号
            paramSign.put("partnerid", map.get("mch_id"));//商户号
            paramSign.put("noncestr", map.get("nonce_str"));//随机字符串
            paramSign.put("prepayid", orderAllRowguid.getPrepayId());//预支付交易会话ID
            paramSign.put("timestamp", d.toString());//时间戳
            paramSign.put("package", "Sign=WXPay");//扩展字段
            String stringA = "appid=" + appid + "&partnerid=" + partner + "&noncestr="
                    + WXPayUtil.generateNonceStr() + "&prepayid=" + map.get("prepay_id")
                    + "&timestamp=" + d.toString() + "&key=" + partnerkey;
            System.out.println("stringA:" + stringA);
            System.out.println("paramSign:" + paramSign);
            String xmlParamSign = WXPayUtil.generateSignedXml(paramSign, partnerkey);// 生成签名
            System.out.println("xmlParamSign:" + xmlParamSign);

            Map<String, String> map_sign = WXPayUtil.xmlToMap(xmlParamSign);

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("prepay_id", orderAllRowguid.getPrepayId());// 预支付id
            paramMap.put("mch_id", map.get("mch_id"));// 商户号
            paramMap.put("order_no", out_order_no);// 订单号
            paramMap.put("total_fee", order.gettPrice());//总金额（分）
            paramMap.put("nonce_str", map.get("nonce_str"));//随机字符串
            paramMap.put("sign", map_sign.get("sign"));//调起支付接口 签名 随机字符串
            paramMap.put("timestamp", d);//随机字符串

            order.settPayWay("微信");// 支付方式
            order.settOrderNo(out_order_no);// 订单号
            appOrderDao.updateByOutOrderNo(order);
            return paramMap;
        } else if (pay_type.equals("zfb")) {
            //订单名称，必填
            String subject = "课程付款";
            //商品描述，可空
            String body = "云河美术课程报名";
            Order order = appOrderDao.findOrder(out_order_no);
            // 订单金额
            Double orderPrice = order.gettPrice();

            //实例化客户端
            AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, merchantPrivateKey, "json", "UTF-8", alipayPublicKey, "RSA2");
            //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
            AlipayTradeAppPayRequest aliPayRequest = new AlipayTradeAppPayRequest();
            //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
            AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
            model.setBody(body);
            model.setSubject(subject);
            model.setOutTradeNo(order.gettOrderNo());
            model.setTimeoutExpress("30m");
            model.setTotalAmount(String.valueOf(orderPrice));
            model.setProductCode("QUICK_MSECURITY_PAY");
            aliPayRequest.setBizModel(model);
            aliPayRequest.setNotifyUrl(notifyUrl);
            try {
                //这里和普通的接口调用不同，使用的是sdkExecute
                AlipayTradeAppPayResponse response = alipayClient.sdkExecute(aliPayRequest);
                order.settPayDate(new Date());
                order.settPayWay("支付宝");
                appOrderDao.updateByOutOrderNo(order);
                Map map = new HashMap();
                map.put("orderStr", response.getBody());
                map.put("total_fee", order.gettPrice());
                map.put("order_no", order.gettOrderNo());
                map.put("seller_id", AlipayConfig.SELLERID);
                return map;
            } catch (AlipayApiException e) {
                e.printStackTrace();
                throw new Exception("支付失败！");
            }
        } else {
            throw new Exception("支付类型输入错误！");
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(AppCourseService.class);

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
                Order order = this.findOrder(out_trade_no);// 根据订单号号查询
                Integer orderStatus = order.gettOrderStatus();
                System.out.println("orderStatus:"+orderStatus);
                //if (orderStatus!=2) {// 订单未支付状态
                    this.updateWxOrderStatus(out_trade_no, order, request);// 修改订单状态
                //}
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

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MyOrderResponseDao myOrderResponseDao;

    /**
     * 我的订单
     *
     * @param rowguid
     * @return
     */
    public Map<String, Object> myOrder(String rowguid) {
        // 根据当前登录用户查询订单信息 包括课包、阶段
        List<UOCCP> userOrderPackage = orderDao.findUserOrderPackage(rowguid);
        // 根据当前登录用户查询订单信息  班型
        List<UOCCP> userOrderClass = orderDao.findUserOrderClass(rowguid);
        Map<String, Object> map = new HashMap<>();
        map.put("userOrderPackage", userOrderPackage);
        map.put("userOrderClass", userOrderClass);
        return map;
    }

    public List<MyOrderResponse> findMyOrder(HttpServletRequest request) {
        String userguid = request.getHeader("token");
        List<MyOrderResponse> orderAll = myOrderResponseDao.findOrderAll(userguid);
        for (MyOrderResponse myOrderResponse : orderAll) {
            String orderRowguid = myOrderResponse.getOrderRowguid();
            MyOrderResponse orderCourseInfo = myOrderResponseDao.findOrderCourseInfo(orderRowguid);
            if (orderCourseInfo==null) {
                myOrderResponse.setOrderCourseName("课程已删除！");
                myOrderResponse.setOrderClassType("课程已删除！");
                myOrderResponse.setOrderCourseType("课程已删除");
                myOrderResponse.setOrderCourseRowguid("课程已删除");
                myOrderResponse.setImgUrl("课程已删除");
                continue;
            }
            myOrderResponse.setOrderCourseName(orderCourseInfo.getOrderCourseName());
            myOrderResponse.setOrderClassType(orderCourseInfo.getOrderClassType());
            myOrderResponse.setOrderCourseType(orderCourseInfo.getOrderCourseType());
            myOrderResponse.setOrderCourseRowguid(orderCourseInfo.getOrderCourseRowguid());
            myOrderResponse.setOrderClassTypeId(orderCourseInfo.getOrderClassTypeId());
            myOrderResponse.setImgUrl(orderCourseInfo.getImgUrl());

            Map<String, Object> map = new HashMap<>();
            List list = new ArrayList();
            if (orderCourseInfo.getOrderClassTypeId() == 1) {
                List<MyOrderResponse> coursePackageInfo = myOrderResponseDao.findCoursePackageInfo(orderRowguid);

                String order_package_rowguid = "";
                for (MyOrderResponse orderResponse : coursePackageInfo) {
                    MyOrderPackageList myOrderPackageList = new MyOrderPackageList();
                    String orderPackageRowguid = orderResponse.getOrderPackageRowguid();
                    order_package_rowguid += orderPackageRowguid + " ";
                    Double orderPackagePrice = orderResponse.getOrderPackagePrice();
                    String orderPackageName = orderResponse.getOrderPackageName();

                    MyOrderResponse coursePackageStageInfo = myOrderResponseDao.findCoursePackageStageInfo(orderPackageRowguid);
                    String orderStageName = coursePackageStageInfo.getOrderStageName();

                    myOrderResponse.setOrderPackageRowguid(order_package_rowguid);
                    map.put(orderStageName, orderResponse.getOrderPackagePrice());

                    myOrderPackageList.setPackagePrice(orderPackagePrice);
                    myOrderPackageList.setPackageRowguid(orderPackageRowguid);
                    myOrderPackageList.setPackageStageName(orderStageName);
                    list.add(myOrderPackageList);
                }
            }
            myOrderResponse.setPackageList(list);
        }
        return orderAll;
    }

    /**
     * 订单详情
     *
     * @param orderNoRowGuid
     * @return
     */
    public MyOrderResponse findOrderDetail(String orderNoRowGuid) {
        // 查询订单信息
        MyOrderResponse orderAllRowguid = myOrderResponseDao.findOrderAllRowguid(orderNoRowGuid);
        MyOrderResponse orderCourseInfo = myOrderResponseDao.findOrderNoCourseInfo(orderNoRowGuid);
        if (orderCourseInfo!=null) {
            orderAllRowguid.setOrderCourseName(orderCourseInfo.getOrderCourseName());
            orderAllRowguid.setImgUrl(orderCourseInfo.getImgUrl());
            orderAllRowguid.setOrderClassType(orderCourseInfo.getOrderClassType());
            orderAllRowguid.setOrderCourseRowguid(orderCourseInfo.getOrderCourseRowguid());
            orderAllRowguid.setOrderCourseType(orderCourseInfo.getOrderCourseType());
            orderAllRowguid.setOrderClassTypeId(orderCourseInfo.getOrderClassTypeId());

            MyOrderResponse courseTimeTable =
                    myOrderResponseDao.isCourseTimeTable(orderCourseInfo.getOrderCourseRowguid(), orderAllRowguid.getOrderUserGuid());
            if (courseTimeTable == null) { // 未加入课表
                orderAllRowguid.setIsTimeTable(false);
            } else {
                orderAllRowguid.setIsTimeTable(true);
            }

            if (orderAllRowguid.getOrderPayWay().equals("微信")) {
                orderAllRowguid.setOrderPayWay("wx");
            } else if (orderAllRowguid.getOrderPayWay().equals("支付宝")) {
                orderAllRowguid.setOrderPayWay("zfb");
            }

            Map<String, Object> map = new HashMap<>();
            List list = new ArrayList();
            String order_package_rowguid = "";
            if (orderCourseInfo.getOrderClassTypeId() == 1) { // 长期班
                List<MyOrderResponse> coursePackageInfo = myOrderResponseDao.findOrderNoCoursePackageInfo(orderNoRowGuid);
                for (MyOrderResponse orderResponse : coursePackageInfo) {
                    MyOrderPackageList myOrderPackageList = new MyOrderPackageList();
                    String orderPackageRowguid = orderResponse.getOrderPackageRowguid();
                    order_package_rowguid += orderPackageRowguid + " ";
                    Double orderPackagePrice = orderResponse.getOrderPackagePrice();
                    String orderPackageName = orderResponse.getOrderPackageName();

                    MyOrderResponse coursePackageStageInfo = myOrderResponseDao.findCoursePackageStageInfo(orderPackageRowguid);
                    String orderStageName = coursePackageStageInfo.getOrderStageName();

                    myOrderPackageList.setPackagePrice(orderPackagePrice);
                    myOrderPackageList.setPackageRowguid(orderPackageRowguid);
                    myOrderPackageList.setPackageStageName(orderStageName);
                    myOrderPackageList.setActivateDate(orderResponse.getActivateDate());
                    myOrderPackageList.setStudyDate(orderResponse.getStudyDate());
                    myOrderPackageList.setPackageName(orderPackageName);

                    list.add(myOrderPackageList);
                }
            }
            orderAllRowguid.setPackageList(list);
        } else {
            orderAllRowguid.setOrderCourseName("课程不存在");
            orderAllRowguid.setImgUrl("课程不存在");
            orderAllRowguid.setOrderClassType("课程不存在");
            orderAllRowguid.setOrderCourseRowguid("课程不存在");
            orderAllRowguid.setOrderCourseType("课程不存在");
        }
        return orderAllRowguid;
    }

    /**
     * 取消订单
     *
     * @param orderNo
     */
    public Map cancelOrder(String orderNo) throws Exception {
        Map<String, String> param = new HashMap();
        param.put("appid", appid);//公众号
        param.put("mch_id", partner);//商户号
        param.put("nonce_str", WXPayUtil.generateNonceStr());//随机字符串
        param.put("out_trade_no", orderNo);//商户订单号

        //2.生成要发送的xml
        String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
        System.out.println(xmlParam);
        String url = "https://api.mch.weixin.qq.com/pay/closeorder";
        HttpClient httpClient = new HttpClient(url);
        httpClient.setHttps(true);
        httpClient.setXmlParam(xmlParam);
        httpClient.post();

        String result = httpClient.getContent();
        System.out.println(result);

        // 修改订单状态 取消订单
        appOrderDao.updateCancelOrderNo(orderNo);
        // 缓存中清除订单号
        redisTemplate.delete(orderNo);

        Map<String, String> map = WXPayUtil.xmlToMap(result);
        return map;
    }

    /**
     * 根据rowguid查询订单
     */
    public Order findOrder(String rowGuid) {
        Order order = appOrderDao.findOrder(rowGuid);
        return order;
    }

    public CourseAll findCourseService(String courseGuid) {
        CourseAll courseService = courseAllDao.findCourseService(courseGuid);
        logger.debug("客服信息：", courseService);
        return courseService;
    }

    @Autowired
    private CourseUserDao courseUserDao;

    @Autowired
    private CoursePackageDao coursePackageDao;

    @Autowired
    private CoursePackageUserDao coursePackageUserDao;

    /**
     * 微信支付 修改订单状态
     *
     * @param t_order_no
     * @param order
     */
    public void updateWxOrderStatus(String t_order_no, Order order, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        Order order1 = appOrderDao.findOrder(t_order_no);
        logger.debug("订单信息service" + order1);
        order1.settOrderStatus(2);// 支付成功 修改订单状态为已支付
        order1.settPayDate(date);// 支付日期

        // 短期班和免费班不做限制 一次报名 永久可用，不需处理
        appOrderDao.updateByRowGuid(order1);
        updateOrderStatus(order1, request);
    }

    /**
     * 支付宝支付 修改订单状态
     *
     * @param rowguid
     * @param order
     * @param request
     */
    public void updateAliOrderStatus(String rowguid, Order order, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        Order order1 = appOrderDao.findOrder(rowguid);
        logger.debug("订单信息" + order1);
        order1.settOrderStatus(2);// 支付成功 修改订单状态为已支付
        order1.settPayWay("支付宝");
        order1.settPayDate(date);// 支付日期
        appOrderDao.updateByRowGuid(order1);

        updateOrderStatus(order1, request);
    }

    /**
     * 修改订单状态
     *
     * @param order1
     * @param request
     */
    public void updateOrderStatus(Order order1, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        String id = idWorker.nextId() + "";
        // 获取当前登录用户id
        String userId = order1.gettUserGuid();//request.getHeader("token");//(String) request.getSession().getAttribute("row_guid");
        logger.debug("当前登录用户userId" + userId);
        CoursePackageUser coursePackageUser = new CoursePackageUser();

        // 判断如果是长期班 计算激活日期 和 计算到期日期
        String courseGuid = order1.gettCourseGuid();
        Course course = courseDao.findById(courseGuid);
        if (course.tClassTypeId == 1) { // 长期班
            // 购买课程后 到激活日期 自动激活 激活日期-到期日期
            String packageGuid = order1.gettClassPackageGuid();
            String[] splitPackGuid = packageGuid.split(",");
            for (String packGuid : splitPackGuid) {
                CoursePackage coursePackage = coursePackageDao.findById(packGuid);
                Integer activateDate = coursePackage.gettActivateDate();// 待激活时长

                // 计算激活日期
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DATE, activateDate);
                Date activateDateTime = cal.getTime();
                System.out.println(activateDateTime);
                coursePackageUser.settActivateTime(activateDateTime);// 激活时间

                // 计算到期日期
                Integer studyDate = coursePackage.gettStudyDate();// 待学习时长
                Calendar calStudyDate = Calendar.getInstance();
                calStudyDate.setTime(activateDateTime);
                calStudyDate.add(Calendar.DATE, studyDate);
                Date studyDateTime = calStudyDate.getTime();// 到期时间
                System.out.println(studyDateTime);

                coursePackageUser.settDueTime(studyDateTime);// 到期时间
                coursePackageUser.setUserGuid(userId);// 用户rowguid
                coursePackageUser.settStatus(3);// 未激活
                coursePackageUser.settPackageGuid(packGuid);// 课包guid
                coursePackageUser.settCourseGuid(order1.gettCourseGuid());// 课程guid
                coursePackageUser.setRowGuid(id);// rowguid
                coursePackageUserDao.insert(coursePackageUser);
            }
        } else {
            // 短期班&免费班
            coursePackageUser.setUserGuid(userId);// 用户rowguid
            coursePackageUser.settStatus(3);// 未激活
            coursePackageUser.settCourseGuid(order1.gettCourseGuid());// 课程guid
            coursePackageUser.setRowGuid(id);// rowguid
            coursePackageUserDao.insert(coursePackageUser);
        }

        // 某用户购买的哪个课程
        CourseUser courseUser = new CourseUser();
        courseUser.settCourseGuid(order1.gettCourseGuid());// 用户购买的哪个课程
        courseUser.settCreateTime(date);
        courseUser.settStatus(3);// 支付成功后为为激活状态 3未激活 2已到期 1正在学
        courseUser.settUserGuid(userId);
        //courseUser.settCoursePackageId(order1.gettClassPackageGuid());// 购买的哪个课包
        courseUserDao.insert(courseUser);
    }

    /**
     * 领取免费课
     *
     * @param request
     */
    public String getFreeCourse(String courseguid, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        String id = idWorker.nextId() + "";
        // 获取当前登录用户id
        String userId = request.getHeader("token");//(String) request.getSession().getAttribute("row_guid");
        logger.debug("当前登录用户userId" + userId);
        CoursePackageUser coursePackageUser = new CoursePackageUser();

        Course course = courseDao.findById(courseguid);

        // 用户领取了哪个免费课程
        coursePackageUser.setUserGuid(userId);// 用户rowguid
        coursePackageUser.settStatus(3);// 未激活
        coursePackageUser.settCourseGuid(courseguid);// 课程guid
        coursePackageUser.setRowGuid(id);// rowguid
        coursePackageUserDao.insert(coursePackageUser);

        // 某用户购买的哪个课程
        CourseUser courseUser = new CourseUser();
        courseUser.settCourseGuid(courseguid);// 用户购买的哪个课程
        courseUser.settCreateTime(date);
        courseUser.settStatus(3);// 支付成功后为为激活状态 3未激活 2已到期 1正在学
        courseUser.settUserGuid(userId);
        //courseUser.settCoursePackageId(order1.gettClassPackageGuid());// 购买的哪个课包
        courseUserDao.insert(courseUser);
        return course.getRowGuid();
    }

    @Autowired
    private StageDao stageDao;

    /**
     * 去上课 选择日期 加入课表
     *
     * @param rowguid
     * @param courseguid
     * @param week
     */
    public void selectDate(String rowguid, String courseguid, String packagguid, Integer week) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        // 选择上课日期
        // 如果是长期班，激活课包，加入课表
        // 如果是免费班或短期班，加入课表
        Course course = courseDao.findById(courseguid);
        Integer gettClassTypeId = course.gettClassTypeId();
        if (gettClassTypeId == 1) { // 长期班
            String stageId = course.getStageId();
            String[] stage_id = stageId.split(",");
            List<CoursePackageUser> userGuid = coursePackageUserDao.findByPackageUserGuid(rowguid, courseguid, packagguid);
            for (CoursePackageUser coursePackageUser : userGuid) {
                if (coursePackageUser.gettStatus() != 0) { // 未激活
                    // 激活
                    coursePackageUser.settStatus(0);// 课包状态 0未开始 1正在学 2已到期 3未激活
                    coursePackageUser.settDueTime(date);// 激活日期
                    coursePackageUser.setUserGuid(rowguid);// 用户id
                    coursePackageUser.settPackageGuid(coursePackageUser.gettPackageGuid());
                    coursePackageUserDao.updateByRowGuid(coursePackageUser);

                    Calendar cal = Calendar.getInstance();
                    int weekI = cal.get(Calendar.DAY_OF_WEEK);
                    SimpleDateFormat Week = new SimpleDateFormat("EEEE");
                    //String weekstr = Week.format(dateFormat);
                    // 日期格式化
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date stime = null;
                    // 选择星期
//                    if (weekI<week) { // 选定星期在今天之后

                    List<Long> weekIndex = TimeUtils.getDateListByWeek(week, 1);
                    for (Long aLong : weekIndex) {
                        for (String sid : stage_id) {
                            Stage stage = stageDao.findById(sid);
                            String classId = stage.gettClassId();
                            if (classId!=null) {
                                // 加入课表
                                Timetable timetable = new Timetable();
                                timetable.setRowGuid(idWorker.nextId() + "");
                                timetable.setUserGuid(rowguid);
                                timetable.settCourseGuid(courseguid);
                                timetable.settPackageGuid(coursePackageUser.gettPackageGuid());
                                String[] cid = classId.split(",");
                                for (String s : cid) {
                                    // 保存课节id
                                    timetable.settClassId(s);
                                    System.out.println(new DateTime(aLong).toString("yyyy-MM-dd HH:mm:ss"));
                                    stime = new Date(aLong);
                                    dateFormat.format(stime);
                                    timetable.settCreateDate(stime);
                                    timetable.settWeeks(week);

//                                SimpleDateFormat w = new SimpleDateFormat("EEEE");
//                                String format = w.format(calendar.getCalendarType());
                                    // 保存到课表
                                    timetableDao.insert(timetable);

                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(stime);
                                    calendar.add(Calendar.DATE, 7);
                                    stime = calendar.getTime();
                                    String st = dateFormat.format(stime);
                                    // 转时间戳
                                    aLong = dateFormat.parse(st, new ParsePosition(0)).getTime();
//                            stime = new Date(aLong);
//                            dateFormat.format(stime);
                                    String format = Week.format(stime);
                                    switch (format) {
                                        case "星期一":
                                            week = 1;
                                            break;
                                        case "星期二":
                                            week = 2;
                                            break;
                                        case "星期三":
                                            week = 3;
                                            break;
                                        case "星期四":
                                            week = 4;
                                            break;
                                        case "星期五":
                                            week = 5;
                                            break;
                                        case "星期六":
                                            week = 6;
                                            break;
                                        case "星期日":
                                            week = 7;
                                            break;
                                    }
                                    System.out.println(format);
                                    weekIndex.set(0, aLong);
                                }
                            }
                        }
                    }
                }
            }
        } else { // 免费班 and 短期班
            // 学习时间
            //timetable.settCreateDate(date);
            SimpleDateFormat Week = new SimpleDateFormat("EEEE");
            // 日期格式化
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date stime = null;
            // 选择星期
            List<Long> weekIndex = TimeUtils.getDateListByWeek(week, 1);
            for (Long aLong : weekIndex) {
                String classId = course.getClassId();
                if (classId!=null) {
                    String[] cid = classId.split(",");

                    // 加入课表
                    Timetable timetable = new Timetable();
                    timetable.setRowGuid(idWorker.nextId() + "");
                    timetable.setUserGuid(rowguid);
                    timetable.settCourseGuid(courseguid);

                    for (String s : cid) {
                        stime = new Date(aLong);
                        dateFormat.format(stime);
                        // 保存课节id
                        timetable.settClassId(s);
                        timetable.settCreateDate(stime);
                        timetable.settWeeks(week);
                        timetableDao.insert(timetable);

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(stime);
                        calendar.add(Calendar.DATE, 7);
                        stime = calendar.getTime();
                        String st = dateFormat.format(stime);
                        // 转时间戳
                        aLong = dateFormat.parse(st, new ParsePosition(0)).getTime();
//                            stime = new Date(aLong);
//                            dateFormat.format(stime);
                        String format = Week.format(stime);
                        switch (format) {
                            case "星期一":
                                week = 1;
                                break;
                            case "星期二":
                                week = 2;
                                break;
                            case "星期三":
                                week = 3;
                                break;
                            case "星期四":
                                week = 4;
                                break;
                            case "星期五":
                                week = 5;
                                break;
                            case "星期六":
                                week = 6;
                                break;
                            case "星期日":
                                week = 7;
                                break;
                        }
                        System.out.println(format);
                        weekIndex.set(0, aLong);
                    }
                }

            }
        }

        /*if (gettClassTypeId == 0) {  // 免费班
            // 某用户购买的哪个课程
            CourseUser courseUser = new CourseUser();
            courseUser.settCourseGuid(courseguid);// 用户购买的哪个课程
            courseUser.settCreateTime(date);
            courseUser.settStatus(3);// 支付成功后为为激活状态 3未激活 2已到期 1正在学
            courseUser.settUserGuid(rowguid);
            courseUserDao.insert(courseUser);

            CoursePackageUser coursePackUser = new CoursePackageUser();
            coursePackUser.setRowGuid(idWorker.nextId() + "");
            coursePackUser.settCourseGuid(courseguid);// 用户购买的哪个课程
            coursePackUser.setUserGuid(rowguid);
            coursePackageUserDao.insert(coursePackUser);
        }*/

    }

    @Autowired
    private UserLinkStatusDao userLinkStatusDao;

    /**
     * 进入环节学习看课
     *
     * @param rowguid
     */
    public void studyVideo(String rowguid, HttpServletRequest request) {
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        String userId = request.getHeader("token");

        Link linkId = linkDao.findById(rowguid);

        // 上课学习状态 0未开始 1已开始
        UserLinkStatus userLinkStatus = new UserLinkStatus();
        userLinkStatus.setLinkGuid(rowguid);
        userLinkStatus.setUserGuid(userId);
        userLinkStatus.setClassGuid(linkId.gettClassId());
        userLinkStatus.setStudyStatus(1);
        userLinkStatusDao.insert(userLinkStatus);

        Link link = linkDao.findById(rowguid);
        link.setStudyStatus(1);
        link.setStudyTime(date);
        linkDao.updateByRowguid(link);
    }

    @Autowired
    private TimetableDao timetableDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${appid}")
    private String appid;
    @Value("${partner}")
    private String partner;
    @Value("${partnerkey}")
    private String partnerkey;
    @Value("${notifyurl}")
    private String notifyurl;

    public Map queryPayStatus(String out_trade_no) {
        //1.封装参数
        Map param = new HashMap();
        param.put("appid", appid);
        param.put("mch_id", partner);
        param.put("out_trade_no", out_trade_no);
        param.put("nonce_str", WXPayUtil.generateNonceStr());
        try {
            String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
            //2.发送请求
            HttpClient httpClient = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
            httpClient.setHttps(true);
            httpClient.setXmlParam(xmlParam);
            httpClient.post();

            //3.获取结果
            String xmlResult = httpClient.getContent();
            Map<String, String> map = WXPayUtil.xmlToMap(xmlResult);
            System.out.println("调动查询API返回结果：" + xmlResult);

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Autowired
    private TimetableDetailsDao timetableDetailsDao;

    /**
     * 根据当前登录用户查询课表
     *
     * @param rowguid
     * @return
     */
    public List<TimetableDetailsResponse> findTimetableUser(String rowguid, int page, int size) {
        PageHelper.startPage(page, size);
        List<TimetableDetailsResponse> timetableUser = timetableDetailsDao.findTimetableUser(rowguid);
        return timetableUser;
    }

    public List<ClassLinkVo> findClassLink(String rowguid, HttpServletRequest request) throws ClientException {
        String userId = request.getHeader("token");
        List<ClassLinkVo> classLink = timetableDetailsDao.findClassLink(rowguid);

        for (ClassLinkVo classLinkVo : classLink) {

            // -------------------------获取视频播放地址-------------------------------
            DefaultAcsClient client = VideoUtil.initVodClient("LTAI4FgFCe1fEaUBL7fNSJnj",
                    "IsvdGXmFh3Vwi3338b7mWchDqAt749");
            GetPlayInfoResponse response = new GetPlayInfoResponse();
            String playURL = "";
            try {
                response = VideoUtil.getPlayInfo(client, classLinkVo.getVideoId());
                List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();
                //播放地址
                for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                    System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
                    playURL = playInfo.getPlayURL();
                }
                //Base信息
                System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
                classLinkVo.setVideoUrl(playURL);
            } catch (Exception e) {
                System.out.print("ErrorMessage = " + e.getLocalizedMessage());
            }
            System.out.print("RequestId = " + response.getRequestId() + "\n");
            // -----------------------------------------------------------------------

            if (classLinkVo.gettLinkName().equals("作品")) {
                classLinkVo.setType(3);
            } else if (classLinkVo.gettLinkName().equals("预习")) {
                classLinkVo.setType(1);
            } else if (classLinkVo.gettLinkName().equals("上课")) {
                classLinkVo.setType(2);
            }
            // 环节学习状态
            UserLinkStatus linkStudyStatus = timetableDetailsDao.findLinkStudyStatus(userId, classLinkVo.getLinkRowGuid());
            if (linkStudyStatus == null) {
                classLinkVo.setStudyStatus(0);// 未开始学习
            } else {
                classLinkVo.setStudyStatus(1);// 已学习
            }
        }
        // timetableDetailsDao.updateStudyStatus(rowguid);
        return classLink;
    }

    @Autowired
    private LinkDao linkDao;

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

    @Autowired
    private WorksDao worksDao;

    /**
     * 根据课节rowguid上传作品
     *
     * @return
     */
    public List<Link> uploadWork(String classguid, String courseguid, String linkguid,
                                 String url, HttpServletRequest request) {
        String userguid = request.getHeader("token");
        // 日期格式化 当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        Works link = new Works();

        // linkguid='1239881190442291200'
        // 课节rowguid-> 阶段表 查阶段id-> 课程表 环节表的rowguid
        // 课节rowguid-> 课程表 环节表的rowguid
        int i = 0;
        Link byAll = linkDao.findById(linkguid);
        String gettClassId = byAll.gettClassId();

        List<Stage> stageAll = stageDao.findStageAll();
        for (Stage stage : stageAll) {
            String classId = stage.gettClassId();
            if (classId!=null) {
                String[] split = classId.split(",");
                for (String cid : split) {
                    if (cid.equals(gettClassId)) {
                        i = 1;
                        // 得到阶段id
                        String stageRowGuid = stage.getRowGuid();
                        // 根据阶段id获取课程id
                        List<Course> all = courseDao.findAll();
                        for (Course course : all) {
                            String stageId = course.getStageId();
                            if (stageId!=null) {
                                String[] split_stage_id = stageId.split(",");
                                for (String sta : split_stage_id) {
                                    if (sta.equals(stageRowGuid)) {
                                        courseguid = course.getRowGuid(); // 得到课程id
                                        link.settCourseGuid(courseguid);// 课程rowguid
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        if (i==0) {
            // 根据阶段id获取课程id
            List<Course> all = courseDao.findAll();
            for (Course course : all) {
                String classId = course.getClassId();
                if (classId!=null) {
                    String[] split_class_id = classId.split(",");
                    for (String sta : split_class_id) {
                        if (sta.equals(gettClassId)) {
                            courseguid = course.getRowGuid(); // 得到课程id
                            link.settCourseGuid(courseguid);// 课程rowguid
                            break;
                        }
                    }
                }
            }
        }

        // 日期格式化 当前日期
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Date d = new Date();
        String format = df.format(d);
        // 可以上传多个作品 图片
        // 图片地址
        String userId = (String) request.getSession().getAttribute("row_guid");

        link.settImgUrl("http://airffter.oss-cn-beijing.aliyuncs.com/" + url);// 图片地址
        link.setRowGuid(idWorker.nextId() + "");
        link.settWorksName("work_" + format);// 作品名称
        link.settWorksNum(format);// 作品编号
        link.settStatus(1);
        link.settWorksType("课程作品");
        link.settUploadDate(date);// 上传日期
        link.setLinkGuid(linkguid);// 环节roguid
        link.settClassGuid(gettClassId);// 课节rowguid

        link.settUserGuid(userguid);// 上传的用户rowguid
        worksDao.insert(link);

        return null;
    }

    @Autowired
    private TypeDao typeDao;

    public List<Type> findAll() {
        return typeDao.findType();
    }

    /**
     * 我的课程
     *
     * @param rowguid
     * @return
     */
    public List<CourseAll> myCourse(String rowguid, Integer type) {
        List<CourseAll> myCourse = courseAllDao.findMyCourse(rowguid, type);
        for (CourseAll courseAll : myCourse) {
            Integer buyCourseCount = courseAllDao.findBuyCourseCount(courseAll.getRowGuid());
            Integer classCount = 0;
            if (courseAll.gettClassTypeId() == 1) {
                classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
            } else {
                classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
            }
            //Integer classCount = courseAllDao.findClassCount(courseAll.getRowGuid());
            Integer stageCount = courseAllDao.findStageCount(courseAll.getRowGuid());
            if (courseAll.getCourseCount() == null) {
                courseAll.setCourseCount(0);
            }
            if (courseAll.gettPrice() == null) {
                courseAll.settPrice(0.00);
            }
            if (courseAll.gettScore() == null) {
                courseAll.settScore(0);
            }
            if (buyCourseCount == null) {
                buyCourseCount = 0;
                courseAll.setBuyCount(buyCourseCount);
            } else {
                courseAll.setBuyCount(buyCourseCount);
            }
            if (courseAll.gettLearnCount() == null) {
                courseAll.settLearnCount(0);
            }
            if (courseAll.getBuyCount() == null) {
                courseAll.setBuyCount(0);
            }
            if (courseAll.getClassCount() == null) {
                courseAll.setClassCount(0);
            }
            if (classCount == null) {
                classCount = 0;
                courseAll.setClassCount(classCount);
            } else {
                courseAll.setClassCount(classCount);
            }
            if (stageCount == null) {
                stageCount = 0;
                courseAll.setStageCount(stageCount);
            } else {
                courseAll.setStageCount(stageCount);
            }
            CourseAll activate = courseAllDao.isActivate(courseAll.getPackageGuid(), courseAll.gettUserGuid());
            if (activate != null) { // 是长期班有课包
                if (activate.gettStatus() == 3) { // 课包状态 0未开始 1正在学 2已到期 3未激活
                    courseAll.setIsActivate(false);
                } else {
                    courseAll.setIsActivate(true);
                }
            }
        }
        return myCourse;
    }

    public List<CourseAll> search(String courseName) {
        List<CourseAll> search = courseAllDao.search(courseName);
        for (CourseAll courseAll : search) {
            Integer classCount = 0;
            if (courseAll.gettClassTypeId() == 1) {
                classCount = stageClassDao.findClassTotalCount(courseAll.getRowGuid());
                courseAll.setClassCount(classCount);
                //courseAllDao.findClassCount(courseAll.getRowGuid());
            } else {
                classCount = classDedailsDao.findClassTotalCount(courseAll.getRowGuid());
                courseAll.setClassCount(classCount);
            }
            // 多少人购买该课程
            Integer buyCountCourse = this.findBuyCourseCount(courseAll.getRowGuid());
            // 查询课节数
            //Integer classCount = appCourseService.findClassCount(course.getRowGuid());
            if (buyCountCourse == null) {
                buyCountCourse = 0;
                courseAll.setBuyCount(buyCountCourse);
            } else {
                courseAll.setBuyCount(buyCountCourse);
            }
            if (courseAll.gettPrice() == null) {
                courseAll.settPrice(0.00);
            }
            if (courseAll.gettLearnCount() == null) {
                courseAll.settLearnCount(0);
            }
            if (courseAll.getBuyCount() == null) {
                courseAll.setBuyCount(0);
            }
            if (courseAll.gettScore() == null) {
                courseAll.settScore(0);
            }
        }
        return search;
    }

    // 修改用户的阶段
    public void updateUserLevel(String rowguid, Integer stageId) {
        courseDao.updateUserLevel(rowguid, stageId);
    }

    public Integer findBuyCourseCount(String rowGuid) {
        Integer buyCourseCount = courseAllDao.findBuyCourseCount(rowGuid);
        if (buyCourseCount == null) {
            buyCourseCount = 0;
        }
        return buyCourseCount;
    }

    public Integer findClassCount(String rowGuid) {
        Integer classCount = courseAllDao.findClassCount(rowGuid);
        if (classCount == null) {
            classCount = 0;
        }
        return classCount;
    }

    public int findStageCount(String rowGuid) {
        Integer stageCount = courseAllDao.findStageCount(rowGuid);
        if (stageCount == null) {
            stageCount = 0;
        }
        return stageCount;
    }

    public List<TimetableDetailsResponse> findTimetableBAUser(String rowguid, int page, int size, int condition, HttpServletRequest request) {
        String userId = request.getHeader("token");
        if (condition == 1) { // 当前时间之前
            PageHelper.startPage(page, size);
            List<TimetableDetailsResponse> timetableUser = timetableDetailsDao.findTimetableBeforeUser(rowguid);
            for (TimetableDetailsResponse timetableDetailsResponse : timetableUser) {
                UserLinkStatus userLinkStatus = timetableDetailsDao.findUserLinkStatus(userId, timetableDetailsResponse.getClassRowGuid());
                if (userLinkStatus == null) { // 未开始学习
                    timetableDetailsResponse.setStudyStatus(0);
                } else {
                    timetableDetailsResponse.setStudyStatus(1);
                }
            }
            return timetableUser;
        } else { // 当前时间之后
            PageHelper.startPage(page, size);
            List<TimetableDetailsResponse> timetableUser = timetableDetailsDao.findTimetableAfterUser(rowguid);
            for (TimetableDetailsResponse timetableDetailsResponse : timetableUser) {
                UserLinkStatus userLinkStatus = timetableDetailsDao.findUserLinkStatus(userId, timetableDetailsResponse.getClassRowGuid());
                if (userLinkStatus == null) { // 未开始学习
                    timetableDetailsResponse.setStudyStatus(0);
                } else {
                    timetableDetailsResponse.setStudyStatus(1);
                }
            }
            return timetableUser;
        }
    }

    @Autowired
    private MyWorkDao myWorkDao;

    public List<WorkResponse> findMyWork(String courseguid, HttpServletRequest request) {
        String rowguid = request.getHeader("token");
        List<WorkResponse> myWork = myWorkDao.findMyWork(rowguid, courseguid);
        for (WorkResponse workResponse : myWork) {
            if (workResponse.gettContent() == null) {
                workResponse.setIsEvaluation(false);
            } else {
                workResponse.setIsEvaluation(true);
            }
        }
        return myWork;
    }

    public List<WorkResponse> findMyClassWork(String classguid, HttpServletRequest request) {
        String rowguid = request.getHeader("token");
        List<WorkResponse> myClassWork = myWorkDao.findMyClassWork(rowguid, classguid);
        for (WorkResponse workResponse : myClassWork) {
            if (workResponse.gettContent() == null) {
                workResponse.setIsEvaluation(false);
            } else {
                workResponse.setIsEvaluation(true);
            }
        }
        return myClassWork;
    }

    public WorkResponse findWorkDetail(String rowguid) {
        WorkResponse workDetail = myWorkDao.findWorkDetail(rowguid);
        if (workDetail == null) {
            workDetail = myWorkDao.findWorkNoHaveContentDetail(rowguid);
            workDetail.setIsEvaluation(false);
        } else if (workDetail.gettContent() == null) {
            workDetail.setIsEvaluation(false);
        } else {
            workDetail.setIsEvaluation(true);
        }

        return workDetail;
    }

    public List<MyWorkResponse> findMyWorkAll(HttpServletRequest request) {
        String useguid = request.getHeader("token");
        List<MyWorkResponse> workResponse = myWorkDao.findMyWorkAll(useguid);
        for (MyWorkResponse response : workResponse) {
            if (response.gettContent() != null) { // 有评价
                response.setIsEvaluation(true);
            } else {
                response.setIsEvaluation(false);
            }
        }

        return workResponse;
    }

    public List<CoursePackageResponse> findMyCoursePackage(String courseguid, HttpServletRequest request) {
        String userguid = request.getHeader("token");
        // 查询某课程下所有已发布的课包
        List<CoursePackageResponse> myCoursePackage = myWorkDao.findMyCoursePackageAll(courseguid);
        for (CoursePackageResponse coursePackageResponse : myCoursePackage) {
            // 得到课包rowguid 查询 课包状态、到期时间信息 - 查询课包所包含的阶段
            String packageRowguid = coursePackageResponse.getPackageRowguid();
            CoursePackageResponse coursePackage = myWorkDao.findMyCoursePackage(userguid, packageRowguid);
            if (coursePackage == null) { // 说明课包未购买
                coursePackageResponse.setIsBuy(false);
                coursePackageResponse.setCpuStatus(4);
            } else {
                coursePackageResponse.setIsBuy(true);
                coursePackageResponse.setCpuStatus(coursePackage.getCpuStatus());
                coursePackageResponse.setCpuRowguid(coursePackage.getCpuRowguid());
                coursePackageResponse.setPackageRowguid(coursePackage.getPackageRowguid());
                coursePackageResponse.settDueTime(coursePackage.gettDueTime());// 到期时间
            }
            // 查询课包所包含的阶段
            CoursePackageResponse packageStage = myWorkDao.findPackageStage(packageRowguid);
            coursePackageResponse.setStageName(packageStage.getStageName());
            int count = 0;
            List<CoursePackageResponse> stageRowguid = myWorkDao.findStageRowguid(packageRowguid);
            for (CoursePackageResponse packageResponse : stageRowguid) {
                // 阶段的课节数
                Integer classCount = myWorkDao.classCount(packageResponse.getStageRowguid());
                if (classCount==null){
                    classCount=0;
                } else {
                    count += classCount;
                }
            }
            coursePackageResponse.setClassCount(count);
        }
        return myCoursePackage;
    }

    @Autowired
    private TwoCodeDao twoCodeDao;

    public TwoCode twoCode(String rowguid) {
        return twoCodeDao.findImgUrl(rowguid);
    }

    public ClassImageResponse findClassImage(String rowguid) {
        return twoCodeDao.findClassImage(rowguid);
    }

    public AddressDetails findByIdAddress(String addressId) {
        return addressDao.findByIdAddress(addressId);
    }

    public CoursePackageResponse findCoursePackageById(String packageguid) {
        CoursePackageResponse myCoursePackageStage = myWorkDao.findMyCoursePackageStage(packageguid);
        return myCoursePackageStage;
    }

}
