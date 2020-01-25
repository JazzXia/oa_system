package com.qtatelier.OASystem.basics.link_customer_revisit.mapper;

import com.qtatelier.OASystem.basics.link_customer_revisit.model.LinkCustomerRevisit;
import com.qtatelier.OASystem.basics.link_customer_revisit.model.LinkCustomerRevisitExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LinkCustomerRevisitMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int countByExample(LinkCustomerRevisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int deleteByExample(LinkCustomerRevisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int deleteByPrimaryKey(String linkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int insert(LinkCustomerRevisit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int insertSelective(LinkCustomerRevisit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    List<LinkCustomerRevisit> selectByExample(LinkCustomerRevisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    LinkCustomerRevisit selectByPrimaryKey(String linkId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int updateByExampleSelective(@Param("record") LinkCustomerRevisit record, @Param("example") LinkCustomerRevisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int updateByExample(@Param("record") LinkCustomerRevisit record, @Param("example") LinkCustomerRevisitExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int updateByPrimaryKeySelective(LinkCustomerRevisit record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table link_customer_revisit
     *
     * @mbggenerated Fri Jan 24 10:27:51 CST 2020
     */
    int updateByPrimaryKey(LinkCustomerRevisit record);
}