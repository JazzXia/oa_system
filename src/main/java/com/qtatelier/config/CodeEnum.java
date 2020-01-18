package com.qtatelier.config;
/**
 * @description:
 * @author:JazzXia(kima_riiiiii)
 * @date:2019-12-07 11:12
 * @contact:jazzxiaw@qq.com & xia.weiwei163@163.com
 */
public enum CodeEnum {
    SUCCESS("操作成功", 200),
    SUCCESS_204("成功请求，没有获取到内容", 204),
    SUCCESS_304("成功请求，不符合业务逻辑，操作失败", 304),
    ERROR_400("请求校验失败", 400),
    ERROR_401("没有权限", 401),
    ERROR_403("权限校验失败", 403),
    ERROR_404("资源不存在", 404),
    ERROR_405("访问方式不合法", 405),
    ERROR_500("系统内部错误", 500),
    ERROR_501("添加部门失败",501),
    ERROR_502("删除部门失败",502);

    private String name;
    private int value;

    private CodeEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static String getName(int value) {
        CodeEnum[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            CodeEnum c = var1[var3];
            if (c.getValue() == value) {
                return c.name;
            }
        }

        return null;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
