package com.example.validationpoc

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.validationpoc.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        initViews(binding)
        setContentView(view)
    }

    private fun initViews(binding: ActivityMainBinding) {
        binding.fullNameContainer.txvLabel.text =
            applicationContext.getString(R.string.full_name_label)
        binding.emailContainer.txvLabel.text = applicationContext.getString(R.string.email_label)
        binding.mobileContainer.txvLabel.text =
            applicationContext.getString(R.string.mobile_number_label)
        binding.calendarContainer.txvLabel.text =
            applicationContext.getString(R.string.birthdate_label)

        binding.calendarContainer.txeFullName.isFocusable = false
        binding.calendarContainer.txeFullName.setOnClickListener {
            Toast.makeText(this, "Click me not", Toast.LENGTH_LONG).show()
        }
        binding.ageContainer.txvLabel.text = applicationContext.getString(R.string.age_label)
        binding.ageContainer.txeFullName.isFocusable = false
        binding.ageContainer.txeFullName.setText(
            applicationContext.getString(R.string.age_value_text, ""),

            )
        binding.genderSelect.adapter =
            ArrayAdapter.createFromResource(
                this,
                R.array.gender_array,
                android.R.layout.simple_spinner_dropdown_item
            )

    }
}