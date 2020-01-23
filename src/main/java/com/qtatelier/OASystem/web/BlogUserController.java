package com.qtatelier.OASystem.web;

import com.alibaba.fastjson.JSONObject;
import com.qtatelier.OASystem.basics.userinfo.service.BlogUserService;
import com.qtatelier.OASystem.request.UserEmp;
import com.qtatelier.OASystem.request.UserReq;
import com.qtatelier.OASystem.response.ResBlogUser;
import com.qtatelier.common.aop.SystemControllerLog;
import com.qtatelier.config.CodeBusiness;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ResultView;
import com.qtatelier.config.ToolRedis;
import com.qtatelier.config.token.JWTUtil;
import com.qtatelier.dev_util.commons.annotation.UserLoginToken;
import com.qtatelier.dto.BlogUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

/**
 * @description: 用户信息以及权限管理
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-01-12 13:48
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com & xiaww@redoornetwork.com
 */
@RestController
@Api(value = "用户信息", tags = "用户信息列表")
@RequestMapping("/role")
public class BlogUserController {

    //日志
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BlogUserService blogUserService;

    @Autowired
    private JWTUtil tokenService;

    @Autowired
    private ToolRedis redis;

    @Value("${login.salt}")
    private String salt;


    /**
     * @return
     * @description 获取用户信息
     */

