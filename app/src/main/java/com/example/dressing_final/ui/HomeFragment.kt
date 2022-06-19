package com.example.dressing02.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.dressing_final.R
import com.example.dressing_final.databinding.FragmentHomeBinding
import com.example.dressing_final.ui.viewModel.HomeViewModel
import com.example.dressing_v00.adapters.OutFitAdapter
import com.example.dressing_v00.models.setModel.OutFitProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationServices.getFusedLocationProviderClient
import com.squareup.picasso.Picasso
import java.util.*
import android.widget.ArrayAdapter as ArrayAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of requireActivity() fragment.
 */
class HomeFragment : Fragment() {
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var cityN:String
    //Configuramos el Binding entre fragmentos.
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Declaramos la clase vista-modelo de nuestro fragment.
        val homeViewModel: HomeViewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
//Obtengo una instancia del cliente de proveedor de ubicación combinada.
        fusedLocationProviderClient = getFusedLocationProviderClient(requireActivity())
        checkPermissions()
        // Obtenemos el layout de la vista contenedora y la desplegamos.
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
       /* val textView: TextView = binding.textHome
        binding.textHome.movementMethod = ScrollingMovementMethod()

        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        setComponents()
        return root
    }

    private fun setComponents() {

        //Spinners
        val listStyles = listOf("Estilo informal", "Arreglado", "Deportivo")
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,listStyles)
        binding.spinnerStyle.adapter = adapter
        val listColors = listOf("Colores básicos", "Color principal", "Colores secundarios")
        val adapter2 = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,listColors)
        binding.spinnerColors.adapter = adapter2
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    //Métodos Weather.
    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            //Pido que los acepte.
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                1
            )
        } else {
            getLocationDevice()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    /**
     * Método que obtiene la ubicación a tiempo real.
     */
    private fun getLocationDevice() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            //Si no es null, guaradmos las coordenadas y obtenemos el tiempo real en esa ubicación.
            if (it != null) {
                // 'apply' es un tipo de 'scope-functions' que nos permite aplicar cambios al objeto que la haga referencia.
                it.apply {
                    val latitude = this.latitude
                    val longitude = this.longitude
                    Log.i("infoRTM", latitude.toString())
                    Log.i("infoRTM", longitude.toString())
                    getCityName(longitude, latitude)
                    //getCityName(-3.5314, 40.4228)
                    //getWeather(getCityName(longitude, latitude))
                    getWeather(getCityName(-3.5314, 40.4228))
                }
            }
        }
    }

    /**
     * Método que nos devuelve el nombre de una ciudad según las coordenadas que recibe.
     */
    private fun getCityName(longitude: Double, latitude: Double): String {
        var cityName = "Not found"
        val gcd = Geocoder(requireContext(), Locale.getDefault())
        try {
            val address: List<Address> = gcd.getFromLocation(latitude, longitude, 10)

            for (add in address) {
                var city = add?.locality
                var c = add?.countryName
                if (city!! != "") {
                    cityName = city
                    cityN = city
                    Log.i("infoRTM", cityName + c)
                } else {
                    Log.d(
                        "DEbugsss", "city not FOUND"
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("Exception-GetCityName:", e.toString())
        }
        return cityName
    }

    /**
     * Método que nos devuelve el tiempo, según la ciudad recibida.
     */
    private fun getWeather(city:String) {

        val queue = Volley.newRequestQueue(requireContext())
        val url =
            "http://api.weatherapi.com/v1/forecast.json?key=4b97fba8bdb44c2498e145727222405&q=$city&days=1&aqi=yes&alerts=yes"

        val jsonObjectRequest = JsonObjectRequest(url, null,
            {

                    response ->
                Log.i("Respuesta json", response.toString())
                Log.i("Respuesta json", response["location"].toString())
                Log.i("Respuesta json", response["current"].toString())
                Log.i("Respuesta json", response["forecast"].toString())
                /*val obj: ExampleJsonWeather? =
                    Gson().fromJson(response.toString(), ExampleJsonWeather::class.java)
                Log.i("Respuesta json2", obj?.location?.name.toString())*/
                Log.i("Respuesta json3", response.getJSONObject("location")["name"].toString())
                binding.tvLocation.text = response.getJSONObject("location")["name"].toString()//+", "+response.getJSONObject("location")["region"]
                binding.tvTemperature.text = response.getJSONObject("current")["temp_c"].toString()+"ºC"+" - "+response.getJSONObject("current").getJSONObject("condition")["text"]
                val conditionIcon = response.getJSONObject("current").getJSONObject("condition").getString("icon")
                Picasso.get().load("http://$conditionIcon").into(binding.ivIconTemp)

            },
            { error -> error.printStackTrace() })
        queue.add(jsonObjectRequest)

    }

}