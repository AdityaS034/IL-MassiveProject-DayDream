package com.coding.meet.medistockapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.coding.meet.medistockapp.RClient
import com.coding.meet.medistockapp.ResponseTotalObat
import com.coding.meet.medistockapp.Adapter.TotalObatAdapter
import com.coding.meet.medistockapp.TotalObatData
import com.coding.meet.medistockapp.databinding.FragmentDataTotalObatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DataTotalObat : Fragment() {
    private var _binding: FragmentDataTotalObatBinding? = null
    private val binding get() = _binding!!
    private val listTotalObat = ArrayList<TotalObatData>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataTotalObatBinding.inflate(inflater, container, false)
        val view = binding.root

        getDataTotalObat()

        return view
    }

    fun getDataTotalObat(){
        binding.rvTotaldata.setHasFixedSize(true)
        binding.rvTotaldata.layoutManager = LinearLayoutManager(context)

        val bundle = arguments
        val cari = bundle?.getString("cari")

        binding.progressBar.visibility
        RClient.instancesttl.getData(cari).enqueue(object : Callback<ResponseTotalObat>{
            override fun onResponse(
                call: Call<ResponseTotalObat>,
                response: Response<ResponseTotalObat>
            ) {
                if(response.isSuccessful){
                    listTotalObat.clear()
                    response.body()?.let { listTotalObat.addAll(it.data) }

                    val adapter = TotalObatAdapter(listTotalObat, requireContext())
                    binding.rvTotaldata.adapter = adapter
                    adapter.notifyDataSetChanged()
                    binding.progressBar.isVisible = false
                }
            }

            override fun onFailure(call: Call<ResponseTotalObat>, t: Throwable) {
            }

        })
    }

    override fun onStart() {
        super.onStart()
        getDataTotalObat()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}