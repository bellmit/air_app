package cn.stylefeng.guns.service;

import cn.stylefeng.guns.pojo.Img;
import cn.stylefeng.guns.pojos.EmpImg;
import com.github.pagehelper.Page;

import javax.servlet.http.HttpServletRequest;

public interface CarouselSerivce {
    EmpImg findById(String id);
    Page<EmpImg> findAll(int page, int size, String query);
    int countImg();
    void addCarouse(String userId, Img img, HttpServletRequest request);
    public void deleteById(String id, String userId, HttpServletRequest request);
    void updateCarouse(String userId, String id, Img img, HttpServletRequest request);
}
