package com.qtatelier.OASystem.web;

import com.github.pagehelper.PageInfo;
import com.qtatelier.OASystem.basics.deptinfo.model.DeptInfo;
import com.qtatelier.OASystem.basics.deptinfo.service.DeptInfoService;
import com.qtatelier.common.aop.SystemControllerLog;
import com.qtatelier.config.CodeBusiness;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ResultView;
import com.qtatelier.dev_util.commons.annotation.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-12 20:48
 */
@RestController
@RequestMapping("dept")
@Api(value = "部门信息", tags = "部门CRUD操作")
public class DeptInfoController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeptInfoService deptInfoService;


    /**
     * @param deptInfo
     * @param token
     * @return
     * @description 新增部门
     */
    @ApiOperation(value = "添加部门", notes = "添加新部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @PostMapping("/add")
    @SystemControllerLog(description = "添加部门", optType = CodeBusiness.OPT_TYPE.ADD_CODE, moduleName = CodeBusiness.MODULE_NAME.DEPT_MODULE)
    @UserLoginToken
    public ResultView insertDept(@RequestBody DeptInfo deptInfo, @ApiIgnore String token) {

        String logStr = "添加部门,deptInfo={}";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", deptInfo.toString());

            if (StringUtils.isNotBlank(deptInfo.getDeptName())) {
                CodeEnum codeEnum = deptInfoService.addDept(deptInfo);

                if (codeEnum != CodeEnum.SUCCESS) {
                    logger.error(logStr + "新增部门失败");
                    resultView = new ResultView(CodeEnum.ERROR_501, CodeEnum.ERROR_501.getName());
                }
                resultView = new ResultView(codeEnum, "新增部门成功");
            } else {
                resultView = new ResultView(CodeEnum.ERROR_405, "部门不能为空");
            }
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "新增部门异常", e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }

    @ApiOperation(value = "展示部门", notes = "显示所有的部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "查出所有的部门", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.DEPT_MODULE)
    @GetMapping("/showAll")
    @UserLoginToken
    public ResultView findAll(Integer pageNum, Integer pageSize, String token) {
        String logStr = "查出所有的部门";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token);
            PageInfo<DeptInfo> pageInfo = deptInfoService.findAllDeptInfo(pageNum, pageSize);
            resultView = new ResultView(CodeEnum.SUCCESS, pageInfo);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "展示部门异常", e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


    @ApiOperation(value = "删除部门", notes = "删除部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "删除部门", optType = CodeBusiness.OPT_TYPE.DELETE_CODE, moduleName = CodeBusiness.MODULE_NAME.DEPT_MODULE)
    @DeleteMapping("/delete/{deptId}")
    @UserLoginToken
    public ResultView deleteDept(@PathVariable("deptId") String deptId, String token) {

        String logStr = "删除部门,deptId={}";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", deptId);

            if (StringUtils.isNotBlank(deptId)) {
                CodeEnum codeEnum = deptInfoService.delectDeptInfo(deptId);

                if (codeEnum != CodeEnum.SUCCESS) {
                    logger.error(logStr + "删除部门失败");
                    resultView = new ResultView(CodeEnum.ERROR_502, CodeEnum.ERROR_502.getName());
                }
                resultView = new ResultView(codeEnum, "删除部门成功");
            } else {
                resultView = new ResultView(CodeEnum.ERROR_405, "部门id不能为空");
            }
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "删除部门异常", e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


    @ApiOperation(value = "按主键查询部门信息", notes = "按主键查询部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "按主键查询部门信息", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.DEPT_MODULE)
    @GetMapping("/detail")
    @UserLoginToken
    public ResultView findInfo(String deptId, String token) {
        String logStr = "按主键查询部门信息";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token, deptId);
            DeptInfo deptInfo = deptInfoService.selectInfoByKey(deptId);
            resultView = new ResultView(CodeEnum.SUCCESS, deptInfo);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


    @ApiOperation(value = "按主键修改部门信息", notes = "按主键修改部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "按主键修改部门信息", optType = CodeBusiness.OPT_TYPE.UPDATE_CODE, moduleName = CodeBusiness.MODULE_NAME.DEPT_MODULE)
    @PutMapping("/updateDeptInfo")
    @UserLoginToken
    public ResultView updateDeptInfo(DeptInfo deptInfo, String token) {
        String logStr = "按主键修改部门信息";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token, deptInfo.toString());
            CodeEnum codeEnum = deptInfoService.updateDeptInfo(deptInfo);
            if (codeEnum != CodeEnum.SUCCESS) {
                logger.error(logStr + "修改部门失败");
                resultView = new ResultView(CodeEnum.ERROR_502, "修改部门失败!");
            }
            resultView = new ResultView(CodeEnum.SUCCESS, deptInfo);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


}
