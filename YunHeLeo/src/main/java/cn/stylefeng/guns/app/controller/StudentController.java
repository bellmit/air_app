package cn.stylefeng.guns.app.controller;

import cn.stylefeng.guns.app.service.StudentService;
import cn.stylefeng.guns.config.WeChatConfig;
import cn.stylefeng.guns.entity.JsonData;
import cn.stylefeng.guns.entity.Result;
import cn.stylefeng.guns.entity.ResultStatusCode;
import cn.stylefeng.guns.pojo.Level;
import cn.stylefeng.guns.pojo.User;
import cn.stylefeng.guns.pojo.UserCode;
import cn.stylefeng.guns.pojo.UserResquest;
import cn.stylefeng.guns.utils.HttpUtils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mysql.cj.util.StringUtils;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping( "/app/api/user" )
@CrossOrigin
@Api( value = "app用户注册&登录", description = "app用户注册&登录" )
//@JsonInclude( value = JsonInclude.Include.NON_NULL )
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Logger dataLogger = LoggerFactory.getLogger("dataLogger");

    @Autowired
    private StudentService studentService;

    @Autowired
    private WeChatConfig weChatConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping( "/registerCode" )
    @ResponseBody
    @ApiOperation( "注册发送短信验证码" )
    public Result registerCode(String phone, HttpServletRequest request) {
        try {
            String code = studentService.sendSms(phone);
            return new Result(true, 0, "发送验证码成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "发送验证码失败!");
        }
    }

    @PostMapping( "/thirdLoginCode" )
    @ResponseBody
    @ApiOperation( "第三方发送短信验证码" )
    public Result thirdLoginCode(@RequestParam( value = "unionid", defaultValue = "" ) String unionid,
                                 @RequestParam( value = "phone", defaultValue = "" ) String phone,
                                 @RequestParam( value = "type", defaultValue = "" ) String type) {
        try {
            if ( "wx".equals(type) || "qq".equals(type) ) {
                //微信登录 qq 登录
                studentService.sendThirdLoginSms(phone, unionid, type);
            } else {
                return new Result(false, ResultStatusCode.FAIL, "type类型不对!");
            }
            return new Result(true, 0, "发送验证码成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "发送验证码失败!");
        }
    }

    @PostMapping( "/forgetCode" )
    @ResponseBody
    @ApiOperation( "修改密码发送短信验证码" )
    public Result forgetCode(String phone, HttpServletRequest request) {
        try {
            String code = studentService.sendForgetSms(phone);
            User use = studentService.findByMobile(phone);
            Map map = new HashMap();
            map.put("rowguid", use.getRowGuid());
            return new Result(true, 0, "发送验证码成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "发送验证码失败!");
        }
    }

    @PostMapping( "/thirdLoginCheckCode" )
    @ResponseBody
    @ApiOperation( "校验第三方登录验证码" )
    public Result thirdLoginCheckCode(@RequestParam( value = "phone", defaultValue = "" ) String phone,
                                      @RequestParam( value = "code", defaultValue = "" ) String code,
                                      @RequestParam( value = "type", defaultValue = "" ) String type) {
        try {
            if ( "wx".equals(type) || "qq".equals(type) ) {
                //微信登录 qq 登录
                String codeRedis = ( String ) redisTemplate.opsForValue().get("third_login " + phone);
                if ( !StringUtils.isNullOrEmpty(codeRedis) ) {
                    //有值
                    String tempCode = codeRedis.split(" ")[0];
                    String unionId = codeRedis.split(" ")[1];
                    String tempType = codeRedis.split(" ")[2];
                    if ( !tempType.equals(type) ) {
                        return Result.FAIL("验证码不正确");
                    }
                    if ( !tempCode.equals(code) ) {
                        return Result.FAIL("验证码不正确");
                    }
                    //验证码验证通过
                    User use = studentService.findByMobile(phone);
                    if ( use != null && use.gettStatus() == 0 ) {
                        //已经有并且是正常用户

                        if ( type.equals("wx") ) {
                            studentService.updateWxUnicode(phone, unionId);
                        } else if ( type.equals("qq") ) {
                            studentService.updateQQUnicode(phone, unionId);
                        }

                        Map map = new HashMap();
                        map.put("rowguid", use.getRowGuid());
                        map.put("unionId", "");
                        map.put("needRegist", false);
                        return new Result(true, 0, "登录成功!", map);
                    } else {
                        Map map = new HashMap();
                        map.put("rowguid", use.getRowGuid());
                        map.put("unionId", unionId);
                        map.put("needRegist", true);
                        return new Result(true, 0, "验证码验证成功!", map);
                    }
                }
            } else {
                return new Result(false, ResultStatusCode.FAIL, "type类型不对!");
            }
            return new Result(true, 0, "验证码验证成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "验证码验证失败！");
        }
    }

    @GetMapping( "/check_code" )
    @ResponseBody
    @ApiOperation( "校验验证码" )
    public Result checkCode(String code, String mobile, HttpServletRequest request) {
        try {
            User use = studentService.findByMobile(mobile);
            String codeRedis = ( String ) redisTemplate.opsForValue().get(mobile);
            if ( codeRedis == null )
                return new Result(false, ResultStatusCode.FAIL, "请先获取短信验证码!");
            if ( !codeRedis.equals(code) )
                return new Result(false, ResultStatusCode.FAIL, "验证码输入错误!");
            else {
                Map map = new HashMap();
                map.put("rowguid", use.getRowGuid());
                return new Result(true, 0, "验证码验证通过!", map);
            }
        } catch ( Exception e ) {
            return new Result(false, ResultStatusCode.FAIL, "验证码验证失败！");
        }
    }

    @GetMapping( "/check_pwd_code" )
    @ResponseBody
    @ApiOperation( "校验验证码 修改密码" )
    public Result checkPwdCode(String code, String mobile, HttpServletRequest request) {
        try {
            User use = studentService.findByMobile(mobile);
            if ( use != null ) {
                String codeRedis = ( String ) redisTemplate.opsForValue().get(mobile);
                if ( codeRedis == null )
                    return new Result(false, ResultStatusCode.FAIL, "请先获取短信验证码!");
                if ( !codeRedis.equals(code) )
                    return new Result(false, ResultStatusCode.FAIL, "验证码输入错误!");
                else {
                    Map map = new HashMap();
                    map.put("rowguid", use.getRowGuid());
                    return new Result(true, 0, "验证码验证通过!", map);
                }
            } else {
                return new Result(false, ResultStatusCode.FAIL, "手机号不存在！");
            }
        } catch ( Exception e ) {
            return new Result(false, ResultStatusCode.FAIL, "验证码验证失败或手机号不存在！");
        }
    }

    @GetMapping( "/checkCodeLogin" )
    @ResponseBody
    @ApiOperation( "校验验证码|验证码登录" )
    public Result checkCodeLogin(String code, String mobile, HttpServletRequest request) {
        try {
            User use = studentService.findByMobile(mobile);

            // jwt
            JwtBuilder builder = Jwts.builder();
            builder.setIssuer("air");// 颁发者
            builder.setIssuedAt(new Date());
            builder.setSubject("jwt登录");
            builder.signWith(SignatureAlgorithm.HS256, "airedu");
            String jwt = builder.compact();
            request.getSession().setAttribute("row_guid", use.getRowGuid());
            Map map = new HashMap();
            map.put("rowguid", use.getRowGuid());
            String codeRedis = ( String ) redisTemplate.opsForValue().get(mobile);
            if ( codeRedis == null )
                return new Result(false, ResultStatusCode.FAIL, "请先获取短信验证码!");
            if ( !codeRedis.equals(code) )
                return new Result(false, ResultStatusCode.FAIL, "验证码输入错误!");
            else {
                if ( use.gettStatus() == 2 ) { // 未注册
                    return new Result(true, 0, "验证码验证通过 用户未注册!", use);
                } else if ( use.gettStatus() == 0 ) { // 已注册 可以登录
                    request.getSession().setAttribute("row_guid", use.getRowGuid());
                    request.getSession().setAttribute("mobile", use.gettMobile());
                    redisTemplate.opsForValue().set("row_guid", use.getRowGuid(), 366, TimeUnit.DAYS);
                    redisTemplate.opsForValue().set("use", use, 366, TimeUnit.DAYS);
                    return new Result(true, 1, "验证码验证通过 登录成功!", use);
                } else {
                    return new Result(true, 2, "验证码验证通过 用户已禁用!", map);
                }
                //return new Result(true, 0, "验证码验证通过!", use);
            }
        } catch ( Exception e ) {
            return new Result(false, ResultStatusCode.FAIL, "验证码验证或登录失败！");
        }
    }

    /*@GetMapping("/codeLogin")
    @ResponseBody
    @ApiOperation("验证码登录")
    public Result codeLogin(String code,String mobile,HttpServletRequest request) {
        try {
            User use = studentService.findByMobile(mobile);
            // jwt
            JwtBuilder builder = Jwts.builder();
            builder.setIssuer("air");// 颁发者
            builder.setIssuedAt(new Date());
            builder.setSubject("jwt登录");
            builder.signWith(SignatureAlgorithm.HS256, "airedu");
            String jwt = builder.compact();
            request.getSession().setAttribute("row_guid", use.getRowGuid());
            Map map = new HashMap();
            map.put("jwt",jwt);
            map.put("userInfo",use.getRowGuid());
            if (use.gettStatus() == 2) { // 未注册
                return new Result(true, 0, "验证码验证通过 用户未注册!", use);
            } else if (use.gettStatus() == 0) { // 已注册 可以登录
                request.getSession().setAttribute("row_guid", use.getRowGuid());
                redisTemplate.opsForValue().set("row_guid",use.getRowGuid(),366, TimeUnit.DAYS);
                redisTemplate.opsForValue().set("use",use,366, TimeUnit.DAYS);
                return new Result(true, 1, "验证码验证通过 登录成功!", use);
            } else {
                return new Result(false, 2, "验证码验证通过 用户已禁用!", use);
            }
        } catch (Exception e) {
            return new Result(false, ResultStatusCode.FAIL, "验证码验证或登录失败！",null);
        }
    }*/

    @GetMapping( "/courseLevel" )
    @ResponseBody
    @ApiOperation( "选择APP首页学习阶段（当前登录用户rowguid，选择阶段的showStageId）" )
    public Result courseLevel(Integer showStageId, String rowguid) {
        try {
            studentService.updateShowStageId(showStageId, rowguid);
            return new Result(true, 0, "选择学习阶段成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "根据学习阶段查询课程失败!");
        }
    }

    @GetMapping( "/studyLevel" )
    @ResponseBody
    @ApiOperation( "查询学习阶段" )
    public Result getStudyLevel() {
        try {
            List<Level> findAll = studentService.getStudyLevel();
            return new Result(true, 0, "查询学习阶段成功!", findAll);
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "查询学习阶段失败!", null);
        }
    }

    @PutMapping( "/register" )
    @ResponseBody
    @ApiOperation( "完善用户注册信息" )
    public Result register(String mobile, @RequestBody UserResquest user) {
        try {
            // 判断是否已经注册该用户
            User use = studentService.findByMobile(mobile);
            if ( use.gettStatus() != 0 ) { // 0 正常 可以登录 已完善信息 1 禁用 2 发送验证码未完善信息
                studentService.register(user, mobile);
                return new Result(true, 0, "完善用户注册信息成功!", use);
            } else {
                studentService.updateUserInfo(user, mobile);
                return new Result(false, ResultStatusCode.FAIL, "该用户手机号已存在!");
            }
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "用户注册失败!");
        }
    }

    @PostMapping( "/third_login" )
    @ResponseBody
    @ApiOperation( "第三方登录" )
    public Result third_login(@RequestParam( value = "unionid", defaultValue = "" ) String unionid,
                              @RequestParam( value = "type", defaultValue = "wx" ) String type) {
        try {
            String userId = "";
            if ( "wx".equals(type) ) {
                //微信登录
                userId = studentService.isWxUnionidBinded(unionid);
            } else if ( "qq".equals(type) ) {
                userId = studentService.isQQUnionidBinded(unionid);
            } else {
                return Result.FAIL("type只能为wx和qq");
            }

            if ( StringUtils.isNullOrEmpty(userId) ) {
                //用户没有绑定过
                return Result.FAIL("此用户未绑定手机号，请去绑定手机号");
            } else {
                //绑定过了
                User use = studentService.findByGuid(userId);
                if ( use.gettStatus() == 0 ) { // 0 正常 可以登录 已完善信息 1 禁用 2 发送验证码未完善信息
                    return Result.SUCCESS("登录成功", use);
                } else if ( use.gettStatus() == 1 ) {
                    return Result.FAIL("此账号已被禁用");
                } else {
                    //用户没有绑定过
                    return Result.FAIL("此用户未绑定手机号，请去绑定手机号");
                }
            }

        } catch ( Exception e ) {
            e.printStackTrace();
            return Result.FAIL("用户登录失败!");
        }
    }

    @PutMapping( "/updateUserInfo" )
    @ResponseBody
    @ApiOperation( "修改用户信息" )
    public Result updateUserInfo(String mobile, @RequestBody UserResquest user) {
        try {
            logger.debug("controller：修改用户信息——前端传递的用户生日日期：", user.gettChildrenDate());
            studentService.updateUserInfo(user, mobile);
            return new Result(true, 0, "修改用户信息成功!");
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "修改用户信息失败!");
        }
    }

    @PutMapping( "/forgetPwd" )
    @ResponseBody
    @ApiOperation( "忘记密码 重置密码" )
    public Result forgetPwd(String rowguid, String mobile, String password) {
        try {
            User use = studentService.findByMobile(mobile);
            if ( use != null ) {
                if ( rowguid.equals(use.getRowGuid()) ) {
                    studentService.forgetPwd(mobile, password);
                    Map map = new HashMap();
                    map.put("rowguid", use.getRowGuid());
                    return new Result(true, 0, "修改密码成功!");
                }
                return new Result(false, 2, "没有权限修改密码!", null);
            }
            return new Result(false, 2, "手机号不存在!", null);
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, 3, "手机号不存在!", null);
        }
    }

    @GetMapping( "/checkMobile" )
    @ResponseBody
    @ApiOperation( "校验手机号是否存在" )
    public Result checkMobile(String mobile) {
        try {
            User use = studentService.findByMobile(mobile);
            return new Result(true, 0, "手机号已注册!", use);
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "手机号未注册!", null);
        }
    }

    @GetMapping( "/login" )
    @ResponseBody
    @ApiOperation( "用户登录" )
    public Result login(String mobile, String password, HttpServletRequest request) {
        try {
            User use = studentService.findByMobile(mobile);
            // jwt
            JwtBuilder builder = Jwts.builder();
            builder.setIssuer("air");// 颁发者
            builder.setIssuedAt(new Date());
            builder.setSubject("jwt登录");
            builder.signWith(SignatureAlgorithm.HS256, "airedu");
            String jwt = builder.compact();
            request.getSession().setAttribute("row_guid", use.getRowGuid());
            request.getSession().setAttribute("mobile", use.gettMobile());
            redisTemplate.opsForValue().set("row_guid", use.getRowGuid(), 366, TimeUnit.DAYS);
            redisTemplate.opsForValue().set(jwt, jwt, 366, TimeUnit.DAYS);
            if ( password.equals(use.gettPassword()) ) {
                use.settPassword(null);
                return new Result(true, 0, "登录成功!", use, null);
            } else {
                return new Result(false, ResultStatusCode.LOGIN_FAIL2, "手机号或密码输入错误!", null);
            }
        } catch ( Exception e ) {
            return new Result(false, ResultStatusCode.LOGIN_FAIL2, "手机号或密码输入错误!", null);
        }
    }

    @GetMapping( "/userInfo" )
    @ResponseBody
    @ApiOperation( "根据rowGuid获取用户信息" )
    public Result userInfo(String rowguid, HttpServletRequest request) {
        try {
            User user = studentService.userInfo(rowguid, request);
            return new Result(true, 0, "获取用户信息成功!", user);
        } catch ( Exception e ) {
            e.printStackTrace();
            return new Result(false, ResultStatusCode.FAIL, "获取用户信息失败!");
        }
    }

}
