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
import mx.tec.lumaapp.mail.AppExecutors
import mx.tec.lumaapp.mail.Credenciales
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

class RegisterFragment : Fragment() {
    private var sharedPreferences : SharedPreferences? = null
    lateinit var appExecutors: AppExecutors

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.register_layout, container, false)

        sharedPreferences = this.activity?.getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)

        val btnAceptar = view.findViewById<Button>(R.id.btnAceptar)
        btnAceptar.setOnClickListener{
            sendEmail2()
//            Register(view)
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

    private fun sendEmail2(){
        val Email = "a01423630@itesm.mx"
        appExecutors.diskIO().execute {
            val props = System.getProperties()
            props.put("mail.smtp.host", "smtp.gmail.com")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.port", "465")

            val session =  Session.getInstance(props,
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
                mm.setText("Bienvenida(o) a PuntoVerde." +
                        "\nNuestro objetivo es crear un cambio positivo en la comunidad ITESM Cuernavaca en favor del medio ambiente." +
                        "\n\nPara iniciar sesión y comenzar a utilizar su cuenta utilice:" +
                        "\nSu usuario:" +
                        "\nY la contraseña:" +
                        "\n\nSugerimos cambiar su contreseña predefinida a una de su elección una vez que ingrese a su cuenta." +
                        "\n\n\nPuntoVerde nunca le pedirá datos personales como usuario, contraseña, correo, teléfono o cualquier otro dato sensible a través de un correo elctrónico." +
                        "\nEste correo es informativo, favor de no responder a esta dirección de correo, ya que no se encuentra habilitada para recibir mensajes." +
                        "\nAtte. el equipo de LUMA.")
//                , "text/html; charset=utf-8"

                //Sending email
                Transport.send(mm)

                appExecutors.mainThread().execute {
                    //Something that should be executed on main thread.

                    Toast.makeText(this.requireContext(), "Correo enviado", Toast.LENGTH_SHORT).show()
                }

            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }

    private fun sendEmail(){
        appExecutors.diskIO().execute {
            val props = System.getProperties()
            props.put("mail.smtp.host", "smtp.gmail.com")
            props.put("mail.smtp.socketFactory.port", "465")
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
            props.put("mail.smtp.auth", "true")
            props.put("mail.smtp.port", "465")

            val session =  Session.getInstance(props,
                object : javax.mail.Authenticator() {
                    //Authenticating the password
                    override fun getPasswordAuthentication(): PasswordAuthentication {
                        return PasswordAuthentication(Credenciales.EMAIL, Credenciales.PASSWORD)
                    }
                })

            try {
                //Creating MimeMessage object
                val mm = MimeMessage(session)
                val emailId = "a01423630@itesm.mx"
                //Setting sender address
                mm.setFrom(InternetAddress(Credenciales.EMAIL))
                //Adding receiver
                mm.addRecipient(
                    Message.RecipientType.TO,
                    InternetAddress(emailId))
                //Adding subject
                mm.subject = "Le damos la bienvenida a PuntoVerde"
                //Adding message
                mm.setText("Your mail body.")

                //Sending email
                Transport.send(mm)

                appExecutors.mainThread().execute {
                    //Something that should be executed on main thread.
                }

            } catch (e: MessagingException) {
                e.printStackTrace()
            }
        }
    }

    fun Register(view : View){

        val regex = Regex(pattern = "^([AL][0-9]{8}|[a-zA-Z0-9]+([._-]?[a-zA-Z0-9])*)@(itesm|tec)\\.mx$")
        if(txtUser.text.isNotEmpty()){
            if(regex.containsMatchIn(input = txtUser.text)) {
                Toast.makeText(this.requireContext(), "El correo válido", Toast.LENGTH_SHORT).show()
                sendEmail()
//                    if (txtPass.text.toString() == txtPassConfirm.text.toString()) {
//
//                        val dialogBuilder = AlertDialog.Builder(this.requireContext())
//                        dialogBuilder.setMessage("¿Deseas activar esta cuenta?")
//                            .setCancelable(false)
//                            .setPositiveButton("Activar") { dialogInterface, which ->
//
//                                with(sharedPreferences!!.edit()) {
//                                    putString("user", txtUser.text.toString())
//                                    putString("password", txtPass.text.toString())
//                                    putInt("accountValidate", 1)
//                                    commit()
//                                }
//                                Toast.makeText(
//                                    this.requireContext(),
//                                    "Cuenta activada",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                this.activity?.onBackPressed()
//                            }
//                            .setNegativeButton(
//                                "Cancelar",
//                                DialogInterface.OnClickListener { dialog, id ->
//                                    dialog.cancel()
//                                })
//                        val alert = dialogBuilder.create()
//                        alert.setTitle("Activar cuenta")
//                        alert.show()
//                    } else {
//                        Toast.makeText(this.requireContext(), R.string.NotMatch, Toast.LENGTH_SHORT)
//                            .show()
//                    }
                }
            else {
                Toast.makeText(this.requireContext(), "El correo no es válido", Toast.LENGTH_SHORT).show()
            }
        }
        else {
            Toast.makeText(this.requireContext(), "Ingresar correo institucional", Toast.LENGTH_SHORT).show()
        }
    }
}