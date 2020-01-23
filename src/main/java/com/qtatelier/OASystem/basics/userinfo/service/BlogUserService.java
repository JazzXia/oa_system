package com.qtatelier.OASystem.basics.userinfo.service;

import com.qtatelier.OASystem.basics.empinfo.mapper.EmpInfoMapper;
import com.qtatelier.OASystem.basics.linkdutydept.mapper.LinkDutyDeptMapper;
import com.qtatelier.OASystem.basics.linkempdept.mapper.LinkEmpDeptMapper;
import com.qtatelier.OASystem.basics.linkempuser.mapper.LinkEmpUserMapper;
import com.qtatelier.OASystem.basics.linkroleuser.mapper.LinkRoleUserMapper;
import com.qtatelier.OASystem.basics.linkroleuser.model.LinkRoleUser;
import com.qtatelier.OASystem.basics.userinfo.mapper.BlogUserMapper;
import com.qtatelier.OASystem.request.UserEmp;
import com.qtatelier.OASystem.response.ResBlogUser;
import com.qtatelier.OASystem.thread.UserException;
import com.qtatelier.common.OaDesc;
import com.qtatelier.config.CodeEnum;
import com.qtatelier.config.ToolRandom;
import com.qtatelier.config.ToolTime;
import com.qtatelier.dto.BlogUser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @description: 用户
 */

@CacheConfig(cacheNames = "ROLETYPE", cacheManager = "MycacheManager")
@Service
public class BlogUserService {

    @Autowired
    private BlogUserMapper blogUserMapper;
    @Autowired
    private LinkRoleUserMapper linkRoleUserMapper;
    @Autowired
    private LinkEmpUserMapper linkEmpUserMapper;
    @Autowired
    private EmpInfoMapper empInfoMapper;
    @Autowired
    private LinkEmpDeptMapper linkEmpDeptMapper;
    @Autowired
    private LinkDutyDeptMapper linkDutyDeptMapper;

    @Value("${login.salt}")
    private String salt;

    @Value("${login.password}")
    private String defaultPassword;

    /**
     * @param deptId
     * @param nickName
     * @return
     * @description 模糊查询用户职位列表
     */
    public List<ResBlogUser> findAll(String deptId, String nickName) {
        return blogUserMapper.empList(deptId, nickName);
    }


    /**
     * 通过用户名查询
     *
     * @param blogUser
     * @return
     */
    public BlogUser findUserByname(BlogUser blogUser) {
        if (blogUser != null) {
            return blogUserMapper.selectByUserName(blogUser);
        } else {
            throw new UserException("无当前用户");
        }

    }

    /**
     * @param userEmp
     * @return
     * @description 添加用户
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum insertUser(UserEmp userEmp) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (userEmp != null) {
            String userId = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
            String linkIdRole = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
            String empId = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
            String empNo = OaDesc.EmpInfo.getDesc() + ToolTime.getNowStringByAllTime();
            String linkIdDept = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
            String linkIdUser = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
            userEmp.setEmpId(empId);
            userEmp.setEmpNo(empNo);
            userEmp.setLinkIdDept(linkIdDept);
            userEmp.setLinkIdUser(linkIdUser);
            userEmp.setLinkIdRole(linkIdRole);
            userEmp.setUserId(userId);
            userEmp.setPassword(DigestUtils.md5Hex(salt + defaultPassword));
            int isSuccess = blogUserMapper.insertInfo(userEmp);
            if (isSuccess < 1) {
                if (isSuccess < 0) {
                    return CodeEnum.ERROR_500;
                }
            }
            if (Judge(linkRoleUserMapper.insertInfo(userEmp))) {
                return CodeEnum.ERROR_500;
            }
            if (Judge(linkEmpUserMapper.insertInfo(userEmp))) {
                return CodeEnum.ERROR_500;
            }
            if (Judge(empInfoMapper.insertInfo(userEmp))) {
                return CodeEnum.ERROR_500;
            }
            if (Judge(linkEmpDeptMapper.insertInfo(userEmp))) {
                return CodeEnum.ERROR_500;
            }
            if (Judge(linkDutyDeptMapper.updateInfo(userEmp))) {
                return CodeEnum.ERROR_500;
            }
            return codeEnum;
        }
        throw new UserException("添加的用户不能为空");
    }

    private boolean Judge(int isSuccess) {
        if (isSuccess < 1) {
            return true;
        }
        return false;
    }

    /**
     *
     * @description 添加用户
     * @param blogUser
     * @return
     */
//    @Transactional(rollbackFor = Exception.class)
//    public int insertUser(BlogUser blogUser){
//        blogUser.setUserId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
//        blogUser.setPassword(DigestUtils.md5Hex(salt + blogUser.getPassword().trim()));
//        return blogUserMapper.insert(blogUser);
//    }


    /**
     * @param userId
     * @return
     * @description 简单查询用户具体信息
     */
    @Cacheable(value = "ROLE")
    public ResBlogUser findUserInfo(String userId) {
        if (StringUtils.isNotBlank(userId)) {
            return blogUserMapper.selectByPrimaryKey(userId);
        }
        throw new UserException("用户未登录!");
    }


    /**
     * @param empNo
     * @return
     * @description 用户办理离职手续【即删除用户】
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum removeEmp(String empNo) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (StringUtils.isNotBlank(empNo)) {
            int isSuccess = empInfoMapper.removeEmp(empNo);
            if (isSuccess < 1) {
                throw new UserException("修改离职状态失败!");
            }
            String empId = empInfoMapper.selectIdByNo(empNo);
            String linkId = linkDutyDeptMapper.findIndByEmp(empId);
            if (StringUtils.isNotBlank(linkId)) {
                isSuccess = linkDutyDeptMapper.updateById(linkId);
                if (isSuccess < 1) {
                    throw new UserException("修改关联状态失败!");
                }
            }
            return codeEnum;
        }
        throw new UserException("当前员工不存在!");
    }


    public ResBlogUser showEmpInfo(String empNo) {
        if (StringUtils.isNotBlank(empNo)) {
            ResBlogUser resBlogUser = blogUserMapper.empInfoDetail(empNo);
            return resBlogUser;
        }
        throw new UserException("当前员工不存在");
    }


    @Transactional(rollbackFor = Exception.class)
    public CodeEnum updateEmp(String empNo,String dutyId,String roleId){
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (StringUtils.isNotBlank(empNo)){
            String empId = empInfoMapper.selectIdByNo(empNo);
            String linkId = linkDutyDeptMapper.findIndByEmp(empId);
            if (StringUtils.isNotBlank(linkId)) {
                int isSuccess = linkDutyDeptMapper.updateById(linkId);
                if (isSuccess < 1) {
                    throw new UserException("修改关联状态失败!");
                }
                UserEmp userEmp = new UserEmp();
                userEmp.setDutyId(dutyId);
                userEmp.setEmpId(empId);
                if (Judge(linkDutyDeptMapper.updateInfo(userEmp))) {
                    return CodeEnum.ERROR_500;
                }
                String userId = linkEmpUserMapper.findInfoById(empId);
                LinkRoleUser linkRoleUser = new LinkRoleUser();
                linkRoleUser.setRoleId(roleId);
                linkRoleUser.setUserId(userId);
                isSuccess = linkRoleUserMapper.updateRoleId(linkRoleUser);
                if (isSuccess < 1) {
                    throw new UserException("修改权限失败!");
                }
            }
            return codeEnum;
        }
        throw new UserException("当前员工不存在");
    }

}