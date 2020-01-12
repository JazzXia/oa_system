package com.qtatelier.config;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="返回类型")
public class ResultView{
    @ApiModelProperty(
            value = "返回描述",
            required = true,
            position = 2
    )
    private String msg;
    @ApiModelProperty(
            value = "返回的状态码: 200是操作成功；其他值是错误",
            required = true,
            position = 1
    )
    private int code = 200;
    @ApiModelProperty(
            value = "返回的结果集",
            position = 3
    )
    private Object data;
    @ApiModelProperty(
            hidden = true
    )
    private String time;

    public ResultView() {
    }

    public ResultView(CodeEnum code) {
        this.code = code.getValue();
        this.msg = code.getName();
    }

    public ResultView(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultView(CodeEnum code, String msg) {
        this.code = code.getValue();
        this.msg = msg;
    }

    public ResultView(CodeEnum code, Object data) {
        this.code = code.getValue();
        this.msg = code.getName();
        this.data = data;
    }

    public ResultView(CodeEnum code, String msg, Object data) {
        this.code = code.getValue();
        this.msg = msg;
        this.data = data;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setCode(CodeEnum code) {
        this.code = code.getValue();
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTime() {
        return ToolTime.getNowStringByAllTime();
    }

    public String toString() {
        return JSONObject.toJSONString(this);
    }
}