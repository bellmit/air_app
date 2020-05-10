package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojo.Message;
import cn.stylefeng.guns.pojos.EmpMess;
import com.github.pagehelper.Page;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MessageService {
    Page<EmpMess> findAll(int page, int size, String query);
    int count();
    void addMessage(String userId, Message message, HttpServletRequest request);
    EmpMess findById(String id);
    void deleteByIdMessage(String userId, String id);
}
