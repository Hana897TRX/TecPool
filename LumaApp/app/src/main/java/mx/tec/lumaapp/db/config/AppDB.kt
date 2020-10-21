package mx.tec.lumaapp.db.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mx.tec.lumaapp.db.dao.UsuariosCore
import mx.tec.lumaapp.db.model.Usuario

@Database(entities = [Usuario::class], version = 1)
@TypeConverters(DateConvert::class)
abstract class AppDB : RoomDatabase(){
    abstract fun usuarioDao(): UsuariosCore

    companion object{
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context) : AppDB{
            context.deleteDatabase("Usuarios")
            if(INSTANCE == null){
                INSTANCE = Room.inMemoryDatabaseBuilder(context, AppDB::class.java).build()
            }
            return INSTANCE as AppDB
        }
    }
}