package com.example.validationpoc.ui.usecase

interface ManageDataEntry {
    fun isValidName(): Boolean
    fun isValidEmail(): Boolean
    fun isValidMobile(): Boolean
    fun calculateAge(): Int
    fun updateGender(): String
}