package com.example.dressing_final.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dressing_final.ClothesModel.Closet
import com.example.dressing_final.ClothesModel.ClosetsProvider
import com.example.dressing_final.R
import com.example.dressing_final.adapters.ClosetAdapter
import com.example.dressing_final.databinding.ActivityRegisterClosetBinding
import org.json.JSONArray
import org.json.JSONObject

class RegisterClosetActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterClosetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterClosetBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun cancelRegisterClosetClick(view: View){
        val intent = Intent(applicationContext,ClosetsActivity::class.java)
        startActivity(intent)
    }
    fun guardarClosetClick(view: View){
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/closets"
        val params: MutableMap<String?, String?> = HashMap()
        params["name"] = binding.editTextTextClosetName.text.toString()
        params["location"] = binding.editTextTextPlaceName.text.toString()
        params["userID"] = "4"
        params["photo"] = ""

        val parameters = JSONObject(params as Map<*, *>?)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.POST,url, parameters,
            {
                    response ->
                val r = response
                Log.d("pruebaa",r.toString())
                Log.d("id",r.getJSONArray("Armarios").getJSONArray(0)[0].toString())
                var oldList = ClosetsProvider.list
                var newList = oldList
                newList = listOf(
                    Closet(r.getJSONArray("Armarios").getJSONArray(0)[0].toString(), r.getJSONArray("Armarios").getJSONArray(0)[1].toString(),r.getJSONArray("Armarios").getJSONArray(0)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"),
                    Closet(r.getJSONArray("Armarios").getJSONArray(0)[0].toString(), r.getJSONArray("Armarios").getJSONArray(1)[1].toString(),r.getJSONArray("Armarios").getJSONArray(1)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"),
                    Closet(r.getJSONArray("Armarios").getJSONArray(2)[0].toString(), r.getJSONArray("Armarios").getJSONArray(2)[1].toString(),r.getJSONArray("Armarios").getJSONArray(2)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"),
                    Closet(r.getJSONArray("Armarios").getJSONArray(3)[0].toString(), r.getJSONArray("Armarios").getJSONArray(3)[1].toString(),r.getJSONArray("Armarios").getJSONArray(3)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png")
                )
                Log.d("newList", newList.toString())
                ClosetsProvider.list = newList
                ClosetAdapter(newList).notifyDataSetChanged()
                var i: Int = 0
                while (i<9){
                    for (res in iterator<JSONArray> {r.getJSONArray("Armarios").getJSONArray(i)}){
                        newList = listOf(
                            Closet(res[0].toString(), res[1].toString(),res[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png")
                        )
                        Log.d("res", newList.toString())
                    }
                    i++
                }
                ClosetsProvider.list = newList
                ClosetAdapter(newList).notifyDataSetChanged()


            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)
    }
}