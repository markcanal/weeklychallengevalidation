package com.example.validationpoc

import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var spinner: Spinner? = null
    private var userNameContainer: View? = null
    private var userNameLabel: TextView? = null
    private var userNameError: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initViews()
        setContentView(R.layout.activity_main)
    }

    private fun initViews() {
        userNameContainer = findViewById(R.id.full_name_container)
        userNameLabel = findViewById<TextView>(R.id.txv_label)
        userNameError = findViewById(R.id.txv_message_label)
        userNameLabel.text = applicationContext.getString(R.string.full_name_label)

        val emailContainer: View = findViewById(R.id.email_container)
        val emailLabel: TextView = emailContainer.findViewById(R.id.txv_label)
        val emailError: TextView = emailContainer.findViewById(R.id.txv_message_label)
        userNameLabel.text = applicationContext.getString(R.string.email_label)
//        spinner = findViewById(R.id.gender)
//        ArrayAdapter.createFromResource(
//            this, R.array.gender_array, android.R.layout.simple_spinner_dropdown_item
//        ).also { arrayAdapter ->
//            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner?.adapter = arrayAdapter
//        }
    }
}