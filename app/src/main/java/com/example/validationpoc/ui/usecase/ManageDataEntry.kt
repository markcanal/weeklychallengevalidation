package com.example.validationpoc.ui.usecase

interface ManageDataEntry {
    fun isValidName(name: String?): Boolean
    fun isValidEmail(email: String): Boolean
    fun isValidMobile(digits: String): Boolean
    fun calculateAge(year: Int, day: Int, month: Int): Int
    fun updateGender(): String
}