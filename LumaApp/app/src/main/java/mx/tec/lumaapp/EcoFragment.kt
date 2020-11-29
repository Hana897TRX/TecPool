package mx.tec.lumaapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import mx.tec.lumaapp.Retrofit.IPrizeService
import mx.tec.lumaapp.Utility.EnvSettings
import mx.tec.lumaapp.elementos_recycler.adapter.CuponesAdapter
import mx.tec.lumaapp.models.PriceModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EcoFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.eco_puntos_layout, container, false)

        val EcoPts = view?.findViewById<TextView>(R.id.txtPuntosTotales)

        val retrofit : Retrofit = Retrofit.Builder().baseUrl(EnvSettings.getDbAddress())
                                                    .addConverterFactory(GsonConverterFactory.create())
                                                    .build()

        val service = retrofit.create<IPrizeService>(IPrizeService::class.java)

        service.getPrizes().enqueue(object : Callback<List<PriceModel>>{
            override fun onResponse(call: Call<List<PriceModel>>, response: Response <List<PriceModel>>) {
                val cuponesModern = response.body()

                Toast.makeText(view.context, cuponesModern!!.toString(), Toast.LENGTH_LONG)
                    .show()

                val adaptador = CuponesAdapter(view.context, R.layout.cupones_layout, cuponesModern)

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

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}