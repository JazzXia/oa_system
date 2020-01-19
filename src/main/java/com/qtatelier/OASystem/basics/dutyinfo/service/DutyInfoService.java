package com.qtatelier.OASystem.basics.dutyinfo.service;

import com.qtatelier.OASystem.basics.dutyinfo.mapper.DutyInfoMapper;
import com.qtatelier.OASystem.basics.dutyinfo.model.DutyInfo;
import com.qtatelier.OASystem.basics.linkdutydept.mapper.LinkDutyDeptMapper;
import com.qtatelier.OASystem.request.DutyReq;
import com.qtatelier.OASystem.thread.DeptInfoException;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ToolRandom;
import com.qtatelier.config.ToolTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaweiwei
 * @version V1.0
 * @description
 * @package com.qtatelier.OASystem.basics.dutyinfo.service
 * @date 2020-01-19 14:55
 * @email xiaww@redoornetwork.com
 */
@Service
public class DutyInfoService {
	@Autowired
	private DutyInfoMapper dutyInfoMapper;
	@Autowired
	private LinkDutyDeptMapper linkDutyDeptMapper;

	/**
	 * @description 添加部门信息
	 * @param dutyInfo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public CodeEnum addDutyInfo( DutyReq dutyInfo ){
		CodeEnum codeEnum = CodeEnum.SUCCESS;

		//首先先添加部门信息,然后将关联表信息也添加进去
		if(null != dutyInfo){
			dutyInfo.setDutyId( ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase() );
			dutyInfo.setLinkId( ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase() );
			int isSuccess = dutyInfoMapper.insert(dutyInfo);
			if(isSuccess <= 0){
				return CodeEnum.ERROR_500;
			}
			isSuccess = linkDutyDeptMapper.insert( dutyInfo );
			if(isSuccess <= 0){
				return CodeEnum.ERROR_500;
			}
			return codeEnum;
		}
		throw new DeptInfoException( "职位相关信息不能为空" );
	}
}
