package com.example.rickandmorty.model.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter


object DateTimeFormatUtils {
    fun format(unformattedDate: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        val localDateTime = LocalDateTime.parse(unformattedDate, DateTimeFormatter.ISO_DATE_TIME)
        return localDateTime.format(formatter).toString()
    }
}