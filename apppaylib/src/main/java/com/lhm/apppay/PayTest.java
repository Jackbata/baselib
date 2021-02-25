package com.lhm.apppay;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.lhm.apppay.alipay.AliPayInfo;
import com.lhm.apppay.alipay.AliPayStrategy;
import com.lhm.apppay.callback.IPayResultCallback;
import com.lhm.apppay.wxapi.WXPayStrategy;
import com.lhm.apppay.wxapi.bean.WXPayInfo;
import com.lhm.apppay.wxapi.bean.WXPayInfo.Builder;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @Description: 支付sdk使用测试用例
 * @Author: jackbata
 * @CreateDate: 2021/2/20 11:05 AM
 */
public class PayTest {

  //测试库是否可以正常调用
  public void testlib(String content, Context context){
    Toast.makeText(context,content+"哈哈",Toast.LENGTH_SHORT).show();
  }

  /**
   * 测试支付公共
   * payType 支付类型
   */
  public void testPaySdk(Activity activity){
    wechatPay(activity);
//        aliPay(activity);
  }

  private void wechatPay(Activity activity) {
    WXPayInfo build = new Builder()
        .wechatAppID("wx1ac6eeb1d35759d5")
        .partnerid("1245079902")
        .prepayId("wx201708061553336b1ec3ed250322753607")
        .nonceStr("7e230522657ecdc50e4249581b861f8e")
        .timestamp("1502006012")
        .packageValue("Sign=WXPay")
        .sign("00601875DF4DF40374817F18CD7AD17F")
        .build();

    wxpay(activity, build);

  }



  private void wxpay(Activity activity, WXPayInfo build) {
    AppPayUtil.pay(WXPayStrategy.getInstance(),activity,build,new IPayResultCallback(){

           @Override
           public void onPaySuccess() {
             Toast.makeText(activity.getApplicationContext(),"支付成功",Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onPayCancel() {
             Toast.makeText(activity.getApplicationContext(),"支付取消",Toast.LENGTH_SHORT).show();

           }

           @Override
           public void onPayFailed(int code, String message) {
             Toast.makeText(activity.getApplicationContext(),"支付失败，code="+code+"；message="+message,Toast.LENGTH_SHORT).show();
           }
         });
  }

  private void aliPay(Activity activity) {
    AliPayInfo aliPayInfo = new AliPayInfo();
    aliPayInfo.setOrderInfo("alipay_sdk=alipay-sdk-java-3.6.0.ALL&app_auth_token=202004BBcd0b047aaf1f40a89fcda17e6cb7dX91&app_id=2021002127623398&biz_content=%7B%22extend_params%22%3A%7B%22sys_service_provider_id%22%3A%222088731062544095%22%7D%2C%22out_trade_no%22%3A%22M10022021020716055159921090%22%2C%22total_amount%22%3A%22398.00%22%2C%22subject%22%3A%22%E5%BC%A0%E5%BF%A0%E7%82%9C+%E9%AB%98%E4%B8%89%EF%BC%8C%E9%AB%98%E4%B8%AD%E8%8B%B1%E8%AF%AD%EF%BC%8C%E4%B8%80%E5%AF%B9%E4%B8%80%2C%E9%AB%98%E4%B8%89%EF%BC%8C%E9%AB%98%E4%B8%AD%E7%89%A9%E7%90%86%EF%BC%8C%E4%B8%80%E5%AF%B9%E4%B8%80%2C%22%2C%22timeout_express%22%3A%2210m%22%2C%22body%22%3A%22%E5%BC%A0%E5%BF%A0%E7%82%9C+%E9%AB%98%E4%B8%89%EF%BC%8C%E9%AB%98%E4%B8%AD%E8%8B%B1%E8%AF%AD%EF%BC%8C%E4%B8%80%E5%AF%B9%E4%B8%80%2C%E9%AB%98%E4%B8%89%EF%BC%8C%E9%AB%98%E4%B8%AD%E7%89%A9%E7%90%86%EF%BC%8C%E4%B8%80%E5%AF%B9%E4%B8%80%2C%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fapi.opuscluster.com%2Fzfb%2Fcharge&sign=GI9yXDOvR%2FwI1x0DfCD%2FFFbf6xIW5zaJ8ygfHEwmCAcXI2HVRDVDMW372%2BYV%2FB4Dz4EsNkrm8E2n2p9BKf4EYdFBEXC%2FJFuNycW3H4Kv33ngkjkMuOVrKvlFsT56k0jlpNnUcy10XryTvqjuOXp31mU8TFEvRpbZI4niLhKhjBRcTC9ZRQWMJE7AMk0hkr4bLx9WTyJxFAj1qyH%2Bp0eY2q8F5weRq5bhIBG6lUEgGxcRvPW4m8VnwJhgAsqEbT6mYzjPOAd1dxE2BDiVYJETFx7Z4gnt%2BAmLOq26R8b6MvqOMKY2QvuTEc5EaIzPqi5O8id6ee3XB7TiiV%2B5SZVA5Q%3D%3D&sign_type=RSA2&timestamp=2021-02-23+16%3A05%3A51&version=1.0");
    startalipay(activity, aliPayInfo);
  }
  
  private void startalipay(Activity activity, AliPayInfo aliPayInfo) {
    AppPayUtil.pay(new AliPayStrategy(), activity, aliPayInfo, new IPayResultCallback() {
      @Override
      public void onPaySuccess() {
        Toast.makeText(activity.getApplicationContext(),"支付成功",Toast.LENGTH_SHORT).show();
      }

      @Override
      public void onPayCancel() {
        Toast.makeText(activity.getApplicationContext(),"支付取消",Toast.LENGTH_SHORT).show();

      }

      @Override
      public void onPayFailed(int code, String message) {
        Toast.makeText(activity.getApplicationContext(),"支付失败，code="+code+"；message="+message,Toast.LENGTH_SHORT).show();
      }
    });
  }


  /**
   * 测试课程推荐支付使用
   * @param activity
   * @param type
   * @param params
   */
  public void testPushCoursePaySdk(Activity activity,int type,String params){
    switch (type){
      case 1:
        wechatPay(activity,params);
        break;
      case 2:
        aliPay(activity,params);
        break;
    }
  }
  //payInfo 包含支付信息的json串
  private void wechatPay(Activity activity,String payInfo) {
    try {
      JSONObject info = new JSONObject(payInfo);
      WXPayInfo build = new Builder()
          .wechatAppID(info.optString("appId"))
          .partnerid(info.optString("partnerid"))
          .prepayId(info.optString("prepay_id"))
          .nonceStr(info.optString("nonceStr"))
          .timestamp(info.optString("timeStamp"))
          .packageValue(info.optString("package"))
          .sign(info.optString("sign"))
          .build();
      wxpay(activity, build);

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
  private void aliPay(Activity activity,String payinfo) {
    try {
      JSONObject info = new JSONObject(payinfo);
      AliPayInfo aliPayInfo = new AliPayInfo();
      aliPayInfo.setOrderInfo(info.optString("body"));
      startalipay(activity, aliPayInfo);

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
