package com.example.dressing_final.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dressing_final.ClothesModel.ClosetsProvider
import com.example.dressing_final.MainActivity
import com.example.dressing_final.R
import com.example.dressing_final.adapters.ClosetAdapter
import com.example.dressing_final.databinding.ActivityClosetsBinding

class ClosetsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityClosetsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityClosetsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTopBar()
        setRecycler()
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