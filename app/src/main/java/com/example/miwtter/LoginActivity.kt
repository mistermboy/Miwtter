package com.example.miw.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.miwtter.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        Log.i("INFO",binding.loginBtn.text.toString())
        binding.loginBtn.setOnClickListener{
            Log.i("INFO","CLICK")
            Log.i("INFO","CLICK")
            Log.i("INFO","CLICK")
            Log.i("INFO","CLICK")
            Log.i("INFO","CLICK")

            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

    }
}