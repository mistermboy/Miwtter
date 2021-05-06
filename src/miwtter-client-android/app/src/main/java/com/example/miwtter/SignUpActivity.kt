package com.example.miwtter

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.miwtter.databinding.ActivitySignupBinding
import com.google.android.material.textfield.TextInputLayout
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.UsersServiceClient


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginTxt.setOnClickListener{ startActivity(Intent(this,LoginActivity::class.java)) }
        binding.siginBtn.setOnClickListener{
            val service = UsersServiceClient()
            val name = binding.name.editText?.text.toString()
            val surname = binding.surname.editText?.text.toString()
            val username = binding.username.editText?.text.toString()
            val password = binding.password.editText?.text.toString()
            println(name)
            println(surname)
            println(username)
            println(password)
            val request = Miwtter.RegisterUserRequest.newBuilder()
                    .setName(name)
                    .setSurname(surname)
                    .setUsername(username)
                    .setPassword(password)
                    .build()
            val response = service.register(request)
            Log.i("REGISTER_RESPONSE",response.responseStatus.toString())
            when(response.responseStatus){
                Miwtter.RegisterUserResponse.ResponseStatus.USER_CREATED -> {
                    Log.i("USER_CREATED","Name: "+name+" Surname: "+surname+" Username: "+username+" Password: "+password)
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }

        }
    }
}