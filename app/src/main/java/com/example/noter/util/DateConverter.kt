package com.example.noter.util


import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun timeStampFromDate(date : Date) : Long = date.time

    @TypeConverter
    fun dateFromTimeStamp(timeStamp : Long) : Date = Date(timeStamp)

}