package cn.stylefeng.guns.service;

import cn.stylefeng.guns.entity.PicUploadResult;
import cn.stylefeng.guns.pojo.Employee;
import com.github.pagehelper.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leon
 * @since 2020-02-26
 */
public interface EmployeeService {
    void saveUsername(String userId, Employee employee, HttpServletRequest request);
    Employee findByUsername(String username);
    Page<Employee> findEmpList(int page, int size, String query);
    Employee findById(String id);
    public void updateById(String id, String userId, Employee emp, HttpServletRequest request);
    void deleteById(String ids, HttpServletRequest request);
    void updateStatus(int status, String id, HttpServletRequest request);
    List<Employee> findByUsernameOrMobileOrRn(Employee employee, int page, int size);
    List<Employee> findByStatus(Integer status, int page, int size);
    int countEmp();
    void updateEmpPwd(String newPwd, String userId);
    void updateLoginUserMobile(String mobile, String userId);
    public PicUploadResult upload(MultipartFile uploadFile, HttpServletRequest request);
    List<Employee> teacher();
    List<Employee> service();
}
