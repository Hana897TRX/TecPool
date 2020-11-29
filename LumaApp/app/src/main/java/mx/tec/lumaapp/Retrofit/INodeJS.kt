package mx.tec.lumaapp.Retrofit

import mx.tec.lumaapp.models.User
import retrofit2.Call
import retrofit2.http.*

interface INodeJS {
    @POST(value="register/")
    fun RegisterUsers(@Path(value="id") id: Int,
                      @Body user: User?): Call<User>

    @POST(value="login/")
    fun signIn(@Body info: signIn?): Call<signIn>
}