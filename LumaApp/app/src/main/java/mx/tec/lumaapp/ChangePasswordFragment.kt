package mx.tec.lumaapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.change_password_activity.*
import kotlinx.android.synthetic.main.change_password_activity.view.*
import mx.tec.lumaapp.Retrofit.IUserService
import mx.tec.lumaapp.Utility.EnvSettings
import mx.tec.lumaapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChangePasswordFragment : Fragment() {
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.change_password_activity, container, false)

        sharedPreferences = view.context.getSharedPreferences(EnvSettings.getSPName(), Context.MODE_PRIVATE)

        view.txtName.setText(sharedPreferences!!.getString(EnvSettings.getUserName(), "Invitado")!!)

        return view
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAceptar = view.findViewById<Button>(R.id.btnAceptar)

        btnAceptar.setOnClickListener {
            if (view.txtPass.text.isNotEmpty() && view.txtPassConfirm.text.isNotEmpty() && view.txtName.text.isNotEmpty() && view.txtPass.text.toString() ==  view.txtPassConfirm.text.toString()){
                val retrofit = EnvSettings.getDB()
                val service = retrofit.create(IUserService::class.java)

                var user = User(0,0,0,0,0, "", "", "")
                user.idUser = sharedPreferences!!.getInt(EnvSettings.getIdUser(), -1)
                user.mail = sharedPreferences!!.getString(EnvSettings.getMail(), "null")!!
                user.password = sharedPreferences!!.getString(EnvSettings.getUsCode(), "null")!!
                user.name = txtName.text.toString()

                service.userUpdate(user, txtPass.text.toString()).enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        val data = response.body()

                        if (data!!.name == txtName.text.toString()) {

                            Toast.makeText(view.context, "Datos actualizados. Por favor inicie sesión", Toast.LENGTH_LONG)
                                .show()
                            findNavController().navigate(R.id.action_changePasswordActivity_to_loginActivity)
                        }
                        else
                            Toast.makeText(view.context, "Los datos no pudieron actualizarse.", Toast.LENGTH_SHORT)
                                .show()
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Toast.makeText(view.context, "Error de conexión", Toast.LENGTH_SHORT)
                            .show()
                    }
                })
            }
        }
    }
}