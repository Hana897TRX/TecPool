package mx.tec.lumaapp.db.config

import android.content.Context
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import mx.tec.lumaapp.db.model.Usuario
import java.sql.Date


class DBAccess(contex: Context) : AsyncTask<String, Void, Boolean>() {
    var contex = contex
    var db : AppDB? = null
    var user : Usuario? = null
    var taskCompleted: OnTaskCompleted

    init {
        taskCompleted = contex as OnTaskCompleted
    }

    interface OnTaskCompleted{
        fun onTaskCompleted(response: Boolean)
    }

    override fun doInBackground(vararg params: String?): Boolean {
        db = AppDB.getInstance(contex)
        if(params[1]== "d")
            registerGaby(contex)
        else if(params[2] == "0") {
            var user = db!!.usuarioDao().obtenerUsuario(params[0]!!)
            println(user)
            return (user != null && user!!.username == params[0]!! && user!!.passToken == params[1]!!)
        }
        return false
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: Boolean?) {
        super.onPostExecute(result)
        taskCompleted.onTaskCompleted(result!!)
    }

    fun registerGaby(context: Context) {

        db = AppDB.getInstance(context)

        var gaby: Usuario = Usuario(
            0,
            "Gabriela Fernanda",
            "Soto Ramírez",
            "Hana897TRX",
            Date.valueOf("2020-11-09"),
            "A01423906",
            "ITC",
            20,
            "Hana2"
        );
        db!!.usuarioDao().registrarUsuario(gaby)

        var rulo: Usuario = Usuario(
            0,
            "Raúl",
            " Orihuela Rosas",
            "Rulo",
            Date.valueOf("2020-11-02"),
            "A01422929",
            "ITC",
            300,
            "Rulo"
        )
        db!!.usuarioDao().registrarUsuario(rulo)

        var isra: Usuario = Usuario(
            0,
            "Israel",
            "Sánchez Hinojosa",
            "Montoya",
            Date.valueOf("2020-02-01"),
            "A01423789",
            "ITC",
            0,
            "Montoya"
        )
        db!!.usuarioDao().registrarUsuario(isra)

        var roy: Usuario = Usuario(
            0,
            "Rodrigo Sebastián",
            "De la Rosa Andrés",
            "Tory",
            Date.valueOf("2072-06-01"),
            "A01423630",
            "ITC",
            100,
            "Roy"
        )
        db!!.usuarioDao().registrarUsuario(roy)
    }
}