    @ApiOperation(value = "获取用户", notes = "获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/info")
    @SystemControllerLog(description = "登录并展示当前信息", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.USER_MODULE)
    @UserLoginToken
    public ResultView getUserInfo(String userId, String token) {
        String logStr = "获取用户";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始={}", token, userId);
            ResBlogUser blogUser = blogUserService.findUserInfo(userId);
            if (null == blogUser) {
                logger.info(logStr + "失败");
                return new ResultView(CodeEnum.ERROR_404, "暂无用户信息");
            }
            return new ResultView(CodeEnum.SUCCESS, "获取用户成功", blogUser);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "获取用户异常");
        } finally {

            logger.info(logStr + "结束");
        }
        return resultView;
    }


    /**
     * @return
     * @description 获取用户列表[可分页,可不分页]
     */

    @ApiOperation(value = "获取所有用户", notes = "获取所有用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/list")
    @SystemControllerLog(description = "获取所有用户信息", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.USER_MODULE)
    @UserLoginToken
    public ResultView getUser(String deptId,String nickName, String token) {
        String logStr = "获取所有用户,token={}";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token);
            if (deptId == ""){
                deptId = null;
            }
            if (nickName == ""){
                nickName = null;
            }
            List<ResBlogUser> list = blogUserService.findAll(deptId,nickName);
            if (list.isEmpty()) {
                logger.info(logStr + "失败", token);
                return new ResultView(CodeEnum.ERROR_404, "暂无用户信息");
            }
            return new ResultView(CodeEnum.SUCCESS, "获取所有用户成功", list);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "获取所有用户异常");
        } finally {

            logger.info(logStr + "结束");
        }
        return resultView;
    }


    /**
     * @return
     * @description 新增用户
     */
    @ApiOperation(value = "添加用户", notes = "添加新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "新增用户", optType = CodeBusiness.OPT_TYPE.ADD_CODE, moduleName = CodeBusiness.MODULE_NAME.USER_MODULE)
    @PostMapping("/add")
    @UserLoginToken
    public ResultView insertUser(UserReq userReq, @Param("token") String token, MultipartFile imageUser) {

        String logStr = "新增用户,BlogUser={}";
        ResultView resultView = null;
        try {
            //获得文件名
            String file = imageUser.getOriginalFilename();
            System.out.println("文件名:"+file);
            UserEmp userEmp = new UserEmp();
            userEmp.setImageName(file);
            userEmp.setDeptId(userReq.getDeptId());
            userEmp.setDutyId(userReq.getDutyId());
            userEmp.setRoleId(userReq.getRoleId());
            //真实姓名
            userEmp.setNickName(userReq.getNickName());
            //用户名
            userEmp.setUsername(userReq.getUsername());
                userEmp.setUserEmail(userReq.getUserEmail());
            userEmp.setRoleType(userReq.getRoleType());
            userEmp.setRoleTypeName(userReq.getRoleTypeName());
            //性别
            userEmp.setCallself(userReq.getCallself());
            //网站
            userEmp.setWebUrl(userReq.getWebUrl());
            //"E:"+File.separator+"idea_workspace"+File.separator+"oa_system"+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"META-INF"+File.separator+"resources"+File.separator+"head_image"
            File dir = new File("E:\\idea_workspace\\oa_system\\src\\main\\resources\\META-INF\\resources\\head_image");
            dir.mkdir();
            File f1 = new File(dir,file);
            imageUser.transferTo(f1);

            logger.info(logStr + "开始", userEmp.toString());
            CodeEnum codeEnum = blogUserService.insertUser(userEmp);
            if (codeEnum != CodeEnum.SUCCESS) {
                logger.error(logStr + "新增用户不能为空");
                resultView = new ResultView(CodeEnum.ERROR_404, "新增用户不能为空");
            }else {
                resultView = new ResultView(CodeEnum.SUCCESS, "新增用户成功");
            }
            return resultView;
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "新增用户异常");
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


    /**
     * @param userName
     * @param password
     * @return
     * @description 用户登录
     */
    @ApiOperation(value = "登录", notes = "登录账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", paramType = "query", dataType = "String")
    })
    @SystemControllerLog(description = "用户登录", optType = CodeBusiness.OPT_TYPE.LOGIN_CODE, moduleName = CodeBusiness.MODULE_NAME.LOGIN_MODULE)
    @PostMapping("/login")
    public ResultView login(String userName, String password) {
        BlogUser blogUser = new BlogUser();
        blogUser.setPassword(password);
        blogUser.setUsername(userName);
        logger.info("用户" + blogUser.getUsername() + "尝试登录");
        String logStr = "用户登录";
        ResultView resultView = null;
        try {
            BlogUser userForBase = blogUserService.findUserByname(blogUser);
            if (userForBase == null) {
                logger.warn(logStr + "账号" + blogUser.getUsername() + "不存在！");
                resultView = new ResultView(CodeEnum.ERROR_403, "登录失败，账号或密码错误");
                return resultView;
            } else {
                if (!userForBase.getPassword().equals(DigestUtils.md5Hex(salt + blogUser.getPassword().trim()))) {
                    logger.warn(logStr + "密码错误");
                    resultView = new ResultView(CodeEnum.ERROR_403, "登录失败，账号或密码错误");
                    return resultView;
                } else {
                    String token = tokenService.getToken(userForBase);
                    userForBase.setToken(token);
                    //用户信息存入redis中
                    redis.set(CodeBusiness.TOKEN_ACCESS_KEY + token, userForBase, CodeBusiness.SESSTION_TIME);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("user", userForBase);
                    logger.info(logStr + "登录成功");
                    resultView = new ResultView(CodeEnum.SUCCESS, "登陆成功", jsonObject);
                    return resultView;
                }
            }
        } catch (Exception e) {
            logger.error(logStr + "用户登录失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "登录系统异常");
        } finally {
            logger.info(logStr + "用户：" + blogUser.getUsername() + "登录结束");
        }
        return resultView;
    }


    /**
     * @param
     * @return
     * @description 登出
     */
    @ApiOperation(value = "登出", notes = "退出账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "退出账号", optType = CodeBusiness.OPT_TYPE.LOGOUT_CODE, moduleName = CodeBusiness.MODULE_NAME.LOGIN_MODULE)
    @GetMapping("/logout")
    @UserLoginToken
    public ResultView logout(String token) {
        String logStr = "用户退出";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token);
            boolean flag = redis.delKey(CodeBusiness.TOKEN_ACCESS_KEY);
            if (flag) {
                resultView = new ResultView(CodeEnum.SUCCESS, "退出成功");
            } else {
                resultView = new ResultView(CodeEnum.ERROR_500, "退出失败");
            }

            return resultView;
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "退出异常", e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


    @ApiOperation(value = "移除当前用户", notes = "移除当前用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "移除当前用户", optType = CodeBusiness.OPT_TYPE.UPDATE_CODE, moduleName = CodeBusiness.MODULE_NAME.USER_MODULE)
    @PutMapping("/removeEmp/{empNo}")
    @UserLoginToken
    public ResultView removeEmp(@PathVariable("empNo") String empNo, String token) {
        String logStr = "移除当前用户";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token, empNo);
            CodeEnum codeEnum = blogUserService.removeEmp(empNo);
            if (codeEnum != CodeEnum.SUCCESS) {
                logger.error(logStr + "失败");
                resultView = new ResultView(CodeEnum.ERROR_502, "移除当前用户失败!");
            }
            resultView = new ResultView(codeEnum,"该员工离职成功!");
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


    @ApiOperation(value = "用户列表的详情", notes = "用户列表的详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empNo", value = "员工工号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/showEmpInfo")
    @SystemControllerLog(description = "用户列表的详情", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.USER_MODULE)
    @UserLoginToken
    public ResultView showEmpInfo(String empNo, String token) {
        String logStr = "用户列表的详情";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始={}", token, empNo);
            ResBlogUser blogUser = blogUserService.showEmpInfo(empNo);
            if (null == blogUser) {
                logger.info(logStr + "失败");
                return new ResultView(CodeEnum.ERROR_404, "暂无用户信息");
            }
            return new ResultView(CodeEnum.SUCCESS, "获取用户成功", blogUser);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
        } finally {

            logger.info(logStr + "结束");
        }
        return resultView;
    }


}
