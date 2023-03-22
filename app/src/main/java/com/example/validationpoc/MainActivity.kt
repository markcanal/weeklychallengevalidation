package com.example.validationpoc

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.validationpoc.databinding.ActivityMainBinding
import com.example.validationpoc.ui.utils.UiValidationHelper.setUiBackGround
import com.example.validationpoc.ui.viewmodel.ValidationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.*

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val validationViewModel: ValidationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        observeFullName(binding)
        observeEmail(binding)
        observeMobile(binding)
        observeIfCanSubmit(binding)
        observeAge(binding)
        initViews(binding)

        setContentView(binding.root)
    }

    private fun initViews(binding: ActivityMainBinding) = with(binding) {

        // Labeling
        fullNameContainer.txvLabel.text = applicationContext.getString(R.string.full_name_label)
        emailContainer.txvLabel.text = applicationContext.getString(R.string.email_label)
        mobileContainer.txvLabel.text = applicationContext.getString(R.string.mobile_number_label)
        calendarContainer.txvLabel.text = applicationContext.getString(R.string.birthdate_label)
        ageContainer.txvLabel.text = applicationContext.getString(R.string.age_label)
        mobileContainer.txeFullName.inputType = InputType.TYPE_CLASS_PHONE
        emailContainer.txeFullName.inputType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
        fullNameContainer.txeFullName.inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME

        //Listener
        fullNameContainer.txeFullName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                validationViewModel.verifyFullName(s.toString())
            }

        })

        emailContainer.txeFullName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                validationViewModel.validateEmail(s.toString())
            }

        })

        mobileContainer.txeFullName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                validationViewModel.validateMobile(s.toString())
            }
        })

        submitBtn.setOnClickListener {
            Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_LONG).show()
        }
        //Clickable
        calendarContainer.txeFullName.isFocusable = false
        calendarContainer.txeFullName.setOnClickListener {
            showCalendarPicker(binding)
        }

        //Read only
        ageContainer.txeFullName.isFocusable = false
        ageContainer.txeFullName.setText(
            applicationContext.getString(R.string.age_value_text, "")
        )

        //Spinner
        genderSelect.adapter =
            ArrayAdapter.createFromResource(
                this@MainActivity,
                R.array.gender_array,
                android.R.layout.simple_spinner_dropdown_item
            )
    }

    private fun observeFullName(binding: ActivityMainBinding) = with(binding) {
        lifecycleScope.launch {
            validationViewModel.isValidFullName.collect {
                fullNameContainer.txvMessageLabel.isVisible = !it
                fullNameContainer.txeFullName.background = if (!it) {
                    setUiBackGround(this@MainActivity, R.drawable.input_field_border_error)
                } else {
                    setUiBackGround(this@MainActivity, R.drawable.input_field_border)
                }
                if (it) {
                    fullNameContainer.txeFullName.clearFocus()
                }
                fullNameContainer.txvMessageLabel.text =
                    getString(R.string.name_validation_message)
            }
        }
    }

    private fun observeEmail(binding: ActivityMainBinding) = with(binding) {
        lifecycleScope.launch {
            validationViewModel.isValidEmail.collect {
                emailContainer.txvMessageLabel.isVisible = !it
                emailContainer.txeFullName.background = if (!it) {
                    setUiBackGround(this@MainActivity, R.drawable.input_field_border_error)
                } else {
                    setUiBackGround(this@MainActivity, R.drawable.input_field_border)
                }
                emailContainer.txvMessageLabel.text =
                    getString(R.string.email_validation_message)
            }
        }
    }

    private fun observeMobile(binding: ActivityMainBinding) = with(binding) {
        lifecycleScope.launch {
            validationViewModel.isValidMobileNumber.collect {
                mobileContainer.txvMessageLabel.isVisible = !it
                mobileContainer.txeFullName.background = if (!it) {
                    setUiBackGround(this@MainActivity, R.drawable.input_field_border_error)
                } else {
                    setUiBackGround(this@MainActivity, R.drawable.input_field_border)
                }
                mobileContainer.txvMessageLabel.text =
                    getString(R.string.phone_validation_message)
            }
        }
    }

    private fun observeIfCanSubmit(binding: ActivityMainBinding) = with(binding) {
        lifecycleScope.launch {
            validationViewModel.canSubmit.collect {
                submitBtn.isEnabled = it
            }
        }
    }

    private fun observeAge(binding: ActivityMainBinding) = with(binding) {
        lifecycleScope.launch {
            validationViewModel.calculatedAge.collect {
                ageContainer.txeFullName.setText(it.toString())
                calendarContainer.txvMessageLabel.isVisible = it <= 17
                ageContainer.txeFullName.background =
                    if (it <= 17) {
                        setUiBackGround(this@MainActivity, R.drawable.input_field_border_error)
                    } else {
                        setUiBackGround(this@MainActivity, R.drawable.input_field_border)
                    }
                calendarContainer.txvMessageLabel.text = getString(R.string.age_validation_message)
            }
        }
    }

    private fun showCalendarPicker(binding: ActivityMainBinding) = with(binding) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DATE)
        val datePickerDialog =
            DatePickerDialog(
                this@MainActivity,
                DatePickerDialog.OnDateSetListener { view, years, months, dayOfMonth ->
                    calendar.set(years, months, dayOfMonth)
                    val date = getString(
                        R.string.calendar_date, "${months + 1}",
                        dayOfMonth.toString(),
                        years.toString()
                    )
                    calendarContainer.txeFullName.setText(date)
                    validationViewModel.onChangeBirthDate(years, dayOfMonth, months + 1)
                },
                year,
                month, day
            )
        datePickerDialog.datePicker.maxDate = Date().time
        datePickerDialog.show()
    }
}