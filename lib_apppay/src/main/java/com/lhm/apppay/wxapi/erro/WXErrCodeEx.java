package com.lhm.apppay.wxapi.erro;

import android.text.TextUtils;
import com.lhm.apppay.wxapi.bean.WXPayInfo;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import java.util.HashMap;

public class WXErrCodeEx implements BaseResp.ErrCode {
    private static final HashMap<Integer, String> sErrorMap = new HashMap<>();

    public static final int CODE_UNSUPPORT = 1000;
    public static final int CODE_ILLEGAL_ARGURE = 1001;
    /**
     * 其他错误
     */
    public static final int CODE_OTHER_ERR = 4;

    public static final String MESSAGE_UNSUPPORT = "未安装微信或者微信版本太低";
    public static final String MESSAGE_ILLEGAL_ARGURE = "订单参数不合法";

    static {
        sErrorMap.put(CODE_UNSUPPORT, MESSAGE_UNSUPPORT);
        sErrorMap.put(CODE_ILLEGAL_ARGURE, MESSAGE_ILLEGAL_ARGURE);
    }

    public static String getMessageByCode(int code) {
        return sErrorMap.get(code);
    }

    /**
     * 检查支付参数是否合法
     *
     * @return
     * @param payInfo
     */
    public static void checkPayInfo(WXPayInfo payInfo) throws PayException {
        if (payInfo == null) {
            throw new PayException("支付信息不能为空");
        }
        if (TextUtils.isEmpty(payInfo.getAppid())) {
            throw new PayException("应用id不能为空");
        }
        if (TextUtils.isEmpty(payInfo.getPartnerid())) {
            throw new PayException("商户号partnerid不能为空");
        }
        if (TextUtils.isEmpty(payInfo.getPrepayId())) {
            throw new PayException("预支付交易会话PrepayId不能为空");
        }
        if (TextUtils.isEmpty(payInfo.getPackageValue())) {
            throw new PayException("订单详情扩展字符串PackageValue不能为空");
        }
        if (TextUtils.isEmpty(payInfo.getNonceStr())) {
            throw new PayException("随机字符串noncestr不能为空");
        }
        if (TextUtils.isEmpty(payInfo.getTimestamp())) {
            throw new PayException("时间戳Timestamp不能为空");
        }
        if (TextUtils.isEmpty(payInfo.getSign())) {
            throw new PayException("签名Sign不能为空");
        }

    }

    /**
     *检查微信客户端状态
     * @param mWXApi
     * @throws PayException
     */
    public static void checkWechatState(IWXAPI mWXApi) throws PayException {
        if (mWXApi==null){
            throw new PayException("WXApi初始化失败，请稍后重试");
        }
        if (!mWXApi.isWXAppInstalled()){
            throw new PayException("未安装微信客户端");
        }
        if (!(mWXApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT)){
            throw new PayException("微信版本太低");
        }
    }


    }
