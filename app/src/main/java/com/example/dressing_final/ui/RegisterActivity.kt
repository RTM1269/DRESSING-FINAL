package com.example.dressing_final.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dressing_final.MainActivity
import com.example.dressing_final.R
import com.example.dressing_final.databinding.ActivityRegisterBinding
import org.json.JSONObject
import java.math.BigInteger
import java.nio.charset.StandardCharsets.UTF_8
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }
    fun cancelClick(view: View){
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
    fun registerClick(view: View){
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/user"
        val params: MutableMap<String?, String?> = HashMap()
        params["username"] = binding.editTextTextUserName.text.toString()
        params["password"] = md5Hash(binding.editTextTextPass.text.toString())
        params["nombre"] = binding.editTextTextName.text.toString()
        params["apellido1"] = binding.editTextTextApe1.text.toString()
        params["apellido2"] = binding.editTextTextApe2.text.toString()
        params["edad"] = binding.editTextTextEdad.text.toString()
        params["sexo"] = binding.editTextTextSexo.text.toString()
        params["telefono"] = binding.editTextTextTelefono.text.toString()

        val parameters = JSONObject(params as Map<*, *>?)
        val jsonObjectRequest = JsonObjectRequest(url, parameters,
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
    }


}