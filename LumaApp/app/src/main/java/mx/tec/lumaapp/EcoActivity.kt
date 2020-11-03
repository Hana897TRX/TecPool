package mx.tec.lumaapp

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import mx.tec.lumaapp.elementos_recycler.adapter.CuponesAdapter
import mx.tec.lumaapp.elementos_recycler.model.CuponesModel

class EcoActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val EcoPts = view?.findViewById<TextView>(R.id.txtPuntosTotales)

        val view = inflater.inflate(R.layout.eco_puntos_layout, container, false)

        val cupones = arrayListOf<CuponesModel>(
            CuponesModel("Comida", 100, R.drawable.regalo),
            CuponesModel("Diversion", 150, R.drawable.regalo),
            CuponesModel("???", 200, R.drawable.regalo),
        )

        val adaptador = CuponesAdapter(this.requireContext(), R.layout.cupones_layout, cupones)

        val rvCupones = view.findViewById<RecyclerView>(R.id.rvCupones)
        rvCupones.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        rvCupones.setHasFixedSize(true)
        rvCupones.adapter = adaptador

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val bottomNavigationView = this.activity?.findViewById<BottomNavigationView>(R.id.nav_bar)
//        bottomNavigationView!!.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.ridesActivity -> {
//                    findNavController().navigate(R.id.action_ecoActivity_to_ridesActivity)
//                    true
//                }
//                R.id.homeActivity -> {
//                    findNavController().navigate(R.id.action_ecoActivity_to_homeActivity)
//                    true
//                }
//                R.id.perfilActivity -> {
//                    findNavController().navigate(R.id.action_ecoActivity_to_perfilActivity)
//                    true
//                }
//                else -> false
//            }
//        }
    }
}