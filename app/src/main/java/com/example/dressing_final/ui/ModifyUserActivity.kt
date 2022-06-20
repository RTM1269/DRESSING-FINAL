package com.example.dressing_final.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dressing_final.MainActivity
import com.example.dressing_final.databinding.ActivityModifyUserBinding
import org.json.JSONObject

class ModifyUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityModifyUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fillTextBox()
        binding.btnModificar.setOnClickListener {
            modifyUser()
        }
        binding.btnCancelarMod.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun modifyUser() {
        val userId = 9
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/modifyUser"
        val params: MutableMap<String?, String?> = HashMap()
        params["userId"] = userId.toString()
        params["username"] = binding.editTextTextUserName.text.toString()
        params["password"] = binding.editTextTextPass.text.toString()
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
                Log.i("Respuesta json", response.toString())


            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)
    }

    private fun fillTextBox() {
        val userId = 9
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/user/$userId"
        val jsonObjectRequest = JsonObjectRequest(url, null,
            {
                    response ->
                Log.i("Respuesta json", response.toString() )
                binding.editTextTextUserName.hint = response.getJSONArray("result").getString(1).toString()
                binding.editTextTextPass.hint = response.getJSONArray("result").getString(2).toString()
                binding.editTextTextName.hint = response.getJSONArray("result").getString(3).toString()
                binding.editTextTextApe1.hint = response.getJSONArray("result").getString(4).toString()
                binding.editTextTextApe2.hint = response.getJSONArray("result").getString(5).toString()
                binding.editTextTextEdad.hint = response.getJSONArray("result").getString(6).toString()
                binding.editTextTextSexo.hint = response.getJSONArray("result").getString(7).toString()
                binding.editTextTextTelefono.hint = response.getJSONArray("result").getString(8).toString()
            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)

    }




}