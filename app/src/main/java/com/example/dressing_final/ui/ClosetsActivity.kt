package com.example.dressing_final.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dressing_final.ClothesModel.Closet
import com.example.dressing_final.ClothesModel.ClosetsProvider
import com.example.dressing_final.MainActivity
import com.example.dressing_final.R
import com.example.dressing_final.adapters.ClosetAdapter
import com.example.dressing_final.databinding.ActivityClosetsBinding
import org.json.JSONArray
import org.json.JSONObject

class ClosetsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityClosetsBinding
    var numClosets: Int = 9
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTopBar()
        setRecycler()
        getTotalClosets()
        getClosets()
    }

    private fun getTotalClosets() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/countClosets"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url, null,
            {

                    response ->
                val r = response
                Log.d("pruebaa",r.toString())
                numClosets = r.getJSONArray("NArmarios").getJSONArray(0)[0] as Int
                Log.d("nClosets", numClosets.toString())
            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)
    }

    private fun getClosets() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.1.132:5000/closets"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,url, null,
            {

                    response ->
                val r = response
                Log.d("pruebaa",r.toString())
                Log.d("id",r.getJSONArray("Armarios").getJSONArray(0)[0].toString())
                var oldList = ClosetsProvider.list
                var newList = oldList
                newList = listOf(
                    Closet(r.getJSONArray("Armarios").getJSONArray(0)[0].toString(), r.getJSONArray("Armarios").getJSONArray(0)[1].toString(),r.getJSONArray("Armarios").getJSONArray(0)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"),Closet(r.getJSONArray("Armarios").getJSONArray(0)[0].toString(), r.getJSONArray("Armarios").getJSONArray(1)[1].toString(),r.getJSONArray("Armarios").getJSONArray(1)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"),Closet(r.getJSONArray("Armarios").getJSONArray(2)[0].toString(), r.getJSONArray("Armarios").getJSONArray(2)[1].toString(),r.getJSONArray("Armarios").getJSONArray(2)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"),Closet(r.getJSONArray("Armarios").getJSONArray(3)[0].toString(), r.getJSONArray("Armarios").getJSONArray(3)[1].toString(),r.getJSONArray("Armarios").getJSONArray(3)[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"))
                Log.d("newList", newList.toString())
                ClosetsProvider.list = newList
                ClosetAdapter(newList).notifyDataSetChanged()
                var i: Int = 0

                while (i<numClosets){
                    var res = r.getJSONArray("Armarios").getJSONArray(i)
                    newList = newList + listOf(Closet(res[0].toString(), res[1].toString(),res[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png"))
                    /*for (res in iterator<JSONArray> {r.getJSONArray("Armarios").getJSONArray(i)}){
                        newList = listOf(Closet(res[0].toString(), res[1].toString(),res[2].toString(),"https://vivetotalmentepalacio.mx/wp-content/uploads/2017/05/Principal-internaclosets.png")
                        )
                        Log.d("res", newList.toString())
                    }*/
                    i++
                }
                ClosetsProvider.list = newList
                ClosetAdapter(newList).notifyDataSetChanged()


            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)
    }

    private fun setTopBar() {
        toolbar = findViewById(R.id.topAppBar2)
        setSupportActionBar(toolbar)
    }

    private fun setRecycler() {
        val rv: RecyclerView = binding.rvCloset
        binding.rvCloset.layoutManager = GridLayoutManager(rv.context,2)
        binding.rvCloset.adapter = ClosetAdapter(ClosetsProvider.list)
    }
    fun addCloset(view: View){
        val intent = Intent(applicationContext,RegisterClosetActivity::class.java)
        startActivity(intent)
    }

    //Métodos top-app-bar.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*MenuID: 16908332, PerfilId: 2131231064 */
        super.onOptionsItemSelected(item)
        Log.d("id pulsado", item.itemId.toString())
        if (item.itemId==16908332){
            Log.d("RTM-MENÚ2","click en volver!!!!")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }else{
            Log.d("RTM-PROFILE2","click en perfil!!!!")
        }
        return true
    }
}