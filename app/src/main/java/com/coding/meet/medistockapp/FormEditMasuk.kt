package com.coding.meet.medistockapp

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coding.meet.medistockapp.databinding.ActivityFormEditMasukBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormEditMasuk : AppCompatActivity() {
    private lateinit var binding : ActivityFormEditMasukBinding
    private var b:Bundle? = null
    private val listDataMasuk = ArrayList<ObatMasukData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormEditMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        b = intent.extras
        val nobat = b?.getString("nobat")

        binding.tglmasukView.setOnClickListener{
            val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                binding.tglmasukView.text = dateToString(year, month, dayOfMonth)
            }
            dateDialog(this, datePicker).show()
        }

        binding.tglexpView.setOnClickListener{
            val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                binding.tglexpView.text = dateToString(year, month, dayOfMonth)
            }
            dateDialog(this, datePicker).show()
        }

        nobat?.let { getDetailData(it) }

        binding.btnAddin.setOnClickListener {
            with(binding){
                val nomor = txtKodeobat.text.toString()
                val nama = txtNamaobat.text.toString()
                val merk = txtMerkobat.text.toString()
                val jumlah = txtJmlmasuk.text.toString()
                val unit = txtSatuan.text.toString()
                val masuk = tglmasukView.text.toString()
                val exp = tglexpView.text.toString()
                val describe = txtDeskripsi.text.toString()

                RClient.instances.updateData(nomor, nama, merk, jumlah, unit, masuk, exp, describe).enqueue(object : Callback<ResponCreateMasuk>{
                    override fun onResponse(
                        call: Call<ResponCreateMasuk>,
                        response: Response<ResponCreateMasuk>
                    ) {
                        if(response.isSuccessful){
                            Toast.makeText(applicationContext,"${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }

                    override fun onFailure(call: Call<ResponCreateMasuk>, t: Throwable) {

                    }

                })
            }
        }
    }

    fun getDetailData(nobat:String){
        RClient.instances.getData(nobat).enqueue(object : Callback<ResponseDataMasuk> {
            override fun onResponse(
                call: Call<ResponseDataMasuk>,
                response: Response<ResponseDataMasuk>
            ) {
                if(response.isSuccessful){
                    response.body()?.let { listDataMasuk.addAll(it.data) }
                    with(binding){
                        txtKodeobat.setText(listDataMasuk[0].nobat)
                        txtNamaobat.setText(listDataMasuk[0].nama)
                        txtMerkobat.setText(listDataMasuk[0].merk)
                        txtJmlmasuk.setText(listDataMasuk[0].jml)
                        txtSatuan.setText(listDataMasuk[0].satuan)

                        tglmasukView.setText(listDataMasuk[0].masuk)
                        tglexpView.setText(listDataMasuk[0].exp)

                        txtDeskripsi.setText(listDataMasuk[0].describe)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseDataMasuk>, t: Throwable) {

            }

        })
    }

    private fun dateToString(year: Int, month: Int, dayOfMonth: Int): String {
        return year.toString()+"-"+(month+1)+"-"+dayOfMonth.toString()

    }

    private fun dateDialog(context: Context, datePicker: DatePickerDialog.OnDateSetListener):DatePickerDialog {
        val calendar = Calendar.getInstance()
        return DatePickerDialog(
            context,datePicker,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH],
        )
    }
}