package com.example.blogspace.ui.theme

import android.content.Context
import android.graphics.Typeface
import androidx.core.content.res.ResourcesCompat
import com.example.blogspace.R

object Type {

    fun getRobotoBold(context: Context): Typeface? {
        return try {
            ResourcesCompat.getFont(context, R.font.roboto_bold)
        } catch (e: Exception) {
            null
        }
    }

    fun getRobotoRegular(context: Context): Typeface? {
        return try {
            ResourcesCompat.getFont(context, R.font.roboto_regular)
        } catch (e: Exception) {
            null
        }
    }
}