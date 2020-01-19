package com.qtatelier.OASystem.web;

import com.qtatelier.OASystem.basics.dutyinfo.service.DutyInfoService;
import com.qtatelier.OASystem.request.DutyReq;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaweiwei
 * @version V1.0
 * @description
 * @package com.qtatelier.OASystem.web
 * @date 2020-01-19 15:12
 * @email xiaww@redoornetwork.com
 */
@RestController
@Api(value = "职务管理", tags = "职务管理")
@RequestMapping("linkDutyInfo")
public class DutyInfoController {

	//日志
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private DutyInfoService dutyInfoService;


	/**
	 * @description 新增职位
	 * @return
	 */
	@ApiOperation(value = "新增职位", notes = "新增职位")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "令牌", paramType = "header", dataType = "String", required = true)
	})
	@PostMapping("/add")
	@UserLoginToken
	public ResultView insertUser(@RequestBody DutyReq dutyInfo,String token) {

		String logStr = "新增职位,DutyReq={}";
		ResultView resultView = null;
		try {
			logger.info(logStr + "开始",dutyInfo.toString());
			CodeEnum codeEnum = dutyInfoService.addDutyInfo(dutyInfo);
			if(codeEnum != CodeEnum.SUCCESS){
				logger.error(logStr+"失败");
				resultView = new ResultView(CodeEnum.ERROR_501, CodeEnum.ERROR_501.getName());
			}
			resultView =  new ResultView(CodeEnum.SUCCESS,"新增职位成功");
			return resultView;
		} catch (Exception e) {
			logger.error(logStr+"失败", e);
			resultView = new ResultView(CodeEnum.ERROR_500, e.getMessage());
		} finally {
			logger.info(logStr + "结束");
		}
		return resultView;
	}
}
