package com.example.miwtter

import android.R
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
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


            if (isValid()) {
                val service = UsersServiceClient
                val name = binding.name.editText?.text.toString()
                val surname = binding.surname.editText?.text.toString()
                val username = binding.username.editText?.text.toString()
                val password = binding.password.editText?.text.toString()
                val request = Miwtter.RegisterUserRequest.newBuilder()
                        .setName(name)
                        .setSurname(surname)
                        .setUsername(username)
                        .setPassword(password)
                        .build()
                val response = service.register(request)
                Log.i("REGISTER_RESPONSE", response.responseStatus.toString())
                when (response.responseStatus) {
                    Miwtter.RegisterUserResponse.ResponseStatus.USER_CREATED -> {
                        Log.i("USER_CREATED", "Name: " + name + " Surname: " + surname + " Username: " + username + " Password: " + password)
                        startActivity(Intent(this, LoginActivity::class.java))
                        Toast.makeText(this, "Succesfully registered!", Toast.LENGTH_SHORT).show()
                    }
                    Miwtter.RegisterUserResponse.ResponseStatus.USERNAME_ALREADY_EXISTS ->{
                        binding.username.error = "Username already exists"
                        binding.username.requestFocus()
                    }
                }
            }

        }
    }


    private fun isValid():Boolean = validateName() && validateSurname() && validateUsername() && validatePassword()

    private fun validateName(): Boolean {
        if (binding.name.editText?.text.toString().trim().isEmpty()) {
            binding.name.error = "Required Field!"
            binding.name.requestFocus()
            return false
        }
        binding.name.isErrorEnabled = false
        return true
    }

    private fun validateSurname(): Boolean {
        if (binding.surname.editText?.text.toString().trim().isEmpty()) {
            binding.surname.error = "Required Field!"
            binding.surname.requestFocus()
            return false
        }
        binding.surname.isErrorEnabled = false
        return true
    }

    private fun validateUsername(): Boolean {
        if (binding.username.editText?.text.toString().trim().isEmpty()) {
            binding.username.error = "Required Field!"
            binding.username.requestFocus()
            return false
        }
        binding.username.isErrorEnabled = false
        return true
    }

    private fun validatePassword(): Boolean {
        if (binding.password.editText?.text.toString().trim().isEmpty()) {
            binding.password.error = "Required Field!"
            binding.password.requestFocus()
            return false
        }
        binding.password.isErrorEnabled = false
        return true
    }

}