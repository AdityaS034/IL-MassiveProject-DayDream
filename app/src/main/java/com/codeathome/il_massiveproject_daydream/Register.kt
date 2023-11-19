package com.codeathome.il_massiveproject_daydream

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeathome.il_massiveproject_daydream.databinding.ActivityLoginBinding
import com.codeathome.il_massiveproject_daydream.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_register)
    }
}