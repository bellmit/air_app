package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojo.Works;
import cn.stylefeng.guns.pojos.WorkUC;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WorkUCService {
    List<WorkUC> findAll(int page, int size, String query);
    int count();
    WorkUC findById(Integer id);
    void save(String userId, Integer id, Works works);
}
