package com.example.payumoneytest

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.payumoneytest.databinding.ActivityMainBinding
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity(),PaymentResultListener {

    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pay.setOnClickListener {
            val checkout = Checkout()
            checkout.setKeyID("rzp_test_7ON5e4kPw5zlrU")

            val sAmount: String = binding.amount.text.toString()

            val amount = (sAmount.toFloat() * 100).roundToInt()

            val jsonObject = JSONObject()
            try {

                jsonObject.put("name", "Kirti Jain")

                jsonObject.put("description", "Test payment")

                jsonObject.put("currency", "INR")

                jsonObject.put("amount", amount)

                jsonObject.put("prefill.contact", "8890024434")

                jsonObject.put("prefill.email", "kirti.jain@ubuy.com")

                checkout.open(this,jsonObject)

            }catch (e: JSONException){
                 e.printStackTrace()
            }

        }

    }

    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment is successful", Toast.LENGTH_SHORT).show()
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment is failed ", Toast.LENGTH_SHORT).show()
    }


}

