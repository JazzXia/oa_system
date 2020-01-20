package com.qtatelier.OASystem.request;

import lombok.Data;

/**
 * @author xiaweiwei
 * @version V1.0
 * @description
 * @package com.qtatelier.OASystem.request
 * @date 2020-01-20 16:23
 * @email xiaww@redoornetwork.com
 */
@Data
public class UserEmp {

	private String userId;

	private String password;

	private String sign;

	private String location;

	private String empId;

	private String empNo;

	private String linkIdDept;

	private String linkIdRole;

	private String linkIdUser;

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
	private String webUrl;
	private String callself;

}
