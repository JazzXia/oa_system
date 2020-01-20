package com.qtatelier.OASystem.basics.dutyinfo.service;

import com.qtatelier.OASystem.basics.dutyinfo.mapper.DutyInfoMapper;
import com.qtatelier.OASystem.basics.dutyinfo.model.DutyInfo;
import com.qtatelier.OASystem.basics.linkdutydept.mapper.LinkDutyDeptMapper;
import com.qtatelier.OASystem.request.DutyReq;
import com.qtatelier.OASystem.response.DutyInfoRes;
import com.qtatelier.OASystem.thread.DeptInfoException;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ToolRandom;
import com.qtatelier.config.ToolTime;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
     * @param dutyInfo
     * @return
     * @description 添加部门信息
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum addDutyInfo(DutyReq dutyInfo) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;

        //首先先添加部门信息,然后将关联表信息也添加进去
        if (null != dutyInfo) {
            dutyInfo.setDutyId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
            dutyInfo.setLinkId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
            int isSuccess = dutyInfoMapper.insert(dutyInfo);
            if (isSuccess <= 0) {
                return CodeEnum.ERROR_500;
            }
            isSuccess = linkDutyDeptMapper.insert(dutyInfo);
            if (isSuccess <= 0) {
                return CodeEnum.ERROR_500;
            }
            return codeEnum;
        }
        throw new DeptInfoException("职位相关信息不能为空");
    }


    /**
     * @return
     * @description 本质上可以使用pageHelper来进行后端分页, 但是前端使用的是layui所以性能有所降低
     */
    public List<DutyInfoRes> selectAll() {
        List<DutyInfoRes> list = dutyInfoMapper.findAll();
        if (list.isEmpty()) {
            throw new DeptInfoException("当前部门下无职位信息");
        }
        return list;
    }


    @Transactional(rollbackFor = Exception.class)
    public CodeEnum deleteDuty(String dutyId) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        //如果不为空
        if (StringUtils.isNotBlank(dutyId)) {
            int isSuccess = dutyInfoMapper.deleteByPrimaryKey(dutyId);
            if (isSuccess < 0) {
                return CodeEnum.ERROR_500;
            }
            isSuccess = linkDutyDeptMapper.deleteByDutyId(dutyId);
            if (isSuccess < 0) {
                return CodeEnum.ERROR_500;
            }
            return codeEnum;
        }
        throw new DeptInfoException("当前id为空!!!");
    }


    public DutyInfo selectInfoByKey(String dutyId) {
        if (StringUtils.isNotBlank(dutyId)) {
            DutyInfo dutyInfo = dutyInfoMapper.selectByPrimaryKey(dutyId);
            if (dutyInfo == null) {
                throw new DeptInfoException("当前无数据!!!");
            }
            return dutyInfo;
        }
        throw new DeptInfoException("当前id为空!!!");
    }


    @Transactional(rollbackFor = Exception.class)
    public CodeEnum updateInfo(DutyInfoRes dutyInfoRes) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (dutyInfoRes != null) {
            int isSuccess = dutyInfoMapper.updateByPrimaryKeySelective(dutyInfoRes);
            if (isSuccess < 0) {
                return CodeEnum.ERROR_500;
            }
            isSuccess = linkDutyDeptMapper.updateByPrimaryKeySelective(dutyInfoRes);
            if (isSuccess < 0) {
                return CodeEnum.ERROR_500;
            }
            return codeEnum;
        }
        throw new DeptInfoException("当前职务对象为空");
    }


    public List<DutyInfoRes> findInfoByDeptId(String deptId){
        if (StringUtils.isNotBlank(deptId)){
            return dutyInfoMapper.findInfoByDeptId(deptId);
        }
        throw new DeptInfoException("未选择部门");
    }

}
