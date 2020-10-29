package mx.tec.lumaapp

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.register_layout.*

class RegisterActivity : AppCompatActivity() {
    private var sharedPreferences : SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register_layout)
        sharedPreferences = getSharedPreferences("informacion_usuario", Context.MODE_PRIVATE)
    }

    fun Register(view : View){
        if(txtUser.text.isNotEmpty() && txtPass.text.isNotEmpty() && txtPassConfirm.text.isNotEmpty()){
            if(txtPass.text.toString() == txtPassConfirm.text.toString()){

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("Do you want to enable this account?")
                    .setCancelable(false)
                    .setPositiveButton("Activate") { dialogInterface, which ->


                        with(sharedPreferences!!.edit()){
                            putString("user", txtUser.text.toString())
                            putString("password", txtPass.text.toString())
                            putInt("accountValidate", 1)
                            commit()
                        }
                        Toast.makeText(this, "Account enabled", Toast.LENGTH_SHORT).show()
                        }
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })
                val alert = dialogBuilder.create()
                alert.setTitle("Enable account")
                alert.show()
            }
            else{
                Toast.makeText(this@RegisterActivity, R.string.NotMatch, Toast.LENGTH_SHORT).show()
            }
        }
    }
}