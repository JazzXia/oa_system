package com.qtatelier.OASystem.response;

import lombok.Data;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-25 20:57
 */
@Data
public class RevisitLogRes {
    private String revisitId;

    private String revisitLog;

    private String revisitDate;

    private String revisitTel;

    private String customerPhone;

    private String customerName;

    private String customerContact;

    private String customerAddress;
}
