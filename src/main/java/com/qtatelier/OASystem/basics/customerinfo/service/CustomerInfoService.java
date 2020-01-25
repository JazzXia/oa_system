package com.qtatelier.OASystem.basics.customerinfo.service;

import com.qtatelier.OASystem.basics.customerinfo.mapper.CustomerInfoMapper;
import com.qtatelier.OASystem.basics.customerinfo.model.CustomerInfo;
import com.qtatelier.OASystem.basics.link_user_customer.mapper.LinkUserCustomerMapper;
import com.qtatelier.OASystem.basics.link_user_customer.model.LinkUserCustomer;
import com.qtatelier.OASystem.request.CustomerAddReq;
import com.qtatelier.OASystem.request.CustomerReq;
import com.qtatelier.OASystem.thread.CustomerException;
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
 * @time 2020-01-24 18:53
 */
@Service
public class CustomerInfoService {
    @Autowired
    private CustomerInfoMapper customerInfoMapper;
    @Autowired
    private LinkUserCustomerMapper linkUserCustomerMapper;


    /**
     * @param customerReq
     * @return
     * @description 查询出所有信息
     */
    public List<CustomerInfo> findAll(CustomerReq customerReq) {
        List<CustomerInfo> list = customerInfoMapper.selectByExampleInfo(customerReq);
        return list;
    }


    //TODO 接下来为此系统添加邮件推送系统,获得新型冠状病毒最新信息【爬虫】
    //TODO 为此系统的主页中添加冠状病毒的直方图以及相关新闻更新【爬虫】

    /**
     * @return
     * @description 添加信息到关联表以及vip表
     */
    @Transactional(rollbackFor = Exception.class)
    public CodeEnum addCustomerInfo(CustomerAddReq customerAddReq) {
        CodeEnum codeEnum = CodeEnum.SUCCESS;
        if (null == customerAddReq) {
            throw new CustomerException("当前添加信息为空");
        }
        CustomerInfo customerInfo = new CustomerInfo();
        customerInfo.setAuthor(customerAddReq.getAuthor());
        String backNo = OaDesc.BackNo.getDesc() + ToolTime.getNowStringByAllTime();
        customerInfo.setBackNo(backNo);
        String customerId = ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase();
        customerInfo.setCustomerId(customerId);
        customerInfo.setContract(OaDesc.ContractNo.getDesc() + ToolTime.getNowStringByAllTime());
        customerInfo.setCustomerName(customerAddReq.getCustomerName());
        customerInfo.setCustomerContact(customerAddReq.getCustomerContact());
        customerInfo.setFtp(customerAddReq.getFtp());
        customerInfo.setLoad("1");
        customerInfo.setPayFree(customerAddReq.getPayFree());
        customerInfo.setCustomerPhone(customerAddReq.getCustomerPhone());
        customerInfo.setSignDate(customerAddReq.getSignDate());
        customerInfo.setCustomerAddress(customerAddReq.getCustomerAddress());
        customerInfo.setRemark(customerAddReq.getRemark());
        int isSuccess = customerInfoMapper.insertSelective(customerInfo);
        if (isSuccess < 1) {
            throw new CustomerException("添加失败!!");
        }
        LinkUserCustomer linkUserCustomer = new LinkUserCustomer();
        linkUserCustomer.setLinkId(ToolTime.getNowStringByAllTime() + ToolRandom.getStringByLen(6).toUpperCase());
        linkUserCustomer.setUserId(customerAddReq.getUserId());
        linkUserCustomer.setCustomerId(customerId);
        isSuccess = linkUserCustomerMapper.insertSelective(linkUserCustomer);
        if (isSuccess < 1) {
            throw new CustomerException("添加失败!!");
        }
        return codeEnum;
    }


    public CustomerInfo findInfoByKey(String customerId){
        if (StringUtils.isNotBlank(customerId)){
            return customerInfoMapper.selectByPrimaryKey(customerId);
        }
        throw new CustomerException("当前客户不存在");
    }
}
