package com.qtatelier.OASystem.request;

import lombok.Data;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-25 13:27
 */
@Data
public class CustomerAddReq {

    private String customerName;

    private String customerContact;

    private String customerAddress;

    private String signDate;

    private String payFree;

    private String customerPhone;

    private String remark;

    private String ftp;

    private String author;

    private String suggestionOne;

    private String suggestionTwo;

    private String suggestionThree;

    private String userId;

}
