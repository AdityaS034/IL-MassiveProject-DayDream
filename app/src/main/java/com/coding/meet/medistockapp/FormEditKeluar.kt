package com.coding.meet.medistockapp

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coding.meet.medistockapp.databinding.ActivityFormEditKeluarBinding
import com.coding.meet.medistockapp.databinding.ActivityFormEditMasukBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormEditKeluar : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditKeluarBinding
    private var b:Bundle? = null
    private val listDataKeluar = ArrayList<ObatKeluarData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormEditKeluarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val nobat = b?.getString("nobat")

        binding.tglkeluarView.setOnClickListener{
            val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                binding.tglkeluarView.text = dateToString(year, month, dayOfMonth)
            }
            dateDialog(this, datePicker).show()
        }

        nobat?.let { getDetailData(it) }

        binding.btnAddout.setOnClickListener {
            with(binding){
                val nomor = txtKodeobat.text.toString()
                val nama = txtNamaobat.text.toString()
                val merk = txtMerkobat.text.toString()
                val jumlah = txtJmlkeluar.text.toString()
                val unit = txtSatuan.text.toString()
                val keluar = tglkeluarView.text.toString()
                val status = tglkeluarView.text.toString()
                val describe = txtCatatan.text.toString()

                RClient.instancesklr.updateData(nomor, nama, merk, jumlah, unit, keluar, status, describe).enqueue(object :
                    Callback<ResponCreateKeluar> {
                    override fun onResponse(
                        call: Call<ResponCreateKeluar>,
                        response: Response<ResponCreateKeluar>
                    ) {
                        if(response.isSuccessful){
                            Toast.makeText(applicationContext,"${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<ResponCreateKeluar>, t: Throwable) {

                    }

                })
            }
        }
    }

    fun getDetailData(nobat:String){
        RClient.instancesklr.getData(nobat).enqueue(object : Callback<ResponseDataKeluar> {
            override fun onResponse(
                call: Call<ResponseDataKeluar>,
                response: Response<ResponseDataKeluar>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { listDataKeluar.addAll(it.data) }
                    with(binding){
                        txtKodeobat.setText(listDataKeluar[0].nobat)
                        txtNamaobat.setText(listDataKeluar[0].nama)
                        txtMerkobat.setText(listDataKeluar[0].merk)
                        txtJmlkeluar.setText(listDataKeluar[0].jml)
                        txtSatuan.setText(listDataKeluar[0].satuan)

                        tglkeluarView.setText(listDataKeluar[0].keluar)

                        txtCatatan.setText(listDataKeluar[0].describe)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataKeluar>, t: Throwable) {

            }

        })
    }

    private fun dateToString(year: Int, month: Int, dayOfMonth: Int): String {
        return year.toString()+"-"+(month+1)+"-"+dayOfMonth.toString()

    }

    private fun dateDialog(context: Context, datePicker: DatePickerDialog.OnDateSetListener): DatePickerDialog {
        val calendar = Calendar.getInstance()
        return DatePickerDialog(
            context,datePicker,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH],
        )
    }
}