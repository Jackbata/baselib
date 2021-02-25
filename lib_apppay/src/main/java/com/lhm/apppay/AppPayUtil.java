package com.lhm.apppay;

import android.app.Activity;
import com.lhm.apppay.base.IBasePayInfo;
import com.lhm.apppay.base.IBasePayStrategy;
import com.lhm.apppay.callback.IPayResultCallback;

/**
 * @Description: 发起支付入口类
 * @Author: jackbata
 * @CreateDate: 2021/2/20 2:45 PM
 */
public class AppPayUtil {

  /**
   * 发起支付
   * @param payWay 支付策略选择（支持：微信、支付宝）
   * @param mActivity
   * @param payInfo 调起支付客户端所需信息
   * @param callback 支付结果回调
   * @param <T>
   */
  public static <T extends IBasePayInfo> void pay(IBasePayStrategy<T> payWay, Activity mActivity, T payInfo, IPayResultCallback callback){
    payWay.pay(mActivity, payInfo, callback);
  }
}
