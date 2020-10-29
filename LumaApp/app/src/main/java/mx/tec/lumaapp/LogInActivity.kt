package mx.tec.lumaapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class LogInActivity : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_main, container, false)

        val sharedPreferences = this.activity?.getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)
        val iniciar = sharedPreferences!!.getInt("mantener", 0)

        if (iniciar == 1) {
            IniciarSesión_Automatica()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnAceptar = view.findViewById<Button>(R.id.btnAceptar)
        val userTxt = view.findViewById<EditText>(R.id.txtCorreo)
        val passwordTxt = view.findViewById<EditText>(R.id.txtPass)
        val btnOlvide = view.findViewById<TextView>(R.id.olvide_contraBtn)
        val mantener = view.findViewById<CheckBox>(R.id.manter_iniciadoBtn)
        val btnRegistrar = view.findViewById<TextView>(R.id.txtRegister)

        btnAceptar.setOnClickListener {
            validadUsuario_JarCodeado(
                userTxt.text.toString(),
                passwordTxt.text.toString(),
                mantener
            )
        }

        btnOlvide.setOnClickListener {
            findNavController().navigate(R.id.action_loginActivity_to_passwordActivity)
        }

        btnRegistrar.setOnClickListener{
            findNavController().navigate(R.id.action_loginActivity_to_registerActivity)
        }
    }

    private fun IniciarSesión_Automatica() {
        val intent = Intent(this.requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    fun validadUsuario_JarCodeado(user: String, password: String, mantener: CheckBox) {

        val UsusariosJar = arrayListOf<Usuario_JarCodeado>(
            Usuario_JarCodeado(
                "Gabriela Fernanda Soto GUTIERREZ",
                "Gaby",
                "Gaby",
                "7771033890",
                20,
                "ITC",
                30
            ),
            Usuario_JarCodeado("Raúl Orihuela Rosas", "Rulo", "Rulo", "7771046716", 22, "ITC", 300),
            Usuario_JarCodeado(
                "Israel Sánchez Hinojosa",
                "Montoya",
                "Montoya",
                "7773401499",
                20,
                "ITC",
                0
            ),
            Usuario_JarCodeado(
                "Rodrigo Sebastián de la Rosa Andrés",
                "Tory",
                "Tory",
                "7472211787",
                20,
                "ITC",
                100
            )
        )

        UsusariosJar.forEach {

            if (user == it.user) {
                if (password == it.password) {
                    val sharedPreferences =
                        this.activity?.getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)

                    with(sharedPreferences!!.edit()) {
                        putString("nombre", it.nombre)
                        if (mantener.isChecked) {
                            putInt("mantener", 1)
                            putString("user", it.user)
                            putString("password", it.password)
                        } else
                            putInt("mantener", 0)
                        putString("telefono", it.telefono)
                        putInt("edad", it.edad)
                        putString("carrera", it.carrera)
                        putInt("ecosAcumulados", it.ecosAcumulados)
                        commit()
                    }

                    val intent = Intent(this.requireContext(), MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
    }

    data class Usuario_JarCodeado(
        val nombre: String,
        val user: String,
        val password: String,
        val telefono: String,
        val edad: Int,
        val carrera: String,
        val ecosAcumulados: Int
    ) {}
}