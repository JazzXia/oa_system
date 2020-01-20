package com.qtatelier.OASystem.basics.userinfo.service;

import com.qtatelier.OASystem.basics.empinfo.mapper.EmpInfoMapper;
import com.qtatelier.OASystem.basics.linkdutydept.mapper.LinkDutyDeptMapper;
import com.qtatelier.OASystem.basics.linkempdept.mapper.LinkEmpDeptMapper;
import com.qtatelier.OASystem.basics.linkempuser.mapper.LinkEmpUserMapper;
import com.qtatelier.OASystem.basics.linkroleuser.mapper.LinkRoleUserMapper;
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

@CacheConfig(cacheNames = "ROLETYPE",cacheManager = "MycacheManager")
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

    @Value( "${login.password}" )
    private String defaultPassword;

    @Cacheable(value = "ROLE")
    public List<BlogUser> findAll() {
        return blogUserMapper.selectAll();
    }


    /**
     * 通过用户名查询
     * @param blogUser
     * @return
     */
    public BlogUser findUserByname(BlogUser blogUser) {
        return blogUserMapper.selectByUserName(blogUser);
    }

    /**
     *
     * @description 添加用户
     * @param userEmp
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum insertUser( UserEmp userEmp){
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (userEmp != null){
        String userId = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
        String linkIdRole = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
        String empId = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
        String empNo = OaDesc.EmpInfo.getDesc() + ToolTime.getNowStringByAllTime();
        String linkIdDept = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
        String linkIdUser = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
        userEmp.setEmpId( empId );
        userEmp.setEmpNo( empNo );
        userEmp.setLinkIdDept( linkIdDept );
        userEmp.setLinkIdUser( linkIdUser );
        userEmp.setLinkIdRole( linkIdRole );
        userEmp.setUserId(userId);
        userEmp.setPassword(DigestUtils.md5Hex(salt + defaultPassword));
        int isSuccess =  blogUserMapper.insertInfo(userEmp);
        if(isSuccess < 1){
            if (isSuccess < 0) {
                return CodeEnum.ERROR_500;
            }
        }
        if (Judge( linkRoleUserMapper.insertInfo( userEmp ) )){
            return CodeEnum.ERROR_500;
        }
        if (Judge( linkEmpUserMapper.insertInfo( userEmp ) )) {
            return CodeEnum.ERROR_500;
        }
        if (Judge( empInfoMapper.insertInfo( userEmp ) )) {
            return CodeEnum.ERROR_500;
        }
        if (Judge( linkEmpDeptMapper.insertInfo( userEmp ) )) {
            return CodeEnum.ERROR_500;
        }
        if (Judge( linkDutyDeptMapper.updateInfo( userEmp ) )){
            return CodeEnum.ERROR_500;
        }
        return codeEnum;
        }
        throw new UserException( "添加的用户不能为空" );
    }

    private boolean Judge( int isSuccess ) {
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
     * @description 简单查询用户具体信息
     * @param userId
     * @return
     */
    public ResBlogUser findUserInfo(String userId){
        if (StringUtils.isNotBlank( userId )){
            return blogUserMapper.selectByPrimaryKey(userId);
        }
        throw new UserException("用户未登录!");
    }

}