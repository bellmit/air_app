package cn.stylefeng.guns.service.impl;

import cn.stylefeng.guns.dao.LogDao;
import cn.stylefeng.guns.dao.RoleDao;
import cn.stylefeng.guns.dao.RolePerDao;
import cn.stylefeng.guns.pojo.Log;
import cn.stylefeng.guns.pojo.Role;
import cn.stylefeng.guns.pojo.RolePer;
import cn.stylefeng.guns.pojos.RolePermissionNode;
import  cn.stylefeng.guns.service.RoleService;
import cn.stylefeng.guns.utils.IdWorker;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
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
 * @author 
 * @since 2020-02-27
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RolePerDao rolePerDao;

    @Autowired
    private LogDao logDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public List<Role> roleList(int page, int size, String query) {
        PageHelper.startPage(page, size);
        Role role = new Role();
        role.settRoleName(query);
        return roleDao.roleList(role);
    }

    @Override
    public void saveRole(String userId, Role role, HttpServletRequest request) {
        String id = idWorker.nextId()+"";
        role.setRoleId(id);
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        role.settCreateDate(date);

        role.settStatus(1);// 状态 1启用 2停用
        role.settRoleDescription(role.gettRoleDescription());
        role.settRoleName(role.gettRoleName());
        roleDao.insert(role);

        // 分配权限
        // 如果没有分配权限，不保存
        // 分配权限 角色id和获取的权限id保存到角色权限中间表中
        // 3,3,4,5,6,6
        String tids = role.gettIds();
        String[] tidsSplit = tids.split(",");
        RolePer rolePer = new RolePer();
        if (!tids.equals("")) {
            for (String tid : tidsSplit) {
                rolePer.setRoleId(id);
                rolePer.setPerId(Integer.parseInt(tid));
                rolePerDao.insert(rolePer);
            }
        }

        // 获取当前登录用户id
        // String userId = (String) request.getSession().getAttribute("id");
        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("保存角色");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RolePermissionNode> findByRoleId(String roleId) {
        return roleDao.findByRoleId(roleId);
    }

    @Override
    public void updateRole(String roleId, Role role, HttpServletRequest request) {
        // 将要修改的角色名称和角色描述
        roleDao.updateRole(role.gettRoleName(), role.gettRoleDescription(), roleId);

        // 取消某权限  根据角色id和权限id 中间表记录删除
        rolePerDao.deleteByRoleId(roleId);

        // 权限id “3,4,5,6,7,8"
        // 赋予某权限  新增中间表记录
        String tids = role.gettIds();
        String[] tidsSplit = tids.split(",");
        RolePer rolePer = new RolePer();
        if (!tids.equals("")) {
            for (String tid : tidsSplit) {
                rolePer.setRoleId(roleId);
                rolePer.setPerId(Integer.parseInt(tid));
                rolePerDao.insert(rolePer);
            }
        }

        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        // 获取当前登录用户id
        String userId = (String) request.getSession().getAttribute("id");
        try {
            // 操作日志
            Log log = new Log();
            log.setId(idWorker.nextId()+"");
            log.settIpAddress(Inet4Address.getLocalHost().getHostAddress());
            log.settOperatorDate(date);
            log.settOperatorMan(userId);
            log.settOperatorRecord("编辑角色");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int countRole() {
        return roleDao.countRole();
    }

    @Override
    public List<Role> findRoleName(String tRoleName) {
        return roleDao.findRoleName(tRoleName);
    }

    @Override
    public void deleteRole(String userId, String roleId, HttpServletRequest request) {
        // 根据id删除角色信息
        roleDao.deleteRole(roleId);
        // 根据角色id删除中间表权限信息
        rolePerDao.deleteByRoleId(roleId);

        // 获取当前登录用户id
        // String userId = (String) request.getSession().getAttribute("id");
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
            log.settOperatorRecord("删除角色");
            logDao.insertLog(log);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

}
