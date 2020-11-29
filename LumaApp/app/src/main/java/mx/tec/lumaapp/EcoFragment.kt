package mx.tec.lumaapp

import android.Manifest
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.tec.lumaapp.Retrofit.IPrizeService
import mx.tec.lumaapp.Utility.EnvSettings
import mx.tec.lumaapp.elementos_recycler.adapter.CuponesAdapter
import mx.tec.lumaapp.elementos_recycler.model.CuponesModel
import mx.tec.lumaapp.models.PriceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EcoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.eco_puntos_layout, container, false)

        // Default values
        val cupones = arrayListOf<CuponesModel>(
            CuponesModel("Comida", 100, R.drawable.regalo),
            CuponesModel("Diversion", 150, R.drawable.regalo),
            CuponesModel("???", 200, R.drawable.regalo),)

        val adaptador = CuponesAdapter(activity!!.applicationContext, R.layout.cupones_layout, cupones)

        val rvCupones = view.findViewById<RecyclerView>(R.id.rvCupones)
        rvCupones.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        rvCupones.setHasFixedSize(true)
        rvCupones.adapter = adaptador

        // Inflate the layout for this fragment

        val EcoPts = view?.findViewById<TextView>(R.id.txtPuntosTotales)

        val retrofit : Retrofit = Retrofit.Builder().baseUrl(EnvSettings.getDbAddress())
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .build()

        val service = retrofit.create<IPrizeService>(IPrizeService::class.java)


        service.getPrizes().enqueue(object : Callback<List<PriceModel>>{
            override fun onResponse(call: Call<List<PriceModel>>, response: Response<List<PriceModel>>) {
                val cuponesModern = response.body()

                Toast.makeText(view.context, cuponesModern.toString(), Toast.LENGTH_LONG)
                    .show()

                var list = arrayListOf<CuponesModel>()

                for(i in 0 until cuponesModern!!.size){
                    list.add(CuponesModel(cuponesModern[i].category, cuponesModern[i].points, R.drawable.regalo))
                }

                val adaptador = CuponesAdapter(view.context, R.layout.cupones_layout, list)

                val rvCupones = view.findViewById<RecyclerView>(R.id.rvCupones)
                rvCupones.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                rvCupones.setHasFixedSize(true)
                rvCupones.adapter = adaptador
            }

            override fun onFailure(call: Call<List<PriceModel>>, t: Throwable) {
                Toast.makeText(view.context, t.toString(), Toast.LENGTH_LONG)
                    .show()
            }
        })

//        val cupones = arrayListOf<CuponesModel>(
//            CuponesModel("Comida", 100, R.drawable.regalo),
//            CuponesModel("Diversion", 150, R.drawable.regalo),
//            CuponesModel("???", 200, R.drawable.regalo),
//        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}