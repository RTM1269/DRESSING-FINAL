package com.example.dressing02.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.dressing02.ui.viewModel.DashboardViewModel
import com.example.dressing_final.ClothesModel.ClothingDownProvider
import com.example.dressing_final.ClothesModel.ClothingMiddleProvider
import com.example.dressing_final.ClothesModel.ClothingTopProvider
import com.example.dressing_final.adapters.ClothingAdapter
import com.example.dressing_final.databinding.FragmentDashboardBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {
    //Configuramos el Binding entre fragmentos.
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Obtenemos la clase vista-modelo del fragmento
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        // Obtenemos el layout de la vista contenedora y la desplegamos.
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        // Obtenemos el texto del textView y lo observamos hasta que el ciclo de vida termina, es decir, hasta que se cambie de fragmento o se cierre la aplicación.
        /*val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/
        val rv: RecyclerView = binding.rvTop
       // binding.rvTop.layoutManager = LinearLayoutManager(rv.context)
        binding.rvTop.adapter = ClothingAdapter(ClothingTopProvider.setList)
        binding.rvMiddle.adapter = ClothingAdapter(ClothingMiddleProvider.list)
        binding.rvDown.adapter = ClothingAdapter(ClothingDownProvider.list)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}