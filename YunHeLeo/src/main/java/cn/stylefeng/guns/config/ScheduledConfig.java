package cn.stylefeng.guns.config;

import cn.stylefeng.guns.app.service.AppCourseService;
import cn.stylefeng.guns.dao.*;
import cn.stylefeng.guns.pojo.*;
import cn.stylefeng.guns.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.*;

@Configuration
public class ScheduledConfig {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CoursePackageUserDao coursePackageUserDao;

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private StageDao stageDao;

    @Autowired
    private TimetableDao timetableDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private AppOrderDao appOrderDao;

    @Autowired
    private AppCourseService appCourseService;

    /**
     * 订单超时 取消订单
     */
    public void updateOrderStatus(String t_order_no) throws Exception {
        Order order1 = appOrderDao.findOrder(t_order_no);
        order1.settOrderStatus(3);// 支付成功 修改订单状态为已取消

        // 短期班和免费班不做限制 一次报名 永久可用，不需处理
        appOrderDao.updateByRowGuid(order1);// 修改订单状态
        appCourseService.cancelOrder(t_order_no);// 取消订单
    }

    /**
     * 未支付订单 30分钟后 自动取消订单
     */
    @Scheduled(cron = "0/20 * * * * *")
    public void payOrder() throws Exception {
        Date date = new Date();// 当前时间
        // 查询所有订单
        List<Order> orderList = appOrderDao.findOrderList();
        for (Order order : orderList) {
            Date placeOrderDate = order.gettPlaceOrderDate();// 创建订单日期
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(placeOrderDate);// 下单时间开始
            calendar.set(GregorianCalendar.MINUTE,30); // 30min后
            Date time = calendar.getTime();// 得到30分后时间
            if (order.gettOrderStatus()!=2) { // 未支付订单
                if (time.after(placeOrderDate)) {
                    this.updateOrderStatus(order.gettOrderNo());
                }
            }
        }
    }

    /**
     * 定时检查  激活课程
     * 如果课程到待激活日期没有被激活将被自动激活
     */
    @Scheduled( cron = "0/10 * * * * *" ) // 在任务开始后60秒执行下一次调度
    public void activateCourse() {
        // 日期格式化 当前日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        // 获取当前登录用户id
        String rowGuid = ( String ) redisTemplate.opsForValue().get("row_guid");
        String userId = rowGuid;//(String) request.getSession().getAttribute("row_guid");
        if ( userId == null ) {
            System.out.println("当前用户未登录");
        } else {
            // 当前日期 >= 课包激活日期时 自动激活 改变状态
            List<CoursePackageUser> all = coursePackageUserDao.findAll();
            for ( CoursePackageUser coursePackageUser : all ) {
                Date activateTime = coursePackageUser.gettActivateTime();
                if ( coursePackageUser.gettStatus() == 3 ) { // 课包未激活
                    if ( date.after(activateTime) ) {
                        CoursePackageUser packageUsers = coursePackageUserDao.findById(coursePackageUser.getRowGuid());
                        packageUsers.settStatus(0);// 已激活课程 未开始学习
                        coursePackageUserDao.updateByRowGuid(packageUsers);

                        Course course = courseDao.findById(packageUsers.gettCourseGuid());
                        String stageId = course.getStageId();
                        String[] stage_id = stageId.split(",");

                        // 已激活的课包加入我的课程表
                        Timetable timetable = new Timetable();
                        SimpleDateFormat Week = new SimpleDateFormat("EEEE");
                        // 日期格式化
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date stime = new Date();
                        df.format(stime);
                        for ( String sid : stage_id ) {
                            Stage stage = stageDao.findById(sid);
                            String classId = stage.gettClassId();
                            String[] cid = classId.split(",");
                            for ( String s : cid ) {
                                // 保存课节id
                                timetable.settClassId(s);
                                timetable.setRowGuid(idWorker.nextId() + "");
                                timetable.setUserGuid(userId);
                                timetable.settCourseGuid(packageUsers.gettCourseGuid());
                                timetable.settPackageGuid(packageUsers.gettPackageGuid());
                                timetable.settCreateDate(stime);
                                String format = Week.format(stime);// 得到星期
                                switch ( format ) {
                                    case "星期一":
                                        timetable.settWeeks(1);
                                        break;
                                    case "星期二":
                                        timetable.settWeeks(2);
                                        break;
                                    case "星期三":
                                        timetable.settWeeks(3);
                                        break;
                                    case "星期四":
                                        timetable.settWeeks(4);
                                        break;
                                    case "星期五":
                                        timetable.settWeeks(5);
                                        break;
                                    case "星期六":
                                        timetable.settWeeks(6);
                                        break;
                                    case "星期日":
                                        timetable.settWeeks(7);
                                        break;
                                }
                                // 保存到课表
                                timetableDao.insert(timetable);

                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(stime);
                                calendar.add(Calendar.DATE, 7);// 日期加7天
                                stime = calendar.getTime();
                                String st = dateFormat.format(stime);
                                // 转时间戳
                                System.out.println(format);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("111");
    }
}
