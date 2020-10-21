package mx.tec.lumaapp.db.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="Id")
    val id : Int,
    //@NonNull
    @ColumnInfo(name="First_Name")
    val firstName : String,
    //@NonNull
    @ColumnInfo(name="Sur_Name")
    val surName : String,
    //@NonNull
    @ColumnInfo(name="Username")
    val username : String,
    //@NonNull
    @ColumnInfo(name="FechaNacimiento")
    val fechaNacimiento : Date,
    //@NonNull
    @ColumnInfo(name="Matricula")
    val matricula : String,
    //@NonNull
    @ColumnInfo(name="Carrera")
    val carrera : String,
    //@NonNull
    @ColumnInfo(name="EcoPuntos")
    val ecoPuntos : Int,
    @NonNull
    @ColumnInfo(name="PasswordToken")
    val passToken : String
)