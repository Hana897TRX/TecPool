package mx.tec.lumaapp

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import mx.tec.lumaapp.elementos_recycler.adapter.RutaAdapter
import mx.tec.lumaapp.elementos_recycler.model.RutaModel

class RidesActivity : Fragment() {
    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.rides_layout, container, false)

        val rides_mostrados = arrayListOf<RutaModel>(
            RutaModel("Buena Vista", "Tec CVA", 40.00f, "9:00 am"),
            RutaModel("Temixco Centro", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Tec CVA", "Galerias CVA", 80.00f, "4:00 pm"),
            RutaModel("Buena Vistadddddddddddddddddd", "Tec CVA", 40.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am"),
            RutaModel("Buena Vista", "Buena Vista", 10.00f, "9:00 am")
        )

        val adapter = RutaAdapter(this.requireContext(), R.layout.rutas_layout, rides_mostrados)
        val rV = view.findViewById<RecyclerView>(R.id.recycler_rides)

        rV.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rV.setHasFixedSize(true)
        rV.adapter = adapter

//        val divider = this.requireContext().getDrawable(R.drawable.recycler_divider)
//
//        val dividerItemDecoration= DividerItemDecoration(this.requireContext(),DividerItemDecoration.VERTICAL)
//        dividerItemDecoration.setDrawable(divider!!)
//        rV.addItemDecoration(dividerItemDecoration)


        return view
    }

    fun Mostrar_RutaPopUp(view: View){
        val dialog = Dialog(this.requireContext())
        dialog.setContentView(R.layout.info_ruta_pop)

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val bottomNavigationView = this.activity?.findViewById<BottomNavigationView>(R.id.nav_bar)
//        bottomNavigationView!!.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.ecoActivity -> {
//                    findNavController().navigate(R.id.action_ridesActivity_to_ecoActivity)
//                    true
//                }
//                R.id.homeActivity -> {
//                    findNavController().navigate(R.id.action_ridesActivity_to_homeActivity)
//                    true
//                }
//                R.id.perfilActivity -> {
//                    findNavController().navigate(R.id.action_ridesActivity_to_perfilActivity)
//                    true
//                }
//                else -> false
//            }
//        }
    }
}