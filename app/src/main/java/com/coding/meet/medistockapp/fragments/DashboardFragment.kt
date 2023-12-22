package com.coding.meet.medistockapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.coding.meet.medistockapp.Login
import com.coding.meet.medistockapp.R
import com.coding.meet.medistockapp.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val view = binding.root

        // Set click listeners for the buttons
        binding.btnobat.setOnClickListener { navigateToFragment(TotalObat()) }
        binding.btnobat1.setOnClickListener { navigateToFragment(ObatMasuk()) }
        binding.btnobat2.setOnClickListener { navigateToFragment(ObatKeluar()) }

        return view
    }

    private fun navigateToFragment(fragment: Fragment) {
        // Replace the current fragment with the specified fragment
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }
}
