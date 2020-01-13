package com.qtatelier.OASystem.basics.userinfo.service;

import com.qtatelier.OASystem.basics.userinfo.mapper.BlogUserMapper;
import com.qtatelier.OASystem.response.ResBlogUser;
import com.qtatelier.config.ToolRandom;
import com.qtatelier.config.ToolTime;
import com.qtatelier.dto.BlogUser;
import org.apache.commons.codec.digest.DigestUtils;
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
    @Value("#{'salt'}")
    private String salt;

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
     * @param blogUser
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int insertUser(BlogUser blogUser){
        blogUser.setUserId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
        blogUser.setPassword(DigestUtils.md5Hex(salt + blogUser.getPassword().trim()));
        return blogUserMapper.insert(blogUser);
    }


    /**
     * @description 简单查询用户具体信息
     * @param userId
     * @return
     */
    public ResBlogUser findUserInfo(String userId){

        return blogUserMapper.selectByPrimaryKey(userId);
    }

}