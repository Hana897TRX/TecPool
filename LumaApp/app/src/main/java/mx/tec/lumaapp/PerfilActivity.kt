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

class PerfilActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.perfil_layout, container, false)

        val sharedPreferences =
            this.activity?.getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)

        val nombreTxt = view.findViewById<TextView>(R.id.usuarioTxt)
        val carreraTxt = view.findViewById<TextView>(R.id.carreraTxt)
        val telefonoTxt = view.findViewById<TextView>(R.id.telefonoTxt)
        val edadTxt = view.findViewById<TextView>(R.id.edadTxt)
        val ecosTxt = view.findViewById<TextView>(R.id.ecopuntosTxt)

        val nombre = sharedPreferences!!.getString("nombre", "null")
        val carrera = sharedPreferences!!.getString("carrera", "null")
        val telefono = sharedPreferences!!.getString("telefono", "null")
        val edad = sharedPreferences!!.getInt("edad", 0)
        val ecos = sharedPreferences!!.getInt("ecosAcumulados", 0)

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

//        val bottomNavigationView = this.activity?.findViewById<BottomNavigationView>(R.id.nav_bar)
//        bottomNavigationView!!.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.ecoActivity -> {
//                    findNavController().navigate(R.id.action_perfilActivity_to_ecoActivity)
//                    true
//                }
//                R.id.homeActivity -> {
//                    findNavController().navigate(R.id.action_perfilActivity_to_homeActivity)
//                    true
//                }
//                R.id.ridesActivity -> {
//                    findNavController().navigate(R.id.action_perfilActivity_to_ridesActivity)
//                    true
//                }
//                else -> false
//            }
//        }

        cerrarBtn.setOnClickListener {
//                    val sharedPreferences =
//                        this.activity?.getSharedPreferences(
//                            "informacion_usuario",
//                            Context.MODE_PRIVATE
//                        )
//                    with(sharedPreferences!!.edit()) {
//                        putString("nombre", "null")
//                        putString("user", "null")
//                        putString("password", "null")
//                        putString("telefono", "null")
//                        putString("carrera", "null")
//                        putInt("edad", 0)
//                        putInt("ecosAcumulados", 0)
//                        putInt("mantener", 0)
//                        commit()
//                    }
//
//            val intent = Intent(this.context, LumaHomeActivity::class.java)
//            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(intent)

            val dialogBuilder = AlertDialog.Builder(this.requireContext())
            dialogBuilder.setMessage("¿Deseas cerrar sesión?")
                .setCancelable(false)
                .setPositiveButton("Cerrar") { dialogInterface, which ->
                    val sharedPreferences =
                        this.activity?.getSharedPreferences(
                            "informacion_usuario",
                            Context.MODE_PRIVATE
                        )
                    with(sharedPreferences!!.edit()) {
                        putString("nombre", "null")
                        putString("user", "null")
                        putString("password", "null")
                        putString("telefono", "null")
                        putString("carrera", "null")
                        putInt("edad", 0)
                        putInt("ecosAcumulados", 0)
                        putInt("mantener", 0)
                        commit()
                    }

                    val intent = Intent(this.context, LumaHomeActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
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