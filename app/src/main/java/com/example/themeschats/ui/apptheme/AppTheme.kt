package com.example.themeschats.ui.apptheme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.themeschats.enums.Themes
import com.example.themeschats.ui.apptheme.themesColors.natural.darkNaturalScheme
import com.example.themeschats.ui.apptheme.themesColors.natural.lightNaturalScheme
import com.example.themeschats.ui.apptheme.themesColors.original.darkOriginalScheme
import com.example.themeschats.ui.apptheme.themesColors.original.lightOriginalScheme


@Composable
fun  AppTheme(
    //Elegir un tema - Original - Natural - Sunset
    theme: Themes,
    // Solo aplica para la version android 12+
    //Es decir el API 31+
    isDynamicColors : Boolean = false,
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
){

    // TODO: SET COLORS
    val colors : ColorScheme

    if (isDynamicColors && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val context = LocalContext.current
        colors = if (isDarkMode) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)

    }else{
        colors = when(theme){
            Themes.ORIGINAL -> if (isDarkMode) darkOriginalScheme else lightOriginalScheme
            Themes.NATURAL -> if (isDarkMode) darkNaturalScheme else lightNaturalScheme
            Themes.SUNSET -> TODO()
        }
    }


    // TODO: SET TYPOGRAPHY

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}