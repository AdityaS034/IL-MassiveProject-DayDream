package com.coding.meet.medistockapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coding.meet.medistockapp.R
import com.coding.meet.medistockapp.databinding.FragmentObatMasukBinding

class ObatKeluar : Fragment() {
    private var _binding: FragmentObatMasukBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentObatMasukBinding.inflate(inflater, container, false)
        val view = binding.root




        return view
    }

}