package com.qtatelier.OASystem.request;

import lombok.Data;

/**
 * @author xiaweiwei
 * @version V1.0
 * @description
 * @package com.qtatelier.OASystem.request
 * @date 2020-01-19 15:04
 * @email xiaww@redoornetwork.com
 */
@Data
public class DutyReq
{
	private String dutyName;
	private String dutyId;
	private String deptId;
	private String linkId;
	private String dutyDescription;
}
