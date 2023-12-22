package com.coding.meet.medistockapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coding.meet.medistockapp.FormKeluarActivity
import com.coding.meet.medistockapp.R
import com.coding.meet.medistockapp.databinding.FragmentTotalObatBinding


class TotalObat : Fragment() {

    private var _binding: FragmentTotalObatBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTotalObatBinding.inflate(inflater, container, false)
        val view = binding.root

        showDataFragment()

        binding.txtCaritotal.setOnKeyListener(View.OnKeyListener{v,keyCode,event->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                showDataFragment()
                return@OnKeyListener true
            }
            false
        })

        binding.btnAddtl.setOnClickListener {
            val intent = Intent(requireActivity(), FormKeluarActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun showDataFragment(){
        val mFragmentManager = childFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = DataObatKeluar()

        val textCari = binding.txtCaritotal.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle

        mFragmentTransaction.replace(R.id.fl_datatotal, mFragment).commit()
    }

}