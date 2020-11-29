package mx.tec.lumaapp.Retrofit

import mx.tec.lumaapp.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface IUserService {
    @POST(value = "userLogin/")
    fun userLogin(@Body user : User) : Call<User>

    @POST(value = "userReset/")
    fun userReset(@Body user: User) : String

    @POST(value = "userRegister/")
    fun userRegister(@Body user: User) : String

    @POST(value = "userUpdate/")
    fun userUpdate(@Body user : User, @Header("newPassword") password : String) : Call<User>
}