package org.example.todolistserver.general;

import lombok.Data;

/**
 * 响应结果返回类
 */
@Data
public class Result<T> {
    private Boolean success;
    private String message;
    private T data;

    /**
     * 私有化构造方法，禁止在其它类创建对象
     */
    private Result(){}

    /**
     * 成功执行，不返回数据
     * @return
     */
    public static<T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setMessage("success");
        return result;
    }

    /**
     * 成功执行，并返回数据
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> success(T data){
        Result<T> result = new Result<T>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setData(data);
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static<T> Result<T> error(){
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setMessage("error");
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static<T> Result<T> error(String message){
        Result<T> result = new Result<T>();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    /**
     * 设置是否成功，链式编程
     * @param success
     * @return
     */
    public Result<T> success(Boolean success){
        this.setSuccess(success);
        return this;
    }

    /**
     * 设置返回消息
     * @param message
     * @return
     */
    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
}