package com.lhm.apppay.base;

import android.app.Activity;
import com.lhm.apppay.callback.IPayResultCallback;

/**
 * @Description: 策略模式
 * @Author: jackbata
 * @CreateDate: 2021/2/20 3:10 PM
 */
public interface IBasePayStrategy<T extends IBasePayInfo> {

  /**
   * 发起支付
   * @param activity
   * @param payInfo 支付信息
   * @param payResultCallback 支付结果回调
   */
  void pay(Activity activity, T payInfo, IPayResultCallback payResultCallback);
}
