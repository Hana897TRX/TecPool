package mx.tec.lumaapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.perfil_layout.*
import kotlinx.android.synthetic.main.register_layout.*
import mx.tec.lumaapp.Utility.EnvSettings

class PerfilFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.perfil_layout, container, false)

        val sharedPreferences = view.context.getSharedPreferences(EnvSettings.getSPName(), Context.MODE_PRIVATE)

        val nombreTxt = view.findViewById<TextView>(R.id.usuarioTxt)
        val carreraTxt = view.findViewById<TextView>(R.id.carreraTxt)
        val telefonoTxt = view.findViewById<TextView>(R.id.telefonoTxt)
        val edadTxt = view.findViewById<TextView>(R.id.edadTxt)
        val ecosTxt = view.findViewById<TextView>(R.id.ecopuntosTxt)

        val nombre = sharedPreferences!!.getString(EnvSettings.getUserName(), "Invitado")
        val carrera = sharedPreferences!!.getString("carrera", "ITC")
        val telefono = sharedPreferences!!.getString("telefono", "777 341 2411")
        val edad = sharedPreferences!!.getInt("edad", 20)
        val ecos = sharedPreferences!!.getInt(EnvSettings.getEcoPuntos(), 0)

        nombreTxt.text = nombre.toString()
        telefonoTxt.text = String.format("Teléfono: " + telefono.toString())
        carreraTxt.text = String.format("Carrera: " + carrera.toString())
        edadTxt.text = String.format(
            getString(R.string.edad_inicio) + " " + edad.toString() + " " + getString(R.string.edad_final)
        )
        ecosTxt.text = ecos.toString()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cerrarBtn = view.findViewById<TextView>(R.id.cerrar_sesionBtn)

        cerrarBtn.setOnClickListener {
            val dialogBuilder = AlertDialog.Builder(this.requireContext())
            dialogBuilder.setMessage("¿Deseas cerrar sesión?")
                .setCancelable(false)
                .setPositiveButton("Cerrar") { dialogInterface, which ->

                    val sharedPreferences = it.context.getSharedPreferences(EnvSettings.getSPName(), Context.MODE_PRIVATE)
                    with(sharedPreferences!!.edit()) {
                        putString(EnvSettings.getUserName(), "null")
                        putInt(EnvSettings.getEcoPuntos(), 0)
                        apply()
                    }

                    activity!!.finish()

//                    val intent = Intent(it.context, LumaHomeActivity::class.java)
//                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//                    startActivity(intent)
                }
                .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

            val alert = dialogBuilder.create()
            alert.setTitle("Activar cuenta")
            alert.show()
        }
    }
}