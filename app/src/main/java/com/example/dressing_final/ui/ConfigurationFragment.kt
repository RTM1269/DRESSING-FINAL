package com.example.dressing_final.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.dressing02.ui.viewModel.ConfigurationViewModel
import com.example.dressing_final.MainActivity
import com.example.dressing_final.R
import com.example.dressing_final.databinding.ActivityClosetsBinding
import com.example.dressing_final.databinding.FragmentConfigurationBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConfigurationFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConfigurationFragment : Fragment() {

    lateinit var toolbar: Toolbar
    //Configuramos el Binding entre fragmentos.
    private var _binding: FragmentConfigurationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Obtenemos la clase vista-modelo de nuestro fragmento.
        val configViewModel: ConfigurationViewModel = ViewModelProvider(this).get(
            ConfigurationViewModel::class.java
        )

        // Obtenemos el layout de la vista contenedora y la desplegamos.
        _binding = FragmentConfigurationBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnCerrarSesion.setOnClickListener {
            val intent = Intent(requireActivity(), LoginActivity::class.java)
            startActivity(intent)
        }
        binding.cvAjustes.setOnClickListener {
            val intent = Intent(requireActivity(), ModifyUserActivity::class.java)
            startActivity(intent)
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun cerrarSesion(view: View) {
        val intent = Intent(MainActivity().baseContext, LoginActivity::class.java)
        startActivity(intent)
    }



}