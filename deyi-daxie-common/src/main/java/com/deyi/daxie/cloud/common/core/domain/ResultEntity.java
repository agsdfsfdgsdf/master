package com.deyi.daxie.cloud.common.core.domain;

import com.deyi.daxie.cloud.common.constant.HttpStatus;

/**
 * Description:
 *
 * @author Chen Xu
 * @date 2023/6/1
 */
public class ResultEntity<T> {

    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultEntity data(T data){
        return new ResultEntity(this.code, this.msg, data);
    }

    public static ResultEntity error(){
        return new ResultEntity(HttpStatus.ERROR, "操作失败");
    }

    public static ResultEntity error(String msg){
        return new ResultEntity(HttpStatus.ERROR, msg);
    }

    public static <T> ResultEntity<T> success(T data){
        return new ResultEntity(HttpStatus.SUCCESS, "操作成功").data(data);
    }

    public static <T> ResultEntity<T> success(int code, T data){
        return new ResultEntity(code, "操作成功").data(data);
    }
}
