package mx.tec.lumaapp

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.register_layout.*

class RegisterActivity : Fragment() {
    private var sharedPreferences : SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_layout, container, false)

        sharedPreferences = this.activity?.getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)

        val btnAceptar = view.findViewById<Button>(R.id.btnLogin)
        btnAceptar.setOnClickListener{Register(view)}

        val btnCancelar = view.findViewById<Button>(R.id.btnCancel)
        btnCancelar.setOnClickListener {
            this.activity?.onBackPressed()
        }

        return view
    }

    fun Register(view : View){
        if(txtUser.text.isNotEmpty() && txtPass.text.isNotEmpty() && txtPassConfirm.text.isNotEmpty()){
            if(txtPass.text.toString() == txtPassConfirm.text.toString()){

                val dialogBuilder = AlertDialog.Builder(this.requireContext())
                dialogBuilder.setMessage("¿Deseas activar esta cuenta?")
                    .setCancelable(false)
                    .setPositiveButton("Activar") { dialogInterface, which ->

                        with(sharedPreferences!!.edit()){
                            putString("user", txtUser.text.toString())
                            putString("password", txtPass.text.toString())
                            putInt("accountValidate", 1)
                            commit()
                        }
                        Toast.makeText(this.requireContext(), "Cuenta activada", Toast.LENGTH_SHORT).show()
                        this.activity?.onBackPressed()
                        }
                    .setNegativeButton("Cancelar", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Activar cuenta")
                alert.show()
            }
            else{
                Toast.makeText(this.requireContext(), R.string.NotMatch, Toast.LENGTH_SHORT).show()
            }
        }
    }
}