package mx.tec.lumaapp.elementos_recycler.adapter

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import mx.tec.lumaapp.R
import mx.tec.lumaapp.elementos_recycler.model.CuponesModel
import mx.tec.lumaapp.elementos_recycler.model.RutaModel
import mx.tec.lumaapp.models.PriceModel

class CuponesAdapter (
    private val context: Context,
    private val layout: Int,
    private val dataSource: List<PriceModel>): RecyclerView.Adapter<CuponesAdapter.ItemHolder>(){

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private lateinit var onClickListener: View.OnClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = inflater.inflate(layout, parent, false)
        return ItemHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CuponesAdapter.ItemHolder, position: Int) {
        val elemento: PriceModel = dataSource.get(position)

        holder.tipo.text = elemento.category
        holder.imagen.setImageResource(R.drawable.regalo)
        val precio = "${elemento.points} €cos"
        holder.precio.text = precio
        holder.description.text = elemento.description

        holder.itemView.setOnClickListener {
            holder.dialog.setContentView(R.layout.canjeo_cupones)

            val window = holder.dialog.window;
            window!!.setLayout(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            );
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    class ItemHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tipo = itemView.findViewById<TextView>(R.id.txtTipo)
        val imagen = itemView.findViewById<ImageView>(R.id.imgImagen)
        val precio = itemView.findViewById<TextView>(R.id.txtPrecio)
        val description = itemView.findViewById<TextView>(R.id.txtDescription)
        val dialog = Dialog(itemView.context)
    }
}
