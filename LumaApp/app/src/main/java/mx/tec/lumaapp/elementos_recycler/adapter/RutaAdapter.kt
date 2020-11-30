package mx.tec.lumaapp.elementos_recycler.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tec.lumaapp.R
import mx.tec.lumaapp.elementos_recycler.model.RutaModel
import mx.tec.lumaapp.info_ruta_pop

class RutaAdapter(
    private val context: Context,
    private val layout: Int,
    private val dataSource: List<RutaModel>,):RecyclerView.Adapter<RutaAdapter.ItemHolder>(){

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var onClickListener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = inflater.inflate(layout, parent, false)
        return ItemHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RutaAdapter.ItemHolder, position: Int) {
        val elemento: RutaModel = dataSource.get(position)

        holder.destino.text = elemento.destino
        holder.origen.text = elemento.origen
        val precio = "$${elemento.precio} MXN"
        holder.precio.text = precio
        holder.hora.text = elemento.hora

        holder.itemView.setOnClickListener {
            val i = Intent(context, info_ruta_pop::class.java)
            context.startActivity(i)

//            holder.dialog.setContentView(R.layout.info_ruta_pop)

//            val inicioTxt = holder.dialog.findViewById<TextView>(R.id.inicio_rutaTxt)
//            val destinoTxt = holder.dialog.findViewById<TextView>(R.id.destino_rutaTxt)
//            inicioTxt.setText(holder.origen.text)
//            destinoTxt.setText(holder.destino.text)
//
//            val window = holder.dialog.window;
//            window!!.setLayout(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class ItemHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val destino = itemView.findViewById<TextView>(R.id.destinoTxt)
        val origen = itemView.findViewById<TextView>(R.id.origenTxt)
        val precio = itemView.findViewById<TextView>(R.id.precioTxt)
        val hora = itemView.findViewById<TextView>(R.id.horaSalidaTxt)
    }
}

