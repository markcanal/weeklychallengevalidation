package com.example.validationpoc.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ValidationViewModel @Inject constructor() : ViewModel() {
    private var _isValidFullName = MutableStateFlow(false)
    val validFullName: StateFlow<Boolean> get() = _isValidFullName

    private var _isValidEmail = MutableStateFlow(false)
    val validEmail: StateFlow<Boolean> get() = _isValidEmail

    private var _isValidMobileNumber = MutableStateFlow(false)
    val isValidMobileNumber: StateFlow<Boolean> get() = _isValidMobileNumber

    private var _canSubmit = MutableStateFlow(false)
    val canSubmit: StateFlow<Boolean> get() = _canSubmit
}