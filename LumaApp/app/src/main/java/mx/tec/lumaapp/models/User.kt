package mx.tec.lumaapp.models

data class User(var ecoPointsTotal : Int,
                var idAdmin : Int,
                var idDriver : Int,
                var idPassenger : Int,
                var idUser : Int,
                var mail : String,
                var name : String,
                var password : String )
