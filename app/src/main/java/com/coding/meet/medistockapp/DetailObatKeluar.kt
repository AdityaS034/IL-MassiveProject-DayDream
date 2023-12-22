package com.coding.meet.medistockapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.coding.meet.medistockapp.databinding.ActivityDetailObatKeluarBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailObatKeluar : AppCompatActivity() {
    private lateinit var binding : ActivityDetailObatKeluarBinding
    private var b:Bundle? = null
    private val listDataKeluar = ArrayList<ObatKeluarData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailObatKeluarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        b = intent.extras
        val nobat = b?.getString("nobat")

        nobat?.let { getDataDetail(it) }

        binding.btnHapus.setOnClickListener{
            nobat?.let { it1 -> deleteData(it1) }
        }

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this,FormEditKeluar::class.java).apply {
                putExtra("nobat",nobat)
            })
        }
    }

    fun getDataDetail(nobat:String){
        RClient.instancesklr.getData(nobat).enqueue(object : Callback<ResponseDataKeluar> {
            override fun onResponse(
                call: Call<ResponseDataKeluar>,
                response: Response<ResponseDataKeluar>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { listDataKeluar.addAll(it.data) }
                    with(binding){
                        tvNobat.text = listDataKeluar[0].nobat
                        tvNama.text = listDataKeluar[0].nama
                        tvMerk.text = listDataKeluar[0].merk
                        tvJml.text = listDataKeluar[0].jml
                        tvSatuan.text = listDataKeluar[0].satuan
                        tvTglkeluar.text = listDataKeluar[0].keluar
                        tvStatus.text = listDataKeluar[0].status
                        tvCatatan.text = listDataKeluar[0].describe
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataKeluar>, t: Throwable) {

            }

        })
    }

    fun deleteData(nobat: String) {
        val builder = AlertDialog.Builder(this@DetailObatKeluar)
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
        RClient.instancesklr.deleteData(nobat).enqueue(object : Callback<ResponCreateKeluar>{
            override fun onResponse(
                call: Call<ResponCreateKeluar>,
                response: Response<ResponCreateKeluar>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_LONG).show()
                    finish() // Tambah notifikasi di sini
                }
            }

            override fun onFailure(call: Call<ResponCreateKeluar>, t: Throwable) {

            }
        })
    }
}