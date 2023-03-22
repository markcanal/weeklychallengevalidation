package com.example.validationpoc.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.validationpoc.ui.usecase.ManageDataEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ValidationViewModel @Inject constructor(private val manageDataEntry: ManageDataEntry) :
    ViewModel() {
    private var _isValidFullName = MutableStateFlow(true)
    val isValidFullName = _isValidFullName.asStateFlow()

    private var _isValidEmail = MutableStateFlow(true)
    val isValidEmail = _isValidEmail.asStateFlow()

    private var _isValidMobileNumber = MutableStateFlow(true)
    val isValidMobileNumber = _isValidMobileNumber.asStateFlow()

    private var _canSubmit = MutableStateFlow(false)
    val canSubmit = _canSubmit.asStateFlow()

    private var _calculatedAge = MutableStateFlow(0)
    val calculatedAge = _calculatedAge.asStateFlow()

    fun verifyFullName(name: String) {
        viewModelScope.launch {
            _isValidFullName.value = manageDataEntry.isValidName(name)
            canSubmit()
        }
    }

    fun validateEmail(email: String) {
        viewModelScope.launch {
            _isValidEmail.value = manageDataEntry.isValidEmail(email)
            canSubmit()
        }
    }

    fun validateMobile(digit: String) {
        viewModelScope.launch {
            _isValidMobileNumber.value = manageDataEntry.isValidMobile(digit)
            canSubmit()
        }
    }

    private fun canSubmit() {
        viewModelScope.launch {
            _canSubmit.value =
                (isValidFullName.value && isValidEmail.value && isValidMobileNumber.value && calculatedAge.value >= 18)
        }
    }

    fun onChangeBirthDate(year: Int, day: Int, month: Int) {
        _calculatedAge.value = manageDataEntry.calculateAge(year, day, month)
        canSubmit()
    }
}