package mx.tec.lumaapp.db.config

import androidx.room.TypeConverter
import java.sql.Date

class DateConvert {
    @TypeConverter
    fun fromTimestamp(value: Long?) : Date? {
        return value?.let{ Date(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date: Date?) : Long? {
        return date?.time
    }
}