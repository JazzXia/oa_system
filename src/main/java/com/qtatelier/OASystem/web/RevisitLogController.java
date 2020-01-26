package com.qtatelier.OASystem.web;

import com.qtatelier.OASystem.basics.revisit_log.service.RevisitLogService;
import com.qtatelier.OASystem.request.ReqRevisit;
import com.qtatelier.OASystem.request.RevisitLogReq;
import com.qtatelier.OASystem.response.RevisitLogRes;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@Api(value = "回访管理", tags = "回访管理")
@RequestMapping("revisitLog")
public class RevisitLogController {

    //日志
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RevisitLogService revisitLogService;

    @ApiOperation(value = "回访记录", notes = "获取回访记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @GetMapping("/info")
    @SystemControllerLog(description = "获取回访记录", optType = CodeBusiness.OPT_TYPE.SEARCH_CODE, moduleName = CodeBusiness.MODULE_NAME.REVISIT_MODULE)
    @UserLoginToken
    public ResultView getUserInfo(RevisitLogReq revisitLogReq, String token) {
        String logStr = "获取回访记录";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始={}", token, revisitLogReq.toString());
            List<RevisitLogRes> list = revisitLogService.findInfo(revisitLogReq);
            if (list.isEmpty()) {
                logger.info(logStr + "失败");
                return new ResultView(CodeEnum.ERROR_404, "未进行过回访");
            }
            return new ResultView(CodeEnum.SUCCESS, list);
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }


    /**
     * @param reqRevisit
     * @param token
     * @return
     * @description 新增部门
     */
    @ApiOperation(value = "增加回访", notes = "增加回访")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
    })
    @PostMapping("/add")
    @SystemControllerLog(description = "增加回访", optType = CodeBusiness.OPT_TYPE.ADD_CODE, moduleName = CodeBusiness.MODULE_NAME.REVISIT_MODULE)
    @UserLoginToken
    public ResultView insertDept(@RequestBody ReqRevisit reqRevisit,String token) {

        String logStr = "增加回访,deptInfo={}";
        ResultView resultView = null;
        try {
            logger.info(logStr + "开始", reqRevisit.toString());

            if (StringUtils.isNotBlank(reqRevisit.getRevisitLog())) {
                CodeEnum codeEnum = revisitLogService.addRevisitLog(reqRevisit);

                if (codeEnum != CodeEnum.SUCCESS) {
                    logger.error(logStr + "失败");
                    resultView = new ResultView(CodeEnum.ERROR_501, CodeEnum.ERROR_501.getName());
                }else {
                    resultView = new ResultView(codeEnum, "添加回访成功!");
                }
            } else {
                resultView = new ResultView(CodeEnum.ERROR_405, "回访内容不能为空");
            }
        } catch (Exception e) {
            logger.error(logStr + "失败", e);
            resultView = new ResultView(CodeEnum.ERROR_500, "增加回访", e);
        } finally {
            logger.info(logStr + "结束");
        }
        return resultView;
    }

}
