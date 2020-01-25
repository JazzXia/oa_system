package com.qtatelier.OASystem.request;

import lombok.Data;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-24 18:37
 */
@Data
public class CustomerReq {
    private String startTime;
    private String endTime;
    private String customerName;
    private String userId;
    private String load;
}
