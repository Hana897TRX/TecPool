package mx.tec.lumaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_password, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnEnviar = view.findViewById<Button>(R.id.enviarBtn)
        val correo = view.findViewById<EditText>(R.id.txtCorreo)
        enviarBtn.setOnClickListener {
//            if (correo.text.isNotEmpty())
                findNavController().navigate(R.id.action_passwordActivity_to_codePasswordActivity)
        }

    }
}