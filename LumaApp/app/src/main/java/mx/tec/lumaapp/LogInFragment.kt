package mx.tec.lumaapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import mx.tec.lumaapp.Retrofit.IUserService
import mx.tec.lumaapp.Utility.EnvSettings
import mx.tec.lumaapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogInFragment : Fragment() {
    @SuppressLint("RestrictedApi")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
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
        val passwordTxt = view.findViewById<EditText>(R.id.txtPassConfirm)
        val btnOlvide = view.findViewById<TextView>(R.id.olvide_contraBtn)
        val mantener = view.findViewById<CheckBox>(R.id.manter_iniciadoBtn)
        val btnRegistrar = view.findViewById<TextView>(R.id.txtRegister)

        // Log-in
        btnAceptar.setOnClickListener {

            var user = User(0,0,0,0,0, "", "", "")
            user.mail = userTxt.text.toString()
            user.password = passwordTxt.text.toString()

            val retrofit = EnvSettings.getDB()
            val service = retrofit.create(IUserService::class.java)

            service.userLogin(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    var response = response.body()

                    if (response!!.idUser != 0){
                        val sp = view.context.getSharedPreferences(EnvSettings.getSPName(), Context.MODE_PRIVATE)

                        with(sp.edit()){
                            putString(EnvSettings.getUserName(), response.name)
                            putInt(EnvSettings.getEcoPuntos(), response.ecoPointsTotal)
                            apply()
                        }

                        val intent = Intent(view.context, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(view.context, "User or password wrong", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(view.context, "Error de conexión", Toast.LENGTH_SHORT)
                        .show()
                }
            })
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
}