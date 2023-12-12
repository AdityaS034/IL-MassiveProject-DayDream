package com.codeathome.il_massiveproject_daydream

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codeathome.il_massiveproject_daydream.databinding.ActivityTambahObatMasukBinding

class TambahObatKeluar : AppCompatActivity() {
    private lateinit var binding: ActivityTambahObatMasukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahObatMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSimpan.setOnClickListener {
            startActivity(Intent(this, ObatKeluarFragment::class.java))
        }
    }
}