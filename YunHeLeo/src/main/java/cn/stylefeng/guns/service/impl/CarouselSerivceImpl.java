package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.CarouselDao;
import cn.stylefeng.guns.dao.CarouselImgDao;
import cn.stylefeng.guns.dao.CourseDao;
import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.pojo.Course;
import cn.stylefeng.guns.pojo.Img;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojos.EmpImg;
import cn.stylefeng.guns.service.CarouselSerivce;
import cn.stylefeng.guns.utils.IdWorker;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CarouselSerivceImpl implements CarouselSerivce {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CarouselDao empImgDao;

    @Autowired
    private CarouselImgDao carouselImgDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public EmpImg findById(String id) {
        return empImgDao.findById(id);
    }

    @Override
    public Page<EmpImg> findAll(int page, int size,String query) {
        PageHelper.startPage(page, size);
        EmpImg empImg = new EmpImg();
        empImg.settName(query);
        Page<EmpImg> list = empImgDao.findAll(empImg);
        return list;
    }

    @Override
    public int countImg() {
        return empImgDao.countImg();
    }

    @Override
    public void addCarouse(String userId, Img img, HttpServletRequest request) {
        String token = request.getHeader("token");
        String uid = (String) request.getSession().getAttribute("id");
        if (userId==null) {
            String id = idWorker.nextId() + "";
            img.setId(id);
            img.setRowGuid(id);
            img.settUploadMan(uid);// 上传人
            img.settImgType(img.gettImgType());

            Course num = courseDao.findByNum(img.getLinkId());
            if (num!=null) {
                // 链接id
                img.setLinkId(num.getRowGuid());
                // 链接类型
                img.settLinkType(img.gettLinkType());
            } else {
                // 链接id
                img.setLinkId("");
                // 链接类型
                img.settLinkType(img.gettLinkType());
            }
            img.settStatus(1);// 1生效 2停止
            img.settImgUrl(img.gettImgUrl());// 图片url
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);
            img.settUploadDate(date);
            img.settOrderNo(img.gettOrderNo());// 排序号
            carouselImgDao.insert(img);

            try {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(uid);
                log.settOperatorRecord("保存轮播图");
                logDao.insertLog(log);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } else {
            String id = idWorker.nextId() + "";
            img.setId(id);
            img.setRowGuid(id);
            img.settUploadMan(userId);// 上传人
            img.settImgType(img.gettImgType());
            // 根据课程编号id得到课程rowguid
            Course num = courseDao.findByNum(img.getLinkId());
            if (num!=null) {
                // 链接id
                img.setLinkId(num.getRowGuid());
                // 链接类型
                img.settLinkType(img.gettLinkType());
            } else {
                // 链接id
                img.setLinkId("");
                // 链接类型
                img.settLinkType(img.gettLinkType());
            }
            img.settStatus(1);// 1生效 2停止
            img.settImgUrl(img.gettImgUrl());// 图片url
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);
            img.settUploadDate(date);
            img.settOrderNo(img.gettOrderNo());// 排序号
            carouselImgDao.insert(img);

            try {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("保存轮播图");
                logDao.insertLog(log);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(String id, String userId, HttpServletRequest request) {
        carouselImgDao.deleteById(id);

        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        String uid = (String) request.getSession().getAttribute("id");
        try {
            if (userId==null) {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(uid);
                log.settOperatorRecord("删除轮播图");
                logDao.insertLog(log);
            } else {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId() + "");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("删除轮播图");
                logDao.insertLog(log);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCarouse(String userId, String id, Img img, HttpServletRequest request) {
        String token = request.getHeader("token");
        String uid = (String) request.getSession().getAttribute("id");
        if (userId!=null) {
            Img empImg = carouselImgDao.findById(id);
            empImg.settName(img.gettName());
            Course num = courseDao.findByNum(img.getLinkId());
            if (num!=null) {
                // 链接id
                empImg.setLinkId(num.getRowGuid());
                // 链接类型
                empImg.settImgType(img.gettImgType());
            } else {
                // 链接id
                empImg.setLinkId("");
                // 链接类型
                empImg.settImgType(img.gettImgType());
            }
            empImg.settUploadMan(userId);// 上传人
            empImg.settImgUrl(img.gettImgUrl());// 图片url
            empImg.settLinkType(img.gettLinkType());
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);
            empImg.settUploadDate(date);
            empImg.settOrderNo(img.gettOrderNo());// 排序号
            carouselImgDao.updateId(empImg);
        } else {
            Img empImg = carouselImgDao.findById(id);
            empImg.settLinkType(img.gettLinkType());
            empImg.settName(img.gettName());
            Course num = courseDao.findByNum(img.getLinkId());
            if (num!=null) {
                // 链接id
                empImg.setLinkId(num.getRowGuid());
                // 链接类型
                empImg.settImgType(img.gettImgType());
            } else {
                // 链接id
                empImg.setLinkId("");
                // 链接类型
                empImg.settImgType(img.gettImgType());
            }
            empImg.settUploadMan(uid);// 上传人
            empImg.settImgUrl(img.gettImgUrl());// 图片url
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);
            empImg.settUploadDate(date);
            empImg.settOrderNo(img.gettOrderNo());// 排序号
            carouselImgDao.updateId(empImg);
        }
    }

}
