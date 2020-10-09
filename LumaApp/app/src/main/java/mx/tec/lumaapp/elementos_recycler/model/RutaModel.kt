package mx.tec.lumaapp.elementos_recycler.model

import android.widget.ImageView
import android.widget.TextView
import mx.tec.lumaapp.R

data class RutaModel(
    val destino:String,
    val origen :String,
    val precio :String,
    val hora:String) {
}