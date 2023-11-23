package com.codeathome.il_massiveproject_daydream

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.codeathome.il_massiveproject_daydream.databinding.ActivityLoginBinding
import com.codeathome.il_massiveproject_daydream.databinding.ActivityTambahObatMasukBinding

class TambahObatMasuk : AppCompatActivity() {
    private lateinit var binding: ActivityTambahObatMasukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahObatMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpan.setOnClickListener {
            startActivity(Intent(this, ObatMasukFragment::class.java))
        }


    }


}