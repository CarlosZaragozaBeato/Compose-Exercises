package com.carloszaragoza.notesapp.ui.feature_note.presentation.screens.home.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb


class PriorityUtil {
    companion object{
        fun util(priority:String):Int{
            when(priority){
                "LOW" -> {
                     return Color(0xffDA0037).toArgb()
                 }
                "MEDIUM" -> {
                    return Color(0xffFFD369).toArgb()
                }
                "HIGH" -> {
                    return Color(0xffA7D129).toArgb()
                }
                else -> return Color(0xffDFDFDE).toArgb()
            }

        }
    }
}