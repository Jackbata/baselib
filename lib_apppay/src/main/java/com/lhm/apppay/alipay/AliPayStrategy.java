package com.lhm.apppay.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.alipay.sdk.app.PayTask;
import com.lhm.apppay.base.IBasePayStrategy;
import com.lhm.apppay.callback.IPayResultCallback;
import java.util.Map;

/**
 * @Description: 支付宝支付
 * @Author: jackbata
 * @CreateDate: 2021/2/23 11:27 AM
 */
public class AliPayStrategy implements IBasePayStrategy<AliPayInfo> {
  private static final int SDK_PAY_FLAG = 1;
  private Activity mActivity;
  private AliPayInfo mAlipayInfo;
  private static IPayResultCallback mPayCallback;
  
  @Override
  public void pay(Activity activity, AliPayInfo payInfo, IPayResultCallback payResultCallback) {
    this.mActivity = activity;
    this.mAlipayInfo = payInfo;
    mPayCallback = payResultCallback;
    Runnable payRunnable = new Runnable() {
      @Override
      public void run() {
        // 构造PayTask 对象
        PayTask alipay = new PayTask(mActivity);
        // 调用支付接口，获取支付结果
        Map<String,String> result = alipay.payV2(mAlipayInfo.getOrderInfo(),true);
        Message msg = new Message();
        msg.what = SDK_PAY_FLAG;
        msg.obj = result;
        mHandler.sendMessage(msg);
      }
    };
    // 必须异步调用
    Thread payThread = new Thread(payRunnable);
    payThread.start();
  }


  @SuppressLint("HandlerLeak")
  private static Handler mHandler = new Handler() {
    @SuppressWarnings("unused")
    @Override
    public void handleMessage(Message msg) {
      switch (msg.what) {
        case SDK_PAY_FLAG: {
          AliPayResult payResult = new AliPayResult((Map<String, String>) msg.obj);
          Log.d("alipay",payResult.toString());
          /**
           * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
           * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
           * docType=1) 建议商户依赖异步通知
           */
          String resultInfo = payResult.getResult();// 同步返回需要验证的信息

          String resultStatus = payResult.getResultStatus();
          // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档:
          //https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.IXE2Zj&treeId=59&articleId=103671&docType=1
          if (TextUtils.equals(resultStatus, AliResultCode.CODE_SUCCESS)) {
            if (mPayCallback != null) {
              mPayCallback.onPaySuccess();
            }
          } else if(TextUtils.equals(resultStatus, AliResultCode.CODE_CANCEL)){
            if(mPayCallback != null){
              mPayCallback.onPayCancel();
            }
          } else {
            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
            if (mPayCallback != null) {
              mPayCallback.onPayFailed(AliResultCode.getIntCodeByString(resultStatus), AliResultCode.getTextByCode(resultStatus));
            }
          }
          break;
        }
        default:
          break;
      }
    }
  };
}
