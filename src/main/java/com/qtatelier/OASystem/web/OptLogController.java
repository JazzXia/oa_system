package com.qtatelier.OASystem.web;

import com.qtatelier.OASystem.basics.optlog.model.OptLog;
import com.qtatelier.OASystem.basics.optlog.service.OptLogService;
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
 * @version V1.0
 * @description
 * @package com.qtatelier.OASystem.web
 * @date 2020-01-20 15:02
 * @email xiaww@redoornetwork.com
 */
@RestController
@Api(value = "日志管理", tags = "日志管理")
@RequestMapping("optLog")
public class OptLogController {

    //日志
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OptLogService optLogService;

    @ApiOperation(value = "获取日志", notes = "获取日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/info")
    @SystemControllerLog(description = "获取日志信息", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.LOG_MODULE)
    @UserLoginToken
    public ResultView getUserInfo(OptLog optLog, String token) {
        String logStr = "获取日志";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始={}", token, optLog.toString());
            List<OptLog> list = optLogService.selectByAccoutId(optLog);
            if (list.isEmpty()) {
                logger.info(logStr + "失败");
                return new ResultView(CodeEnum.ERROR_404, "距离上次操作超过24小时无数据!!");
            }
            return new ResultView(CodeEnum.SUCCESS, "获取日志成功", list);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
        } finally {

            logger.info(logStr + "结束");
        }
        return resultView;
    }
}
