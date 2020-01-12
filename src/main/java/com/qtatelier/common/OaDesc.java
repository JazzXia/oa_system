package com.qtatelier.common;

/**
 * @author xiaweiwei
 * @descrpition
 * @email xiaww@redoornetwork.com(xia.weiwei163@163.com)
 * @qq 1104841692
 * @time 2020-01-12 13:26
 */
public enum OaDesc {

    /**
     * 员工编号
     */
    EmpInfo(1,"YG"),
    /**
     * 部门编号
     */
    DeptInfo(2,"BM");

    private String desc;
    private int code;

    OaDesc(int code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public int getCode() {
        return code;
    }
}
