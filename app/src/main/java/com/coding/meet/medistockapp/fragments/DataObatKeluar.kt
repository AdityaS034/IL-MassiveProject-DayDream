package com.coding.meet.medistockapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.meet.medistockapp.Adapter.ObatKeluarAdapter
import com.coding.meet.medistockapp.ObatKeluarData
import com.coding.meet.medistockapp.RClient
import com.coding.meet.medistockapp.ResponseDataKeluar
import com.coding.meet.medistockapp.databinding.FragmentDataObatKeluarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataObatKeluar : Fragment() {
    private var _binding: FragmentDataObatKeluarBinding? = null
    private val binding get() = _binding!!
    private val listObatKeluar = ArrayList<ObatKeluarData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataObatKeluarBinding.inflate(inflater, container, false)
        val view = binding.root

        getDataObatKeluar()

        return view
    }

    fun getDataObatKeluar(){
        binding.rvDatakeluar.setHasFixedSize(true)
        binding.rvDatakeluar.layoutManager = LinearLayoutManager(context)

        val bundle = arguments
        val cari = bundle?.getString("cari")

        binding.progressBar.visibility
        RClient.instancesklr.getData(cari).enqueue(object : Callback<ResponseDataKeluar> {
            override fun onResponse(
                call: Call<ResponseDataKeluar>,
                response: Response<ResponseDataKeluar>
            ) {
                if(response.isSuccessful){
                    listObatKeluar.clear()
                    response.body()?.let { listObatKeluar.addAll(it.data) }

                    val adapter = ObatKeluarAdapter(listObatKeluar, requireContext())
                    binding.rvDatakeluar.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }

            override fun onFailure(call: Call<ResponseDataKeluar>, t: Throwable) {
            }

        })
    }

    override fun onStart() {
        super.onStart()
        getDataObatKeluar()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}