package com.example.arcadia.core.database.converters

import androidx.room.TypeConverter
import com.example.arcadia.core.domain.model.PlatformType
import com.example.arcadia.core.domain.util.dateToString
import com.example.arcadia.core.domain.util.toDate
import java.time.LocalDate

class ArcadiaTypeConverters {

    private val delimiter = ","

    @TypeConverter
    fun fromPlatforms(platforms: List<PlatformType>): String =
        platforms.joinToString(delimiter) { it.name }

    @TypeConverter
    fun toPlatforms(value: String): List<PlatformType> =
        value.split(delimiter).map { PlatformType.valueOf(it) }

    @TypeConverter
    fun toDate(dateString: String): LocalDate? {
        return dateString.toDate()
    }

    @TypeConverter
    fun fromDate(date: LocalDate?): String? {
        return date?.dateToString()
    }

    @TypeConverter
    fun fromList(list: List<String>): String {
        return list.joinToString(delimiter)
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        if (value.isBlank()) return emptyList()
        return value.split(delimiter)
    }


}
