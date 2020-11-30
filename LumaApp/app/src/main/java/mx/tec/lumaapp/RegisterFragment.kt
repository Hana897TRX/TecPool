package mx.tec.lumaapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.register_layout.*
import mx.tec.lumaapp.Retrofit.IUserService
import mx.tec.lumaapp.Utility.EnvSettings
import mx.tec.lumaapp.mail.AppExecutors
import mx.tec.lumaapp.mail.Credenciales
import mx.tec.lumaapp.models.SimpleMessage
import mx.tec.lumaapp.models.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class RegisterFragment : Fragment() {
    private var sharedPreferences: SharedPreferences? = null
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_layout, container, false)

        sharedPreferences =
            this.activity?.getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)

        val btnAceptar = view.findViewById<Button>(R.id.btnAceptar)
        btnAceptar.setOnClickListener {
            getNewPassword(txtUser.text.toString(), view)
        }

        val btnCancelar = view.findViewById<Button>(R.id.btnCancel)
        btnCancelar.setOnClickListener {
            this.activity?.onBackPressed()
        }

        return view
    }

    override fun onAttach(context: Context) {
        appExecutors = AppExecutors()
        super.onAttach(context)
    }

    private fun getNewPassword(email : String, view : View){
        val user = User(0,0,0,0,0, email, "Invitado", "")
        val retrofit = EnvSettings.getDB()
        val service = retrofit.create(IUserService::class.java)

        service.userRegister(user).enqueue(object : Callback<SimpleMessage> {
            override fun onResponse(call: Call<SimpleMessage>, response: Response<SimpleMessage>) {
                val data = response.body()
                Toast.makeText(view.context, data!!.message, Toast.LENGTH_SHORT)
                    .show()

                if(data.type != "Error Message")
                    sendEmail2(email, data.message)
            }

            override fun onFailure(call: Call<SimpleMessage>, t: Throwable) {
                Toast.makeText(view.context, "Error de conexión", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun sendEmail2(Email: String, password : String) {
        appExecutors.diskIO().execute {
            val props = System.getProperties()
            props.put("mail.smtp.host", "smtp.gmail.com")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.port", "465")

            val session = Session.getInstance(props,
                object : javax.mail.Authenticator() {
                    //Authenticating the password
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(Credenciales.EMAIL, Credenciales.PASSWORD)
                    }
                })

            try {
                //Creating MimeMessage object
                val mm = MimeMessage(session)
                //Setting sender address
                mm.setFrom(InternetAddress(Credenciales.EMAIL))
                //Adding receiver
                mm.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(Email)
                )
                //Adding subject
                mm.subject = "Le damos la bienvenida a PuntoVerde"
                //Adding message
                mm.setText(
                    "Bienvenida(o) a PuntoVerde." +
                            "\nNuestro objetivo es crear un cambio positivo en la comunidad ITESM Cuernavaca en favor del medio ambiente." +
                            "\n\nPara iniciar sesión y comenzar a utilizar su cuenta utilice:" +
                            "\nSu usuario: " + Email +
                            "\nY la contraseña: " + password +
                            "\n\nSugerimos cambiar su contreseña predefinida a una de su elección una vez que ingrese a su cuenta." +
                            "\n\n\nPuntoVerde nunca le pedirá datos personales como usuario, contraseña, correo, teléfono o cualquier otro dato sensible a través de un correo elctrónico." +
                            "\nEste correo es informativo, favor de no responder a esta dirección de correo, ya que no se encuentra habilitada para recibir mensajes." +
                            "\nAtte. el equipo de LUMA."
                )

                //Sending email
                Transport.send(mm)

                appExecutors.mainThread().execute {
                    //Something that should be executed on main thread.

                    Toast.makeText(this.requireContext(), "Correo enviado", Toast.LENGTH_SHORT)
                        .show()
                }

            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }
}