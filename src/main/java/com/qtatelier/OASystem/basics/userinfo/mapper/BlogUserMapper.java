package com.qtatelier.OASystem.basics.userinfo.mapper;

import com.qtatelier.OASystem.request.UserEmp;
import com.qtatelier.OASystem.response.ResBlogUser;
import com.qtatelier.dto.BlogUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BlogUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated
     */
    int insert(BlogUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated
     */
    ResBlogUser selectByPrimaryKey(String userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated
     */
    List<BlogUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table blog_user
     *
     * @mbg.generated
     */
   int updateByPrimaryKey(BlogUser record);

    /**
     * 查询用户信息以及角色
     * @param blogUser
     * @return
     */
    BlogUser selectByUserName(BlogUser blogUser);


    int insertInfo( UserEmp record);


    List<ResBlogUser> empList(@Param("deptId")String deptId,@Param("nickName")String nickName);
}