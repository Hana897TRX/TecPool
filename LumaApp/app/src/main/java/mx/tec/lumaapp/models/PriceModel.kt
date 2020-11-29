package mx.tec.lumaapp.models

data class PriceModel(var idPrize : Int,
                      var category : String,
                      var partner : String,
                      var name : String,
                      var description : String,
                      var points : Int)