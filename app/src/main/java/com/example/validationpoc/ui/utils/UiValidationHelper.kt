package com.example.validationpoc.ui.utils

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources

object UiValidationHelper {
    fun setUiBackGround(context: Context, resId: Int): Drawable? {
        return AppCompatResources.getDrawable(context, resId)
    }
}