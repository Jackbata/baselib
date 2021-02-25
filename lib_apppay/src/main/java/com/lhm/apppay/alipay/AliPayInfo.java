package com.lhm.apppay.alipay;

import com.lhm.apppay.base.IBasePayInfo;

/**
 * @Description: 支付宝支付
 * @Author: jackbata
 * @CreateDate: 2021/2/23 11:28 AM
 */
public class AliPayInfo implements IBasePayInfo {

  /**
   * 订单信息
   */
  private String orderInfo;

  public String getOrderInfo() {
    return orderInfo;
  }

  public void setOrderInfo(String orderInfo) {
    this.orderInfo = orderInfo;
  }
}
