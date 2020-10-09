package mx.tec.lumaapp.elementos_recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tec.lumaapp.R
import mx.tec.lumaapp.elementos_recycler.model.RutaModel

class RutaAdapter(
    private val context: Context,
    private val layout: Int,
    private val dataSource: List<RutaModel>):RecyclerView.Adapter<RutaAdapter.ItemHolder>(){

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = inflater.inflate(R.layout.rutas_layout, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: RutaAdapter.ItemHolder, position: Int) {
        var elemento:RutaModel = dataSource.get(position)

        holder.destino.text = elemento.destino
        holder.origen.text = elemento.origen
        holder.precio.text = elemento.precio
        holder.hora.text = elemento.hora
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val destino = itemView.findViewById<TextView>(R.id.destinoTxt)
        val origen = itemView.findViewById<TextView>(R.id.destinoTxt)
        val precio = itemView.findViewById<TextView>(R.id.precioTxt)
        val hora = itemView.findViewById<TextView>(R.id.horaSalidaTxt)
    }
}

