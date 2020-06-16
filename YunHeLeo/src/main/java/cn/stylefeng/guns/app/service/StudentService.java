package cn.stylefeng.guns.app.service;

import cn.stylefeng.guns.config.GlobalConstant;
import cn.stylefeng.guns.config.WeChatConfig;
import cn.stylefeng.guns.dao.StudentDao;
import cn.stylefeng.guns.dao.UserCodeDao;
import cn.stylefeng.guns.dao.UserResquestDao;
import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.pojo.Level;
import cn.stylefeng.guns.pojo.User;
import cn.stylefeng.guns.pojo.UserCode;
import cn.stylefeng.guns.pojo.UserResquest;
import cn.stylefeng.guns.utils.HttpUtils;
import cn.stylefeng.guns.utils.IdWorker;

import com.github.pagehelper.util.StringUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;

@Service
public class StudentService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private UserCodeDao userCodeDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserResquestDao userResquestDao;

    @Autowired
    private IdWorker idWorker;


    public String sendThirdLoginSms(String mobile, String unionId, String type) {
        mobile = mobile.replaceAll(" ", "");
        // 主键id
        String id = idWorker.nextId() + "";
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        // 生成随机数
        String code = RandomStringUtils.randomNumeric(4);

        // 存入验证码表
        UserCode userCode = new UserCode();
        userCode.setRowGuid(id);
        userCode.settCode(code);
        userCode.settCreatetime(date);
        userCodeDao.insert(userCode);

        User use = studentDao.findByMobile(mobile);
        if ( use == null ) {
            // 保存用户手机号 状态为2 待完善注册
            User user = new User();
            user.setRowGuid(id);
            user.settMobile(mobile);
            user.settStatus(2);
            studentDao.insert(user);
        }

        //存入redis手机号为key，验证码为value
        redisTemplate.opsForValue().set("third_login " + mobile, code + " " + unionId + " " + type, 60, TimeUnit.SECONDS);
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code);
        //给用户发一份
        rabbitTemplate.convertAndSend("sms_reg", map);

        return code;
    }

    public String sendSms(String mobile) {
        // 主键id
        String id = idWorker.nextId() + "";
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        // 生成随机数
        String code = RandomStringUtils.randomNumeric(4);

        // 存入验证码表
        UserCode userCode = new UserCode();
        userCode.setRowGuid(id);
        userCode.settCode(code);
        userCode.settCreatetime(date);
        userCodeDao.insert(userCode);

        User use = studentDao.findByMobile(mobile);
        if ( use == null ) {
            // 保存用户手机号 状态为2 待完善注册
            User user = new User();
            user.setRowGuid(id);
            user.settMobile(mobile);
            user.settStatus(2);
            studentDao.insert(user);
        }

        //存入redis手机号为key，验证码为value
        redisTemplate.opsForValue().set(mobile, code, 60, TimeUnit.SECONDS);
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code);
        //给用户发一份
        rabbitTemplate.convertAndSend("sms_reg", map);

        //显示在控制台
        System.out.println("验证码：" + code);

        logger.debug("验证码：", code);
        logger.debug("mobile：", mobile);
        return code;
    }

    public String sendForgetSms(String mobile) {
        // 主键id
        String id = idWorker.nextId() + "";
        // 日期格式化
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);

        // 生成随机数
        String code = RandomStringUtils.randomNumeric(4);

        // 存入验证码表
        UserCode userCode = new UserCode();
        userCode.setRowGuid(id);
        userCode.settCode(code);
        userCode.settCreatetime(date);
        userCodeDao.insert(userCode);

        User use = studentDao.findByMobile(mobile);
        if ( use == null ) {
            // 保存用户手机号 状态为2 待完善注册
            User user = new User();
            user.setRowGuid(id);
            user.settMobile(mobile);
            user.settStatus(2);
            studentDao.insert(user);
        }

        //存入redis手机号为key，验证码为value
        redisTemplate.opsForValue().set(mobile, code, 60, TimeUnit.SECONDS);
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("code", code);
        //给用户发一份
        rabbitTemplate.convertAndSend("forget_sms", map);

        //显示在控制台
        System.out.println("验证码：" + code);
        return code;
    }

    /**
     * 完善注册信息
     *
     * @param user
     */
    public void register(UserResquest user, String mobile) {
        try {
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);

            logger.debug("date系统生成日期：", date);
            logger.debug("完善注册信息——前端传递的用户生日日期：", user.gettChildrenDate());

            UserResquest use = userResquestDao.findByMobile(mobile);
            use.settStatus(0);
            use.settSex(user.gettSex());
            use.settAge(user.gettAge());
            // "http://airffter.oss-cn-beijing.aliyuncs.com/"+
            use.settHeadImg(user.gettHeadImg());
            use.settUsername(user.gettUsername());
            use.setStageId(user.getStageId());// 学习阶段
            use.setShowStageId(user.getStageId());// 首页显示学习阶段
            use.settPassword(user.gettPassword());
            use.settStudentNo(user.gettStudentNo());
            use.settRegisterIp(InetAddress.getLocalHost().getHostAddress());
            use.settRegisterDate(date);
            use.settChildrenDate(user.gettChildrenDate());
            use.setWx_unionid(user.getWx_unionid());
            use.setQq_unionid(user.getQq_unionid());
            int count = studentDao.findUserWorksCount(user.getRowGuid());
            user.setWorkCount(count);
            userResquestDao.updateById(use);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void updateWxUnicode(String mobile,String unionid){
        UserResquest use = userResquestDao.findByMobile(mobile);
        use.settStatus(0);
        use.setWx_unionid(unionid);
        userResquestDao.updateById(use);
    }

    public void updateQQUnicode(String mobile,String unionid){
        UserResquest use = userResquestDao.findByMobile(mobile);
        use.settStatus(0);
        use.setQq_unionid(unionid);
        userResquestDao.updateById(use);
    }


    /**
     * 修改用户信息
     *
     * @param user
     * @param mobile
     */
    public void updateUserInfo(UserResquest user, String mobile) {
        try {
            // 日期格式化
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            dateFormat.format(date);

            logger.debug("headimg:"+user.gettHeadImg());

            logger.debug("date系统生成日期：", date);
            logger.debug("service：修改用户信息——前端传递的用户生日日期：", user.gettChildrenDate());

            UserResquest use = userResquestDao.findByMobile(mobile);
            use.settStatus(0);
            use.settSex(user.gettSex());
            use.settAge(user.gettAge());
            //"http://airffter.oss-cn-beijing.aliyuncs.com/"+
            if ( !StringUtil.isEmpty(user.gettHeadImg()) ) {
                if ( user.gettHeadImg().startsWith("http") ) {
                    use.settHeadImg(user.gettHeadImg().replaceFirst(GlobalConstant.OSS_PREFIX, ""));
                } else {
                    use.settHeadImg(user.gettHeadImg());
                }
            } else {
                use.settHeadImg("");
            }
            //use.settHeadImg(user.gettHeadImg());
            use.settUsername(user.gettUsername());
            use.setStageId(user.getStageId());// 学习阶段
            use.setShowStageId(user.getStageId());// 首页显示学习阶段
            use.settStudentNo(user.gettStudentNo());
            use.settChildrenDate(user.gettChildrenDate());
            int count = studentDao.findUserWorksCount(user.getRowGuid());
            user.setWorkCount(count);
            userResquestDao.updateById(use);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void updateShowStageId(Integer showStageId, String rowguid) {
        userResquestDao.updateShowStageId(showStageId, rowguid);
    }

    /**
     * 根据手机号查询
     */
    public User findByMobile(String mobile) {
        User user = studentDao.findByMobile(mobile);
        int count = studentDao.findUserWorksCount(user.getRowGuid());
        user.setWorkCount(count);
        if ( !StringUtil.isEmpty(user.gettHeadImg()) ) {
            if ( user.gettHeadImg().startsWith("http") ) {
                user.settHeadImg(user.gettHeadImg().replaceFirst(GlobalConstant.OSS_PREFIX, ""));
            } else {
                user.settHeadImg(GlobalConstant.OSS_PREFIX + user.gettHeadImg());
            }
        } else {
            user.settHeadImg("");
        }
        System.out.println(user.gettMobile());
        return user;
    }

    /**
     * 查询学习阶段
     */
    public List<Level> getStudyLevel() {
        return studentDao.getStudyLevel();
    }

    /**
     * 修改密码
     *
     * @param mobile
     * @param password
     */
    public void forgetPwd(String mobile, String password) {
        User user = studentDao.findByMobile(mobile);
        user.settPassword(password);
        studentDao.updatePwd(user);
    }

    /**
     * 检查wxUnionid是否绑定的有用户
     *
     * @param wxUnionid
     * @return
     */
    public String isWxUnionidBinded(String wxUnionid) {
        return studentDao.findUserByWxUnionid(wxUnionid);
    }

    public String isQQUnionidBinded(String wxUnionid) {
        return studentDao.findUserByQQUnionid(wxUnionid);
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    public User userInfo(String rowguid, HttpServletRequest request) {
        // 获取当前登录用户id
        String userId = ( String ) request.getSession().getAttribute("row_guid");
        User user = studentDao.userInfo(rowguid);

        String img = user.gettHeadImg();
        if ( !img.startsWith("http") ) {
            String imgUrl = GlobalConstant.OSS_PREFIX + img;
            user.settHeadImg(imgUrl);
        }

        //处理生日
        String birthday = user.gettChildrenDate();
        if ( !StringUtil.isEmpty(birthday) ) {
            DateTimeFormatter yyyyMMddHHmmss = DateTimeFormat.forPattern(GlobalConstant.TIME_FORMAT);
            DateTime dateTime = DateTime.parse(birthday, yyyyMMddHHmmss);
            long millisSecond = dateTime.getMillis() / 1000;
            user.settChildrenDate(millisSecond + "");
        }

        int count = studentDao.findUserWorksCount(rowguid);
        user.setWorkCount(count);
//        Map<String,Object> map = new HashMap<>();
//        map.put("userInfo",user);
//        map.put("count", count);
        return user;
    }

    @Autowired
    private WeChatConfig weChatConfig;

    public User findByGuid(String guid) {
        User byGuid = studentDao.findByGuid(guid);
        String headImg = byGuid.gettHeadImg();
        byGuid.settHeadImg("http://airffter.oss-cn-beijing.aliyuncs.com/"+headImg);
        if (byGuid.getWorkCount()==null)
            byGuid.setWorkCount(0);
        return byGuid;
    }
}
