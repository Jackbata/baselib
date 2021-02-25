package com.lhm.apppay.wxapi;

import android.app.Activity;
import android.content.Context;
import com.lhm.apppay.base.IBasePayStrategy;
import com.lhm.apppay.callback.IPayResultCallback;
import com.lhm.apppay.wxapi.bean.WXPayInfo;
import com.lhm.apppay.wxapi.erro.PayException;
import com.lhm.apppay.wxapi.erro.WXErrCodeEx;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @Description: 微信支付入口
 * @Author: jackbata
 * @CreateDate: 2021/2/20 3:26 PM
 */
public class WXPayStrategy implements IBasePayStrategy<WXPayInfo> {

  private IWXAPI mWXApi;
  /**
   * 是否已经初始化
   */
  private boolean initialized;

  /**
   * 支付信息
   */
  private WXPayInfo mPayInfo;

  private static IPayResultCallback mPayResultCallback;
  private WXPayStrategy() {

  }
  private static class WXPayStrategyInstance{
    private static final WXPayStrategy instance=new WXPayStrategy();
  }

  public static WXPayStrategy getInstance() {
    return WXPayStrategyInstance.instance;
  }

  public IWXAPI getWXApi() {
    return mWXApi;
  }

  private void initWXApi(Context context, String appId) {
    mWXApi = WXAPIFactory.createWXAPI(context.getApplicationContext(), appId);
    mWXApi.registerApp(appId);
    initialized = true;
  }

  @Override
  public void pay(Activity activity, WXPayInfo payInfo, IPayResultCallback payCallback) {
    this.mPayInfo = payInfo;
    mPayResultCallback = payCallback;
    if (payCallback == null) {
      return;
    }

    try {
      WXErrCodeEx.checkPayInfo(payInfo);

      if (!initialized) {
        initWXApi(activity.getApplicationContext(), mPayInfo.getAppid());
      }

      WXErrCodeEx.checkWechatState(mWXApi);

      PayReq req = new PayReq();
      req.appId = mPayInfo.getAppid();
      req.partnerId = mPayInfo.getPartnerid();
      req.prepayId = mPayInfo.getPrepayId();
      req.packageValue = mPayInfo.getPackageValue();
      req.nonceStr = mPayInfo.getNonceStr();
      req.timeStamp = mPayInfo.getTimestamp();
      req.sign = mPayInfo.getSign();

      mWXApi.sendReq(req);

    } catch (PayException e) {
      payCallback.onPayFailed(WXErrCodeEx.CODE_OTHER_ERR, e.getMessage());
      mPayResultCallback = null;
    }
  }


  /**
   * 支付回调响应
   */
  public void onResp(int errorCode, String errorMsg) {
    if (mPayResultCallback == null) {
      return;
    }

    if (errorCode == BaseResp.ErrCode.ERR_OK) {
      mPayResultCallback.onPaySuccess();
    } else if (errorCode == BaseResp.ErrCode.ERR_COMM) {
      mPayResultCallback.onPayFailed(errorCode, errorMsg);
    } else if (errorCode == BaseResp.ErrCode.ERR_USER_CANCEL) {
      mPayResultCallback.onPayCancel();
    } else {
      mPayResultCallback.onPayFailed(errorCode, errorMsg);
    }

    mPayResultCallback = null;
  }

}
