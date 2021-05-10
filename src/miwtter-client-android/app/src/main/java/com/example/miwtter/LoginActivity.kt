package com.example.miwtter

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.miwtter.databinding.ActivityLoginBinding
import es.uniovi.miw.miwtter.Miwtter
import es.uniovi.miw.miwtter.clients.UsersServiceClient
import es.uniovi.miw.miwtter.persistence.Settings

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.loginBtn.setOnClickListener{
            if (isValid()) {
                val service = UsersServiceClient
                val username = binding.username.editText?.text.toString()
                val password = binding.password.editText?.text.toString()
                val request = Miwtter.LoginUserRequest.newBuilder()
                        .setUsername(username)
                        .setPassword(password)
                        .build()
                val response = service.login(request)
                when (response.responseStatus) {
                    Miwtter.LoginUserResponse.ResponseStatus.SUCCESS -> {

                        val findRequest = Miwtter.FindUserRequest.newBuilder()
                            .setFindPolicy(Miwtter.FindUserRequest.FindPolicy.AND)
                            .setUsername(username)
                            .build()

                        val user = service.find(findRequest)
                        Settings(this).username = username
                        Settings(this).name = user.userList.get(0).name
                        Settings(this).surname = user.userList.get(0).surname

                        startActivity(Intent(this, HomeActivity::class.java))
                        Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show()
                    }

                    Miwtter.LoginUserResponse.ResponseStatus.INCORRECT_USERNAME_OR_PASSWORD -> {
                        Toast.makeText(this, "Username or password are incorrect", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
        binding.signUpTxt.setOnClickListener{ startActivity(Intent(this,SignUpActivity::class.java)) }
    }


    private fun isValid():Boolean = validateUsername() && validatePassword()


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