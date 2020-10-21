package mx.tec.lumaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.login_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.tec.lumaapp.db.config.DBAccess

class LogInActivity : AppCompatActivity(), DBAccess.OnTaskCompleted {
    @RequiresApi(Build.VERSION_CODES.O)
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
            var dbAccess : DBAccess = DBAccess(this)
            dbAccess.execute(userTxt.text.toString(), passwordTxt.text.toString(), "0")
        }
    }

    override fun onTaskCompleted(response: Boolean) {
        if(response){
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "Usuario no valido", Toast.LENGTH_SHORT).show()
        }
    }

    fun IniciarSesión_Automatica() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }
}