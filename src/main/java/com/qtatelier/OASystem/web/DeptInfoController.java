package com.qtatelier.OASystem.web;

import com.qtatelier.OASystem.basics.deptinfo.model.DeptInfo;
import com.qtatelier.OASystem.basics.deptinfo.service.DeptInfoService;
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
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(value = "部门信息",tags = "部门CRUD操作")
public class DeptInfoController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeptInfoService deptInfoService;


    /**
     * @description 新增部门
     * @param deptInfo
     * @param token
     * @return
     */
    @ApiOperation(value = "添加部门", notes = "添加新部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @PostMapping("/add")
    @UserLoginToken
    public ResultView insertUser(@RequestBody DeptInfo deptInfo, @ApiIgnore String token) {

        String logStr = "新增用户,BlogUser={}";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始",deptInfo.toString());

            if (StringUtils.isNotBlank(deptInfo.getDeptName()))
            {
                CodeEnum codeEnum = deptInfoService.addDept(deptInfo);

                if(codeEnum != CodeEnum.SUCCESS){
                    logger.error(logStr+"新增部门失败");
                    resultView = new ResultView(CodeEnum.ERROR_501, CodeEnum.ERROR_501.getName());
                }
                resultView =  new ResultView(codeEnum,"新增部门成功");
            }else {
                resultView = new ResultView(CodeEnum.ERROR_405,"部门不能为空");
            }
        } catch (Exception e) {
            logger.error(logStr+"失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "新增部门异常",e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }
}
