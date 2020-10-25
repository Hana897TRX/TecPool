package mx.tec.lumaapp

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import mx.tec.lumaapp.Retrofit.INodeJS
import mx.tec.lumaapp.Retrofit.signIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LogInActivity : AppCompatActivity(){
    var retroFit : Retrofit? = null
    var service :INodeJS? = null
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_main)
        retroFit = Retrofit.Builder()
            .baseUrl("http://192.168.1.66:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retroFit!!.create<INodeJS>(INodeJS::class.java)

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
            signin(userTxt.text.toString(), passwordTxt.text.toString())
        }
    }


    fun IniciarSesión_Automatica() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


    private fun signin(user: String, password: String){
        var signInInfo : signIn? = signIn(user, password)
        service!!.signIn(signInInfo).enqueue(object: retrofit2.Callback<signIn>{
            override fun onResponse(call: retrofit2.Call<signIn>, response: retrofit2.Response<signIn>?){
                signInInfo = response?.body()
                Toast.makeText(this@LogInActivity, "Success", Toast.LENGTH_SHORT).show()

                val intent = Intent(this@LogInActivity, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }

            override fun onFailure(call: Call<signIn>, t: Throwable) {
                t?.printStackTrace()
                Toast.makeText(this@LogInActivity, "Wrong user or Password", Toast.LENGTH_SHORT).show()
            }
        })
    }
}