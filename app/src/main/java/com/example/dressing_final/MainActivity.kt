package com.example.dressing_final
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dressing_final.databinding.ActivityMainBinding
import com.example.dressing_final.ui.ClosetsActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTopBar()
        setBottomNavBar()
    }

    private fun setTopBar() {
        toolbar = findViewById(R.id.topAppBar)
        setSupportActionBar(toolbar)
    }
    private fun setBottomNavBar() {
        //Obtenemos la barra de navegación
        val navView: BottomNavigationView = binding.navView
        //Obtenemos el fragment-parent o fragment-container.
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Configuramos la barra de navegación añadiendo las opciones desarrolladas en 'bottom_nav_menu.xml'
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        //Especificamos que cambie el nombre del ActionBar cuando cambiemos de fragmento.
        //setupActionBarWithNavController(navController, appBarConfiguration)
        //Especificamos a la barra de navegación nuestro navController.
        navView.setupWithNavController(navController)
    }


    //Métodos top-app-bar.
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.top_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*MenuID: 2131231107, PerfilId: 2131231064 */
        super.onOptionsItemSelected(item)
        Log.d("id pulsado", item.itemId.toString())
        if (item.itemId == R.id.profile){
            Log.d("RTM-PROFILE","click en perfil!!!!")
        }else{
            Log.d("RTM-MENÚ","click en menu!!!!")
            val intent = Intent(this, ClosetsActivity::class.java)
            startActivity(intent)
        }
        return true
    }

}