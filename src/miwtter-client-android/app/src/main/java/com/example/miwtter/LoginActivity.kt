package com.example.miwtter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.miwtter.databinding.ActivityLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener{ startActivity(Intent(this,HomeActivity::class.java)) }
        binding.signUpTxt.setOnClickListener{ startActivity(Intent(this,SignUpActivity::class.java)) }
    }

}