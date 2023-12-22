package com.coding.meet.medistockapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.coding.meet.medistockapp.databinding.ActivityDetailObatMasukBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailObatMasuk : AppCompatActivity() {
    private lateinit var binding : ActivityDetailObatMasukBinding
    private var b:Bundle? = null
    private val listDataMasuk = ArrayList<ObatMasukData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailObatMasukBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        b = intent.extras
        val nobat = b?.getString("nobat")

        nobat?.let { getDataDetail(it) }

        binding.btnHapus.setOnClickListener{
            nobat?.let { it1 -> deleteData(it1) }
        }

        binding.btnEdit.setOnClickListener {
            startActivity(Intent(this,FormEditMasuk::class.java).apply {
                putExtra("nobat",nobat)
            })
        }

    }

    override fun onRestart() {
        super.onRestart()
        this.recreate()
    }

    fun getDataDetail(nobat:String){
        RClient.instances.getData(nobat).enqueue(object : Callback<ResponseDataMasuk>{
            override fun onResponse(
                call: Call<ResponseDataMasuk>,
                response: Response<ResponseDataMasuk>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { listDataMasuk.addAll(it.data) }
                    with(binding){
                        tvNobat.text = listDataMasuk[0].nobat
                        tvNama.text = listDataMasuk[0].nama
                        tvMerk.text = listDataMasuk[0].merk
                        tvJml.text = listDataMasuk[0].jml
                        tvSatuan.text = listDataMasuk[0].satuan
                        tvTglmasuk.text = listDataMasuk[0].masuk
                        tvTglexp.text = listDataMasuk[0].exp
                        tvDeskripsi.text = listDataMasuk[0].describe
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataMasuk>, t: Throwable) {

            }

        })
    }

    fun deleteData(nobat: String) {
        val builder = AlertDialog.Builder(this@DetailObatMasuk)
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
        RClient.instances.deleteData(nobat).enqueue(object : Callback<ResponCreateMasuk>{
            override fun onResponse(
                call: Call<ResponCreateMasuk>,
                response: Response<ResponCreateMasuk>
            ) {
                if(response.isSuccessful){
                    Toast.makeText(applicationContext, "Data berhasil dihapus",Toast.LENGTH_LONG).show()
                    finish()
                }
            }

            override fun onFailure(call: Call<ResponCreateMasuk>, t: Throwable) {

            }
        })
    }
}