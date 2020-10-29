package mx.tec.lumaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController

class CodePasswordActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.code_password_activity, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAceptar = view.findViewById<Button>(R.id.aceptarBtn)
        val enviarNuevo = view.findViewById<Button>(R.id.enviarNuevo)

        val codigo = view.findViewById<EditText>(R.id.txtCodigo)
        btnAceptar.setOnClickListener {
//            if(codigo.text.isNotEmpty())
                findNavController().navigate(R.id.action_codePasswordActivity_to_change_password_activity)
        }
    }
}