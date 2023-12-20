package com.coding.meet.medistockapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.meet.medistockapp.ObatMasukAdapter
import com.coding.meet.medistockapp.ObatMasukData
import com.coding.meet.medistockapp.R
import com.coding.meet.medistockapp.RClient
import com.coding.meet.medistockapp.ResponseDataMasuk
import com.coding.meet.medistockapp.databinding.FragmentDataObatMasukBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataObatMasuk : Fragment() {

    private var _binding: FragmentDataObatMasukBinding? = null
    private val binding get() = _binding!!

    private val listObatMasuk = ArrayList<ObatMasukData>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataObatMasukBinding.inflate(inflater, container, false)
        val view = binding.root

        getDataObatMasuk()

        return view

    }

    override fun onStart() {
        super.onStart()
        getDataObatMasuk()
    }

    fun getDataObatMasuk(){
        binding.rvDatamasuk.setHasFixedSize(true)
        binding.rvDatamasuk.layoutManager =LinearLayoutManager(context)

        val bundle = arguments
        val cari = bundle?.getString("cari")

        binding.progressBar.visibility
        RClient.instances.getData(cari).enqueue(object : Callback<ResponseDataMasuk>{
            override fun onResponse(
                call: Call<ResponseDataMasuk>,
                response: Response<ResponseDataMasuk>
            ) {
                if(response.isSuccessful){
                    listObatMasuk.clear()
                    response.body()?.let { listObatMasuk.addAll(it.data) }

                    val adapter = ObatMasukAdapter(listObatMasuk, requireContext())
                    binding.rvDatamasuk.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }

            override fun onFailure(call: Call<ResponseDataMasuk>, t: Throwable) {
            }

        })
    }
}