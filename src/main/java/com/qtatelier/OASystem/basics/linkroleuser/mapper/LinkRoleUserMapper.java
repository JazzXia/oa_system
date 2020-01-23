package com.qtatelier.OASystem.basics.linkroleuser.mapper;


import com.qtatelier.OASystem.basics.linkroleuser.model.LinkRoleUser;
import com.qtatelier.OASystem.request.UserEmp;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LinkRoleUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_role_user
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    int deleteByPrimaryKey(String linkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_role_user
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    int insert(LinkRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_role_user
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    int insertSelective(LinkRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_role_user
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    LinkRoleUser selectByPrimaryKey(String linkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_role_user
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    int updateByPrimaryKeySelective(LinkRoleUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_role_user
     *
     * @mbggenerated Sun Jan 12 19:30:26 CST 2020
     */
    int updateByPrimaryKey(LinkRoleUser record);

    int insertInfo(UserEmp record);

    int updateRoleId(LinkRoleUser linkRoleUser);
}