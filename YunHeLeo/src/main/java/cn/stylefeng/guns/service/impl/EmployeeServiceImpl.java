package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.config.AliyunConfig;
import cn.stylefeng.guns.dao.EmployeeDao;
import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.entity.PicUploadResult;
import cn.stylefeng.guns.pojo.Employee;

import cn.stylefeng.guns.pojo.Log;
import  cn.stylefeng.guns.service.EmployeeService;
import cn.stylefeng.guns.utils.IdWorker;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author leon
 * @since 2020-02-26
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Employee> teacher() {
        return employeeDao.teacher();
    }

    @Override
    public List<Employee> service() {
        return employeeDao.service();
    }

    @Override
    public void saveUsername(String userId, Employee employee, HttpServletRequest request) {
        employee.setId(idWorker.nextId()+"");
        employee.settMobile(employee.gettMobile());
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        employee.settCreatedate(date);
        employee.settIsService(employee.gettIsService());
        employee.settIsTeacher(employee.gettIsTeacher());
        // 密码加密
        String password = new Md5Hash(employee.gettPassword(), employee.gettUsername(), 3).toString();
        employee.settPassword(password);
        // 用户名
        employee.settUsername(employee.gettUsername());
        // 真实姓名
        employee.settRealyname(employee.gettRealyname());
        employee.settStatus(1);// 状态 1正常 2禁用
        // 角色
        employee.setRoleId(employee.getRoleId());
        // String url = (String) request.getSession().getAttribute("url");
        employee.settImgUrl(employee.gettImgUrl());
        employeeDao.insert(employee);

        // 获取当前登录用户id
        // String userId = (String) request.getSession().getAttribute("id");
        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("新增员工");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee findByUsername(String username) {

        return employeeDao.findByUsername(username);
    }

    @Override
    public Page<Employee> findEmpList(int page, int size, String query) {
        PageHelper.startPage(page,size);
        Employee employee = new Employee();
        employee.settUsername(query);
        employee.settRealyname(query);
        employee.settMobile(query);
        Page<Employee> empList = employeeDao.findEmpList(employee);
        long total = empList.getTotal();
        List<Employee> result = empList.getResult();
        return empList;
    }

    @Override
    public Employee findById(String id) {
        return employeeDao.findById(id);
    }

    @Override
    public void updateById(String id, String userId, Employee emp, HttpServletRequest request) {
        try {
            Employee employee = findById(id);
            // 用户名
            employee.settUsername(emp.gettUsername());
            // 密码加密
            //String password = new Md5Hash(emp.gettPassword(), emp.gettUsername(), 3).toString();
            //employee.settPassword(password);
            // 手机号
            employee.settMobile(emp.gettMobile());
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);
            employee.settUpdatedate(date);

            employee.settIsService(emp.gettIsService());
            employee.settIsTeacher(emp.gettIsTeacher());

            // 用户名
            employee.settUsername(emp.gettUsername());
            // 真实姓名
            employee.settRealyname(emp.gettRealyname());
            // 状态 1正常 2禁用
            employee.settStatus(emp.gettStatus());
            // 角色
            employee.setRoleId(emp.getRoleId());
            // String url = (String) request.getSession().getAttribute("url");
            employee.settImgUrl(emp.gettImgUrl());
            employeeDao.updateById(employee);

            // 获取当前登录用户id
            // String userId = (String) request.getSession().getAttribute("id");
            try {
                // 操作日志
                Log log = new Log();
                log.setId(idWorker.nextId()+"");
                log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
                log.settOperatorDate(date);
                log.settOperatorMan(userId);
                log.settOperatorRecord("编辑员工");
                logDao.insertLog(log);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(String ids, HttpServletRequest request) {
        employeeDao.deleteById(ids);

        // 获取当前登录用户id
        String userId = (String) request.getSession().getAttribute("id");
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("删除员工");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStatus(int status, String id, HttpServletRequest request) {
        employeeDao.updateStatus(status,id);
        // 获取当前登录用户id
        String userId = (String) request.getSession().getAttribute("id");
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("修改员工状态");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> findByUsernameOrMobileOrRn(Employee employee,int page,int size) {
        PageHelper.startPage(page,size);
        return employeeDao.findByUsernameOrMobileOrRn(employee);
    }

    @Override
    public List<Employee> findByStatus(Integer status, int page,int size) {
        PageHelper.startPage(page,size);
        return employeeDao.findByStatus(status);
    }

    @Override
    public int countEmp() {
        return employeeDao.countEmp();
    }

    @Override
    public void updateEmpPwd(String newPwd, String userId) {
        employeeDao.updateEmpPwd(newPwd,userId);
    }

    @Override
    public void updateLoginUserMobile(String mobile, String userId) {
        employeeDao.updateLoginUserMobile(mobile,userId);
    }

    // 允许上传的格式
    private static final String[] IMAGE_TYPE = new String[]{".bmp", ".jpg",
            ".jpeg", ".gif", ".png"};

    @Autowired
    private OSSClient ossClient;
    @Autowired
    private AliyunConfig aliyunConfig;

    /**
     * 文件上传
     */
    public PicUploadResult upload(MultipartFile uploadFile,HttpServletRequest request) {
        // 封装Result对象，并且将文件的byte数组放置到result对象中
        PicUploadResult fileUploadResult = new PicUploadResult();

        // 校验图片格式
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(uploadFile.getOriginalFilename(), type)) {
                isLegal = true;
                break;
            }
        }

        if(!isLegal){
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
        // 文件新路径
        String fileName = uploadFile.getOriginalFilename();
        String filePath = getFilePath(fileName);
        // 上传到阿里云
        try {
            // 目录结构： images/年/月/日/xxx.jpg
            PutObjectResult putObjectResult = ossClient.putObject(aliyunConfig.getBucketName(), filePath, new ByteArrayInputStream(uploadFile.getBytes()));
            // 上传成功
            fileUploadResult.setStatus("done");
            // 上传图片全路径
            String url = this.aliyunConfig.getUrlPrefix() + filePath;
            request.getSession().setAttribute("url",url);
            fileUploadResult.setUrl(url);
            fileUploadResult.setUid(String.valueOf(System.currentTimeMillis()));
            return fileUploadResult;
        } catch (Exception e) {
            e.printStackTrace();
            //上传失败
            fileUploadResult.setStatus("error");
            return fileUploadResult;
        }
    }

    private String getFilePath(String sourceFileName) {
        DateTime dateTime = new DateTime();
        return "images/" + dateTime.toString("yyyy")
                + "/" + dateTime.toString("MM") + "/"
                + dateTime.toString("dd") + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(100, 9999) + "." +
                StringUtils.substringAfterLast(sourceFileName, ".");
    }

}
