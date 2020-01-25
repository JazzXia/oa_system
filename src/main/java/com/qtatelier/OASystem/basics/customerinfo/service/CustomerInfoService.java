package com.qtatelier.OASystem.basics.customerinfo.service;

import com.qtatelier.OASystem.basics.customerinfo.mapper.CustomerInfoMapper;
import com.qtatelier.OASystem.basics.customerinfo.model.CustomerInfo;
import com.qtatelier.OASystem.request.CustomerReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * @description 查询出所有信息
     * @param customerReq
     * @return
     */
    public List<CustomerInfo> findAll(CustomerReq customerReq){
        List<CustomerInfo> list = customerInfoMapper.selectByExampleInfo(customerReq);
        return list;
    }
}
