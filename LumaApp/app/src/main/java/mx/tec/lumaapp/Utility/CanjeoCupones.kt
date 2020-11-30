package mx.tec.lumaapp.Utility

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import mx.tec.lumaapp.R

class CanjeoCupones : Fragment() {

    private var cant = 1
    private var precio = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.canjeo_cupones, container, false)

        val agregarBtn = view.findViewById<ImageButton>(R.id.masBtn)
        val menosBtn = view.findViewById<ImageButton>(R.id.menosBtn)
        val cantidad  = view.findViewById<TextView>(R.id.cantidadTxt)
        val costo = view.findViewById<TextView>(R.id.costoTxt)

        cantidad.text = cant.toString()
        costo.text = "Total: $precio Ecopuntos"

        agregarBtn.setOnClickListener{
            cant++
            cantidad.text = cant.toString()
            precio = cant * 20

            costo.text = "Total: $precio Ecopuntos"
        }

        menosBtn.setOnClickListener {
            if (cant > 1) {
                cant--
                cantidad.text = cant.toString()
                precio = cant * 20
                costo.text = "Total: $precio Ecopuntos"
            }
        }

        return view
    }
}