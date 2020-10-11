package mx.tec.lumaapp

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}