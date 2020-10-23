package mx.tec.lumaapp.db.dao

import androidx.room.*
import mx.tec.lumaapp.db.model.Usuario

@Dao
interface UsuariosCore {
    @Query("SELECT * FROM Usuario")
    fun obtenerUsuarios() : List<Usuario>
    @Query("SELECT * FROM Usuario WHERE Username LIKE :username")
    fun obtenerUsuario(username : String) : Usuario
    @Insert
    fun registrarUsuario(user: Usuario)
    @Update
    fun updateUser(user : Usuario)
    @Query("UPDATE Usuario SET EcoPuntos = :ecoPuntos WHERE Id LIKE :id")
    fun updateEcoPuntos(id : Int, ecoPuntos : Int)
    @Delete
    fun deleteUser(user : Usuario)
}