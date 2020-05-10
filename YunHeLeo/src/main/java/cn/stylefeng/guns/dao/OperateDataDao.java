package cn.stylefeng.guns.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface OperateDataDao {

    // 今日登录用户数
    @Select("SELECT COUNT(*) FROM (SELECT recordguid FROM tb_login_log tll WHERE date_format(tll.t_login_date,'%Y%m%d')= date_format(now(),'%Y%m%d')) tllnow")
    int loginTodayUserCount();

    // 今日新增用户
    @Select("SELECT COUNT(*) FROM (SELECT * FROM tb_user tll WHERE date_format(tll.t_register_date,'%Y%m%d')= date_format(now(),'%Y%m%d')) tllnow;")
    int addTodayUserCount();

    // 现有用户总数
    @Select("select count(*) from tb_user")
    int countUser();

    // 今日新增购课数
    @Select("SELECT COUNT(*) FROM (SELECT * FROM tb_order tll WHERE date_format(tll.t_pay_date,'%Y%m%d')= date_format(now(),'%Y%m%d') and tll.t_order_status=2) tllnow;")
    int buyTodayClass();

    // 今日新增作品数
    @Select("SELECT COUNT(*) FROM (SELECT * FROM tb_works tll " +
            "WHERE date_format(tll.t_upload_date,'%Y%m%d')= date_format(now(),'%Y%m%d')) tllnow;")
    int addTodayWorks();

    // 近七日购课数
    @Select("SELECT\n" +
            "\tDATE_FORMAT(o.t_pay_date, '%y-%m-%d') date,\n" +
            "\tcount(*) count\n" +
            "FROM\n" +
            "\ttb_order o\n" +
            "WHERE\n" +
            "\to.t_order_status = 2\n" +
            "AND DATE_SUB(CURDATE(),INTERVAL 7 DAY)<=DATE(o.t_pay_date) \n" +
            "GROUP BY\n" +
            "\tDATE_FORMAT(o.t_pay_date, '%y-%m-%d')")
    List<Map<String,String>> buyClass();

    // 近七日新增用户数
    @Select("SELECT\n" +
            "\tDATE_FORMAT(o.t_register_date, '%y-%m-%d') date,\n" +
            "\tcount(*) count\n" +
            "FROM\n" +
            "\ttb_user o\n" +
            "WHERE\n" +
            "  DATE_SUB(CURDATE(),INTERVAL 7 DAY)<=DATE(o.t_register_date) \n" +
            "GROUP BY\n" +
            "\tDATE_FORMAT(o.t_register_date, '%y-%m-%d')")
    List<Map<String,String>> addUser();

}
