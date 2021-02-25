package com.lhm.apppay.callback;

/**
 * @Description: 支付结果回调
 * @Author: jackbata
 * @CreateDate: 2021/2/20 3:08 PM
 */
public interface IPayResultCallback {

  /**
   * 支付成功
   */
  void onPaySuccess();

  /**
   * 支付取消
   */
  void onPayCancel();

  /**
   * 支付失败
   * @param code
   * @param message
   */
  void onPayFailed(int code,String message);
}
