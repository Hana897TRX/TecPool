package mx.tec.lumaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.login_main.*

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        val sharedPreferences = getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)
        val iniciar = sharedPreferences!!.getInt("mantener", 0)

        if(iniciar ==  1) {
            IniciarSesión_Automatica()
        }

        val btnAceptar = findViewById<Button>(R.id.btnLogin)
        val userTxt = findViewById<EditText>(R.id.txtUser)
        val passwordTxt = findViewById<EditText>(R.id.txtPass)
        val mantener = findViewById<CheckBox>(R.id.manter_iniciadoBtn)

        btnAceptar.setOnClickListener {
            validadUsuario_JarCodeado(userTxt.text.toString(),passwordTxt.text.toString(),mantener)
        }
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
                        getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)

                    with(sharedPreferences.edit()) {
                        putString("nombre", it.nombre)
                        if (mantener.isChecked) {
                            putInt("mantener",1)
                            putString("user", it.user)
                            putString("password", it.password)
                        } else
                            putInt("mantener",0)
                        putString("telefono", it.telefono)
                        putInt("edad", it.edad)
                        putString("carrera", it.carrera)
                        putInt("ecosAcumulados", it.ecosAcumulados)
                        commit()
                    }

                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }
    }

    fun IniciarSesión_Automatica() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    data class Usuario_JarCodeado(
        val nombre: String,
        val user: String,
        val password: String,
        val telefono: String,
        val edad: Int,
        val carrera: String,
        val ecosAcumulados: Int
    ){}
}