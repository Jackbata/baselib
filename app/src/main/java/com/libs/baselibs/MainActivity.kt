package com.libs.baselibs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.libs.baselibs.activity.PayDemoActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }
    private fun setListener() {
        //支付
        btn_pay.setOnClickListener {
            PayDemoActivity().startActivity(this@MainActivity)
        }
    }
}