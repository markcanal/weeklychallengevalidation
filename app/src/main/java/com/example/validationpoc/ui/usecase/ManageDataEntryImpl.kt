package com.example.validationpoc.ui.usecase

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.Period
import javax.inject.Inject

class ManageDataEntryImpl @Inject constructor() : ManageDataEntry {
    override fun isValidName(name: String?): Boolean {
        return if (name == null) {
            true
        } else {
            val regex =
                "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)".toRegex()
            regex.matches(name)
        }
    }

    override fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    override fun isValidMobile(digits: String): Boolean {
        val regex = "^(09|\\+639)\\d{9}\$".toRegex()
//            "^(\\+\\d{1,3}[- ]?)?\\d{11}\$".toRegex()
        return regex.matches(digits)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun calculateAge(year: Int, day: Int, month: Int): Int {
        Log.d("Date", "$year - $day - $month")
        return Period.between(
            LocalDate.of(year, month, day),
            LocalDate.now()
        ).years
    }

    override fun updateGender(): String {
        TODO("Not yet implemented")
    }
}