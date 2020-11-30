package mx.tec.lumaapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.code_password_activity.*
import mx.tec.lumaapp.Retrofit.IUserService
import mx.tec.lumaapp.Utility.EnvSettings
import mx.tec.lumaapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodePasswordFragment : Fragment() {
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.code_password_activity, container, false)

        sharedPreferences = view.context.getSharedPreferences(EnvSettings.getSPName(), Context.MODE_PRIVATE)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAceptar = view.findViewById<Button>(R.id.aceptarBtn)
        val enviarNuevo = view.findViewById<Button>(R.id.enviarNuevo)

        btnAceptar.setOnClickListener {
            var user = User(0,0,0,0,0,"", "", "")
            user.mail = sharedPreferences!!.getString(EnvSettings.getMail(), "null")!!
            user.password = txtCodigo.text.toString()

            val retrofit = EnvSettings.getDB()
            val service = retrofit.create(IUserService::class.java)

            service.userLogin(user).enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    var data = response.body()

                    if (data!!.idUser != 0){
                        val sp = view.context.getSharedPreferences(EnvSettings.getSPName(), Context.MODE_PRIVATE)

                        with(sp.edit()){
                            putInt(EnvSettings.getIdUser(), data.idUser)
                            putString(EnvSettings.getUserName(), data.name)
                            putString(EnvSettings.getUsCode(), txtCodigo.text.toString())
                            putInt(EnvSettings.getEcoPuntos(), data.ecoPointsTotal)
                            apply()
                        }

                        findNavController().navigate(R.id.action_codePasswordActivity_to_change_password_activity)
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
    }
}