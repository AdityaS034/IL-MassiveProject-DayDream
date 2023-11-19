package com.codeathome.il_massiveproject_daydream

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeathome.il_massiveproject_daydream.databinding.ActivityDashboardBinding
import com.codeathome.il_massiveproject_daydream.databinding.ActivityRegisterBinding

class Dashboard : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_dashboard)
    }
}