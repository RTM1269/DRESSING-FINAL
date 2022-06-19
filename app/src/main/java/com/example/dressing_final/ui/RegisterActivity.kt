package com.example.dressing_final.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.android.volley.toolbox.Volley
import com.example.dressing_final.R
import com.example.dressing_final.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    fun registerClick(view: View){
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/verifyLogin/"
    }
}