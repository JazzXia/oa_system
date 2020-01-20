package com.qtatelier.OASystem.request;

import lombok.Data;

/**
 * @author xiaweiwei
 * @version V1.0
 * @description
 * @package com.qtatelier.OASystem.request
 * @date 2020-01-20 17:35
 * @email xiaww@redoornetwork.com
 */
@Data
public class UserReq {
	private String username;
	private String nickName;
	private String imageName;
	private String userEmail;
	private String roleType;
	private String roleTypeName;
	private String deptId;
	private String dutyId;
	private String roleId;
	private String token;
}
