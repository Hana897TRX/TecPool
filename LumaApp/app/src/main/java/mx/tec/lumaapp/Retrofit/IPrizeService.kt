package mx.tec.lumaapp.Retrofit

import mx.tec.lumaapp.Utility.Berry
import mx.tec.lumaapp.Utility.Item
import mx.tec.lumaapp.models.PriceModel
import retrofit2.Call
import retrofit2.http.GET

interface IPrizeService {
    @GET(value = "prizes/")
    fun getPrizes() : Call<List<PriceModel>>

    @GET(value = "item/1")
    fun getItem() : Call<Item>
}