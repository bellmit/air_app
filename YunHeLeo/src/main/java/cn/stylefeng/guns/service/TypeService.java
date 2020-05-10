package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojo.Type;
import com.github.pagehelper.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface TypeService {
    int countType();
    Page<Type> findAll(int page, int size, String query);
    void addType(String userId, Type type, HttpServletRequest request);
    void delete(String userId, String id, HttpServletRequest request);
    List<Type> findType();
    Type findById(String rowGuid);
    void updateType(String rowGuid, String userId, Type type, HttpServletRequest request);
    Type findByCourseTypeId(String id);
}
