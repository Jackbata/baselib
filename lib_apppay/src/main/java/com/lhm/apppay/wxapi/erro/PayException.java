package com.lhm.apppay.wxapi.erro;

/**
 * @Description:  支付异常
 * @Author: jackbata
 * @CreateDate: 2021/2/22 5:12 PM
 */
public class PayException extends Exception{

  //错误码
  public int code;

  @Override
  public synchronized Throwable initCause(Throwable cause) {
    return super.initCause(cause);
  }

  //构造函数
  public PayException(String message){
    super(message);
  }

  //构造函数
  public PayException(String message,int errorCode){
    this(message);
    this.code = errorCode;
  }

}
