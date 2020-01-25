package com.qtatelier.OASystem.web;

import com.qtatelier.OASystem.basics.customerinfo.model.CustomerInfo;
import com.qtatelier.OASystem.basics.customerinfo.service.CustomerInfoService;
import com.qtatelier.OASystem.request.CustomerReq;
import com.qtatelier.common.aop.SystemControllerLog;
import com.qtatelier.config.CodeBusiness;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ResultView;
import com.qtatelier.dev_util.commons.annotation.UserLoginToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-24 19:59
 */
@RestController
@RequestMapping("customer")
@Api(value = "客户信息",tags = "客户所有的信息")
public class CustomerInfoController {
    //日志
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CustomerInfoService customerInfoService;

    @GetMapping("/allInfo")
    @ApiOperation(value = "获取客户所有信息", notes = "获取客户所有信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @SystemControllerLog(description = "获取客户所有信息", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.CUSTOMER_MODULE)
    @UserLoginToken
    public ResultView findCustomerAllInfo(CustomerReq customerReq,String token){
        String logStr = "查出每个部门所有的职位";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", token);
            List<CustomerInfo> list = customerInfoService.findAll(customerReq);
            resultView = new ResultView(CodeEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }
}
