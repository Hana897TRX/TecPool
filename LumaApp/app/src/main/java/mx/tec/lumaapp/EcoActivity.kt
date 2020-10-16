package mx.tec.lumaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.eco_puntos_layout.*
import mx.tec.lumaapp.elementos_recycler.adapter.CuponesAdapter
import mx.tec.lumaapp.elementos_recycler.model.CuponesModel

class EcoActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.eco_puntos_layout, container, false)

        val cupones = arrayListOf<CuponesModel>(
            CuponesModel("Comida", 100, R.drawable.regalo),
            CuponesModel("Entretenimiento", 150, R.drawable.regalo),
            CuponesModel("???", 200, R.drawable.regalo),
            )

        val adaptador = CuponesAdapter(this.requireContext(), R.layout.cupones_layout, cupones)

        val rvCupones = view.findViewById<RecyclerView>(R.id.rvCupones)
        rvCupones.layoutManager = StaggeredGridLayoutManager(3, GridLayoutManager.HORIZONTAL)
        rvCupones.setHasFixedSize(true)
        rvCupones.adapter = adaptador

        return view
    }
}