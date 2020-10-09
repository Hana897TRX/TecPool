package mx.tec.lumaapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PerfilActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil_layout)

        val usuario = intent.getStringExtra("usuario")

        val usuarioLayout = findViewById<TextView>(R.id.usuarioTxt)
        usuarioLayout.text = usuario
    }
}