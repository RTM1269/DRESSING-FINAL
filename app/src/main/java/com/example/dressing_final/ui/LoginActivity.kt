package com.example.dressing_final.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dressing_final.MainActivity
import com.example.dressing_final.databinding.ActivityLoginBinding
import org.json.JSONObject


class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/user"
        val params: MutableMap<String?, String?> = HashMap()
        params["username"] = "rtm"
        params["password"] ="1234"

        val parameters = JSONObject(params as Map<*, *>?)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url, parameters as JSONObject,
            {

                    response ->
                val r = response
                Log.d("pruebaa",r.toString())
            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)
    }

    fun loginClick(view: View){
        Log.d("UserName",binding.etusername.text.toString())
        Log.d("Password",binding.password.text.toString())
        val res = verifyLogin(binding.etusername.text.toString(),binding.password.text.toString())
       Log.d("Res", res.toString())

    }

    private fun verifyLogin(user: String, pass: String): Boolean {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/verifyLogin/$user&$pass"
        var res:Boolean = false
        var r2 = false
        val jsonObjectRequest = JsonObjectRequest(url, null,
            {

                    response ->
                val  r = response.getInt("result")
                Log.i("Respuesta json", response.toString() + r.toString())
                if (r==0){
                    Log.i("prue","Login correcto")
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Log.i("prue","Login incorrecto")
                }

            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)
        return res
    }

}