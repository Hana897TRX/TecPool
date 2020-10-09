package mx.tec.lumaapp

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil_layout)

        val miPerfil = findViewById<TextView>(R.id.miPerfil)

        miPerfil.typeface = Typeface.createFromAsset(assets,"Fonts/Righteous-Regular.ttf")

    }
}