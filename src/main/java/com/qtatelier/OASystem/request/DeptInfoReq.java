package com.qtatelier.OASystem.request;

import lombok.Data;

import java.util.List;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-18 16:06
 */
@Data
public class DeptInfoReq {
    List<String> ids;

    @Override
    public String toString() {
        return "DeptInfoReq{" +
                "ids=" + ids +
                '}';
    }
}
