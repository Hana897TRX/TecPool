package mx.tec.lumaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.UserPresenceUnavailableException
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.perfil_layout.*

class LogInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)

        val btnAceptar = findViewById<Button>(R.id.btnLogin)
        val usuario = findViewById<EditText>(R.id.txtUser)

        btnAceptar.setOnClickListener{
            if(usuario.text.isNotEmpty()) {
                val intent = Intent(this, PerfilActivity::class.java)
                intent.putExtra("usuario",usuario.text.toString())
                startActivity(intent)
            }
            else
                Toast.makeText(this, "Por favor ingresar usuario", Toast.LENGTH_SHORT).show()
        }
    }
}