package com.example.miw.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miwtter.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var activityBinding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivitySignupBinding.inflate(layoutInflater)
        val view = activityBinding.root
        setContentView(view)
    }
}