package com.codeathome.il_massiveproject_daydream

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.codeathome.il_massiveproject_daydream.databinding.ActivityLoginBinding

class Login : AppCompatActivity(){
    private lateinit var binding: ActivityLoginBinding
    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        binding.haventAccount.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        etUsername = binding.edtEmail
        etPassword = binding.edtPassword
        setUser()


    }

    private fun setUser() {
        val bundle = intent.extras
        if (bundle != null) {
            etUsername.setText(bundle.getString("username"))
            etPassword.setText(bundle.getString("password"))
        }
    }



}




