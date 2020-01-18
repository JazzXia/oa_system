package com.qtatelier.OASystem.basics.deptinfo.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qtatelier.OASystem.basics.deptinfo.mapper.DeptInfoMapper;
import com.qtatelier.OASystem.basics.deptinfo.model.DeptInfo;
import com.qtatelier.OASystem.thread.DeptInfoException;
import com.qtatelier.common.OaDesc;
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
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-12 20:41
 */
@Service
public class DeptInfoService {

    @Autowired
    private DeptInfoMapper deptInfoMapper;


    /**
     * @param deptInfo
     * @return
     * @description 添加部门
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum addDept(DeptInfo deptInfo) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (null != deptInfo) {
            deptInfo.setDeptId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
            deptInfo.setDeptNo(OaDesc.DeptInfo.getDesc() + ToolTime.getNowStringByAllTime());
            int isSuccess = deptInfoMapper.insert(deptInfo);
            if (isSuccess < 1) {
                codeEnum = CodeEnum.ERROR_501;
            }
            return codeEnum;
        }
        throw new DeptInfoException("不能为空值!");
    }

    /**
     * @return
     * @description 查出所有部门信息
     */
    public PageInfo<DeptInfo> findAllDeptInfo(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum == null ? 0 : pageNum.intValue(), pageSize == null ? 10 : pageSize.intValue(),
                false);
        List<DeptInfo> list = deptInfoMapper.findAllDeptInfo();
        PageInfo<DeptInfo> pageInfo = new PageInfo<>(list);
        pageInfo.setTotal(deptInfoMapper.findCount());
        return pageInfo;
    }

    /**
     * @param deptId
     * @return
     * @description 删除部门
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum delectDeptInfo(String deptId) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (StringUtils.isNotBlank(deptId)) {
            int isSuccess = deptInfoMapper.deleteByPrimaryKey(deptId);
            if (isSuccess < 1) {
                codeEnum = CodeEnum.ERROR_501;
            }
            return codeEnum;
        }
        throw new DeptInfoException("部门编号不能为空");
    }


    /**
     * @param deptId
     * @return
     * @description 按主键查部门
     */
    public DeptInfo selectInfoByKey(String deptId) {

        if (StringUtils.isNotBlank(deptId)) {
            DeptInfo deptInfo = deptInfoMapper.selectByPrimaryKey(deptId);
            return deptInfo;
        }
        throw new DeptInfoException("部门编号不能为空");
    }


    @Transactional(rollbackFor = Exception.class)
    public CodeEnum updateDeptInfo(DeptInfo deptInfo) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (null != deptInfo) {
            int isSuccess = deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
            if (isSuccess < 1) {
                codeEnum = CodeEnum.ERROR_501;
            }
            return codeEnum;
        }
        throw new DeptInfoException("不能为空值!");
    }


}
