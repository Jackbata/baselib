package com.libs.baselibs.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lhm.apppay.AppPayUtil
import com.lhm.apppay.alipay.AliPayInfo
import com.lhm.apppay.alipay.AliPayStrategy
import com.lhm.apppay.callback.IPayResultCallback
import com.lhm.apppay.wxapi.WXPayStrategy
import com.lhm.apppay.wxapi.bean.WXPayInfo
import com.libs.baselibs.R
import kotlinx.android.synthetic.main.activity_apppay.*

/**
 * @Description:
 * @Author: 肖邦
 * @CreateDate: 2021/2/25 2:43 PM
 */
class PayDemoActivity : AppCompatActivity() {
    fun startActivity(context: Context?) {
        val intent = Intent(context, PayDemoActivity::class.java)
        context?.startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apppay)
        setListener()
    }

    private fun setListener() {
        btn_wx.setOnClickListener {
            val build = WXPayInfo.Builder()
                .wechatAppID("wx1ac...")
                .partnerid("124...")
                .prepayId("wx2017...")
                .nonceStr("7e23...")
                .timestamp("15...")
                .packageValue("Sign=WXPay")
                .sign("2340...")
                .build()
            AppPayUtil.pay(
                WXPayStrategy.getInstance(),
                this,
                build,
                object : IPayResultCallback {
                    override fun onPaySuccess() {
                        Toast.makeText(applicationContext, "支付成功", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onPayCancel() {
                        Toast.makeText(applicationContext, "支付取消", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onPayFailed(code: Int, message: String) {
                        Toast.makeText(
                            applicationContext,
                            "支付失败，code=$code；message=$message",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })

        }
        btn_alipay.setOnClickListener {
            val aliPayInfo = AliPayInfo()
            aliPayInfo.orderInfo ="服务器返回的订单信息"
            AppPayUtil.pay(AliPayStrategy(), this, aliPayInfo, object : IPayResultCallback {
                override fun onPaySuccess() {
                    Toast.makeText(applicationContext, "支付成功", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onPayCancel() {
                    Toast.makeText(applicationContext, "支付取消", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onPayFailed(code: Int, message: String) {
                    Toast.makeText(
                        applicationContext,
                        "支付失败，code=$code；message=$message",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            })
        }
    }
}