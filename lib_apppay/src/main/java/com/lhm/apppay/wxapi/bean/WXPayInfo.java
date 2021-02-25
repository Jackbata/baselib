package com.lhm.apppay.wxapi.bean;

import com.lhm.apppay.base.IBasePayInfo;

/**
 * @Description: 微信支付信息
 * @Author: jackbata
 * @CreateDate: 2021/2/20 3:31 PM
 */
public class WXPayInfo implements IBasePayInfo {
/**
 * IWXAPI api;
 * PayReq request = new PayReq();
 * request.appId = "wxd930ea5d5a258f4f";
 * request.partnerId = "1900000109";
 * request.prepayId= "1101000000140415649af9fc314aa427",;
 * request.packageValue = "Sign=WXPay";
 * request.nonceStr= "1101000000140429eb40476f8896f4c9";
 * request.timeStamp= "1398746574";
 * request.sign= "oR9d8PuhnIc+YZ8cBHFCwfgpaK9gd7vaRvkYD7rthRAZ\/X+QBhcCYL21N7cHCTUxbQ+EAt6Uy+lwSN22f5YZvI45MLko8Pfso0jm46v5hqcVwrk6uddkGuT+Cdvu4WBqDzaDjnNa5UK3GfE1Wfl2gHxIIY5lLdUgWFts17D4WuolLLkiFZV+JSHMvH7eaLdT9N5GBovBwu5yYKUR7skR8Fu+LozcSqQixnlEZUfyE55feLOQTUYzLmR9pNtPbPsu6WVhbNHMS3Ss2+AehHvz+n64GDmXxbX++IOBvm2olHu3PsOUGRwhudhVf7UcGcunXt8cqNjKNqZLhLw4jq\/xDg==";
 * api.sendReq(request);
 */

  /**
   * 应用id
   */
  private String appid;
  /**
   * 商户号
   */
  private String partnerid;
  /**
   * 预支付交易会话ID
   */
  private String prepayId;
  /**
   * 订单详情扩展字符串
   */
  private String packageValue;
  /**
   * 随机字符串
   */
  private String nonceStr;
  /**
   * 时间戳
   */
  private String timestamp;

  /**
   * 签名
   */
  private String sign;


  public String getAppid() {
    return appid;
  }

  public void setAppid(String appid) {
    this.appid = appid;
  }

  public String getPartnerid() {
    return partnerid;
  }

  public void setPartnerid(String partnerid) {
    this.partnerid = partnerid;
  }

  public String getPrepayId() {
    return prepayId;
  }

  public void setPrepayId(String prepayId) {
    this.prepayId = prepayId;
  }

  public String getPackageValue() {
    return packageValue;
  }

  public void setPackageValue(String packageValue) {
    this.packageValue = packageValue;
  }

  public String getNonceStr() {
    return nonceStr;
  }

  public void setNonceStr(String nonceStr) {
    this.nonceStr = nonceStr;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }

  public static class Builder {

    /**
     * 应用id
     */
    private String mAppid;
    /**
     * 商户号
     */
    private String mPartnerid;
    /**
     * 预支付交易会话ID
     */
    private String mPrepayId;
    /**
     * 订单详情扩展字符串
     */
    private String mPackageValue;
    /**
     * 随机字符串
     */
    private String mNonceStr;
    /**
     * 时间戳
     */
    private String mTimestamp;

    /**
     * 签名
     */
    private String mSign;

    public Builder wechatAppID(String appid) {
      this.mAppid = appid;
      return this;
    }

    public Builder partnerid(String partnerid) {
      this.mPartnerid = partnerid;
      return this;
    }

    public Builder prepayId(String prepayId) {
      this.mPrepayId = prepayId;
      return this;
    }

    public Builder packageValue(String packageValue) {
      this.mPackageValue = packageValue;
      return this;
    }

    public Builder nonceStr(String nonceStr) {
      this.mNonceStr = nonceStr;
      return this;
    }

    public Builder timestamp(String timestamp) {
      this.mTimestamp = timestamp;
      return this;
    }

    public Builder sign(String sign) {
      this.mSign = sign;
      return this;
    }

    public WXPayInfo build() {
      WXPayInfo payInfo = new WXPayInfo();
      payInfo.appid = mAppid;
      payInfo.partnerid = mPartnerid;
      payInfo.prepayId = mPrepayId;
      payInfo.packageValue = mPackageValue;
      payInfo.nonceStr = mNonceStr;
      payInfo.timestamp = mTimestamp;
      payInfo.sign = mSign;
      return payInfo;
    }

  }
}
