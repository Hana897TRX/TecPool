package mx.tec.lumaapp.Utility

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EnvSettings {
    private val stringKeys:HashMap<String,String> = HashMap()
    init {
        stringKeys["dbUrl"] = "/rest/luma/"
        stringKeys["dbIp"] = "http://192.168.1.70" //192.168.43.102
        stringKeys["dbPort"] = "8080"
        stringKeys["spName"] = "TempUser"
        stringKeys["idUser"] = "idUser"
        stringKeys["mail"] = "mail"
        stringKeys["usCode"] = "usCode"
        stringKeys["idEcopuntos"] = "idEcopuntos"
        stringKeys["userName"] = "userName"
    }

    companion object{
        private var instance:EnvSettings? = null

        @Synchronized
        fun getInstance():EnvSettings {
            if (instance == null) instance = EnvSettings()
            return instance as EnvSettings
        }

        fun getDbAddress():String{
            val values = getInstance().stringKeys
            return (values["dbIp"] + ":" + values["dbPort"] + values["dbUrl"])
        }

        fun getDB() : Retrofit {
            return Retrofit.Builder().baseUrl(EnvSettings.getDbAddress()).addConverterFactory(
                GsonConverterFactory.create()).build()
        }

        fun getIdUser() : String{
            return getInstance().stringKeys["idUser"]!!
        }

        fun getUsCode() : String{
            return getInstance().stringKeys["usCode"]!!
        }

        fun getSPName() : String{
            return getInstance().stringKeys["spName"]!!
        }

        fun getUserName() : String{
            return getInstance().stringKeys["userName"]!!
        }

        fun getEcoPuntos() : String {
            return getInstance().stringKeys["idEcopuntos"]!!
        }

        fun getMail() : String {
            return getInstance().stringKeys["mail"]!!
        }
    }
}