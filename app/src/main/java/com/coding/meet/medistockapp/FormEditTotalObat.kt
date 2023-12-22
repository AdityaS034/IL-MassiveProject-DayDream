package com.coding.meet.medistockapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coding.meet.medistockapp.databinding.ActivityFormEditTotalObatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormEditTotalObat : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditTotalObatBinding
    private var b:Bundle? = null
    private val listTotalObatData = ArrayList<TotalObatData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormEditTotalObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val nobat = b?.getString("nobat")

        nobat?.let { getDetailData(it) }

        binding.btnAddttl.setOnClickListener {
            with(binding){
                val nomor = txtKodeobat.text.toString()
                val nama = txtNamaobat.text.toString()
                val satuan = txtSatuan.text.toString()
                val stokawal = txtStokawal.text.toString()
                val obatmasuk = txtObatmasuk.text.toString()
                val obatkeluar = txtObatkeluar.text.toString()
                val stokakhir = txtStokakhir.text.toString()

                RClient.instancesttl.updateData(nomor, nama, satuan, stokawal, obatmasuk, obatkeluar, stokakhir).enqueue(object :
                    Callback<ResponCreateTotalObat> {
                    override fun onResponse(
                        call: Call<ResponCreateTotalObat>,
                        response: Response<ResponCreateTotalObat>
                    ) {
                        if(response.isSuccessful){
                            Toast.makeText(applicationContext,"${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<ResponCreateTotalObat>, t: Throwable) {

                    }

                })
            }
        }
    }

    fun getDetailData(nobat:String){
        RClient.instancesttl.getData(nobat).enqueue(object : Callback<ResponseTotalObat> {
            override fun onResponse(
                call: Call<ResponseTotalObat>,
                response: Response<ResponseTotalObat>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { listTotalObatData.addAll(it.data) }
                    with(binding){
                        txtKodeobat.setText(listTotalObatData[0].nobat)
                        txtNamaobat.setText(listTotalObatData[0].nama)
                        txtSatuan.setText(listTotalObatData[0].satuan)
                        txtStokawal.setText(listTotalObatData[0].stokawal)
                        txtObatmasuk.setText(listTotalObatData[0].obatmasuk)
                        txtObatkeluar.setText(listTotalObatData[0].obatkeluar)
                        txtStokakhir.setText(listTotalObatData[0].stokakhir)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTotalObat>, t: Throwable) {

            }

        })
    }
}