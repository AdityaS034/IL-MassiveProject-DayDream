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
import com.coding.meet.medistockapp.databinding.FragmentObatKeluarBinding

class ObatKeluar : Fragment() {
    private var _binding: FragmentObatKeluarBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentObatKeluarBinding.inflate(inflater, container, false)
        val view = binding.root

        showDataFragment()

        binding.txtCarikeluar.setOnKeyListener(View.OnKeyListener{v,keyCode,event->
            if(keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP){
                showDataFragment()
                return@OnKeyListener true
            }
            false
        })

        binding.btnAddkl.setOnClickListener {
            val intent = Intent(requireActivity(), FormKeluarActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    fun showDataFragment(){
        val mFragmentManager = childFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = DataObatKeluar()

        val textCari = binding.txtCarikeluar.text
        val mBundle = Bundle()
        mBundle.putString("cari", textCari.toString())
        mFragment.arguments = mBundle

        mFragmentTransaction.replace(R.id.fl_datakeluar, mFragment).commit()
    }

}