package com.mebeal.marvelapp.presentation.utils.navigation

import java.text.SimpleDateFormat
import java.util.*

fun getDateFormated(date: String) : String {
    val dateString = date.replace('T',' ')
    val cal = Calendar.getInstance()
    val sdfGet = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
    cal.time = sdfGet.parse(dateString)
    val sdfSet = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    return sdfSet.format(cal.time)
}