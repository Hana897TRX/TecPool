package mx.tec.lumaapp.Utility

class EnvSettings {
    private val stringKeys:HashMap<String,String> = HashMap()
    init {
        stringKeys["dbUrl"] = "/rest/luma/"
        stringKeys["dbIp"] = "http://192.168.1.70" //192.168.43.102
        stringKeys["dbPort"] = "8080"
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
    }
}