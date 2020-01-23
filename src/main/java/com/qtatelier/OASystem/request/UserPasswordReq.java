package com.qtatelier.OASystem.request;

import lombok.Data;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-23 16:50
 */
@Data
public class UserPasswordReq {
    private String userName;
    private String prePassWord;
    private String newPassWord;
}
