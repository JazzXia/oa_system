package com.qtatelier.OASystem.response;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-13 19:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResBlogUser {
    private String userId;
    private String username;
    private String password;
    private String sign;
    private String location;
    private String webUrl;
    private String callself;
    private String nickName;
    private String imageName;
    private String userEmail;
    private String roleType;
    private String roleTypeName;
    private String token;
    private String deptNo;
    private String deptName;
    private String empNo;
    private String deptId;
    private String dutyName;

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
