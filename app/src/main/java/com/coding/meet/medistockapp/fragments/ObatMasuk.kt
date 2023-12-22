package com.coding.meet.medistockapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coding.meet.medistockapp.FormMasukActivity
import com.coding.meet.medistockapp.R
import com.coding.meet.medistockapp.databinding.FragmentDataObatMasukBinding
import com.coding.meet.medistockapp.databinding.FragmentObatMasukBinding


class ObatMasuk : Fragment() {
    private var _binding: FragmentObatMasukBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentObatMasukBinding.inflate(inflater, container, false)
        val view = binding.root

        showDataFragment()

        binding.txtCari.setOnKeyListener(View.OnKeyListener{v,keyCode,event->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                showDataFragment()
                return@OnKeyListener true
            }
            false
        })

        binding.btnAdd.setOnClickListener {
            val intent = Intent(requireActivity(), FormMasukActivity::class.java)
            startActivity(intent)
        }


        return view
    }



    fun showDataFragment(){
        val mFragmentManager = childFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = DataObatMasuk()

        val textCari = binding.txtCari.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle

        mFragmentTransaction.replace(R.id.fl_data, mFragment).commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}