package com.coding.meet.medistockapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.coding.meet.medistockapp.databinding.ActivityDetailTotalObatBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailTotalObat : AppCompatActivity() {
    private lateinit var binding : ActivityDetailTotalObatBinding
    private var b:Bundle? = null
    private val listTotalData = ArrayList<TotalObatData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailTotalObatBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        b = intent.extras
        val nobat = b?.getString("nobat")

        nobat?.let { getDataDetail(it) }

        binding.btnHapus.setOnClickListener{
            nobat?.let { it1 -> deleteData(it1) }
        }

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this,FormEditTotalObat::class.java).apply {
                putExtra("nobat",nobat)
            })
        }
    }

    fun getDataDetail(nobat:String){
        RClient.instancesttl.getData(nobat).enqueue(object : Callback<ResponseTotalObat> {
            override fun onResponse(
                call: Call<ResponseTotalObat>,
                response: Response<ResponseTotalObat>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { listTotalData.addAll(it.data) }
                    with(binding){
                        tvNobat.text = listTotalData[0].nobat
                        tvNama.text = listTotalData[0].nama
                        tvSatuan.text = listTotalData[0].satuan
                        tvAwal.text = listTotalData[0].stokawal
                        tvMasuk.text = listTotalData[0].obatmasuk
                        tvKeluar.text = listTotalData[0].obatkeluar
                        tvAkhir.text = listTotalData[0].stokakhir
                    }
                }
            }

            override fun onFailure(call: Call<ResponseTotalObat>, t: Throwable) {

            }

        })
    }

    fun deleteData(nobat: String) {
        val builder = AlertDialog.Builder(this@DetailTotalObat)
        builder.setMessage("Yakin data obat ini dihapus ?")
            .setCancelable(false)
            .setPositiveButton("Ya, Hapus !"){dialog, id->
                doDeleteData(nobat)
            }
            .setNegativeButton("Tidak"){dialog,id->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }

    private fun doDeleteData(nobat: String) {
        RClient.instancesttl.deleteData(nobat).enqueue(object : Callback<ResponCreateTotalObat>{
            override fun onResponse(
                call: Call<ResponCreateTotalObat>,
                response: Response<ResponCreateTotalObat>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                    finish() // Tambah notifikasi di sini
                }
            }

            override fun onFailure(call: Call<ResponCreateTotalObat>, t: Throwable) {

            }
        })
    }
}