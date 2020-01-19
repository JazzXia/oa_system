package com.qtatelier.OASystem.web;

import com.qtatelier.OASystem.basics.dutyinfo.service.DutyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("linkDutyInfo")
public class DutyInfoController {

	@Autowired
	private DutyInfoService dutyInfoService;

}
