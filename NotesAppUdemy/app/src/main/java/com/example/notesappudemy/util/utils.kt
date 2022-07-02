package com.example.notesappudemy.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(time:Long):String{
    val date = Date(time)
    val format = SimpleDateFormat("EEE, d MM hh:mm aa",
                                Locale.getDefault())

    return format.format(time)
}