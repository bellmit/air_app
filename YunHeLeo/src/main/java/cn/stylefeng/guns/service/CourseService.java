package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojo.*;
import cn.stylefeng.guns.pojos.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CourseService {
    List<CourseType> courseList();
    String creatClassType(Course course, String userId, HttpServletRequest request);
    CourseTypesResponse findTypesId(String rowGuid);
    void editCourseInfo(String RowGuid, Course course);
    void saveCourseInfo(String RowGuid, Course course);
    String addStage(Stage stage, HttpServletRequest request);
    void updateCourseStage(String RowGuid, String stageId);
    void releaseLongCourse(String RowGuid);
    void takenCourse(String RowGuid);
    void deleteCourse(String RowGuid);
    void orderNoCourse(String RowGuid, Integer orderNo);
    String addClass(TClass tClass, String userId);
    void updateStageClass(String stageId, String classId);
    public void updateCourseClass(String courseRowGuid, String id);
    void updateStage(String RowGuid, Stage stage);
    Stage findIdStage(String id);
    Map<String,Object> findByClass(String rowGuid);
    void editClass(String rowGuid, TClass tClass);
   // List<StageClass> findStage(int page, int size, String RowGuid);
    List<ClassDedailsResponse> findFreeClass(String rowGuid);
    List<ClassDedailsResponse> findShortClass(String rowGuid);
    // 创建课包
    String addCoursePackage(CoursePackage coursePackage);
    void updateCourse(String rowGuid, String cpId);
    List<Stage> findAll(String RowGuid);
    Map findCPAll(String rowGuid, int page, int size);
    Course findById(String RowGuid);
    void releaseShortCourse(String rowGuid);
    void updateShortPrice(String rowGuid, Double price);
    List<CourseAll> findCourseAll(int page, int size, String query, Integer classTypeId, Integer courseTypeId, Integer tStatus);
    void downCoursePackage(String rowGuid);
    void editCoursePackage(String rowGuid, CoursePackage coursePackage);
    void deleteCoursePackage(String rowGuid);
    CourseAll buyCourseCount(String tCourseGuid);
    Integer courseCount();
    public Integer packageCount(String rowGuid);
    void releaseCoursePackage(String rowGuid);

    void classAddImage(String rowGuid, String userId, String imgurl);

   // List<ClassDedailsResponse> findByRowGuidClass(String rowGuid);

    void downClass(String rowGuid);

    void upClass(String rowGuid);

    void isTestClass(String rowGuid, Boolean isTest);
}
