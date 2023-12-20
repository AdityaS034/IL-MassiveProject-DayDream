package com.coding.meet.medistockapp

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.coding.meet.medistockapp.databinding.ActivityFormMasukBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormMasukActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormMasukBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormMasukBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        supportActionBar?.title = "Tambah Obat Masuk"

        binding.btnAddin.setOnClickListener{
            saveData()
        }

        binding.tglmasukView.setOnClickListener{
            val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                binding.tglmasukView.text = dateToString(year, month, dayOfMonth)
            }
            dateDialog(this, datePicker).show()
        }

        binding.tglexpView.setOnClickListener{
            val datePicker = DatePickerDialog.OnDateSetListener{view, year, month, dayOfMonth ->
                binding.tglexpView.text = dateToString(year, month, dayOfMonth)
            }
            dateDialog(this, datePicker).show()
        }
    }

    fun saveData(){
        with(binding){
            val nobat = txtKodeobat.text.toString()
            val nama = txtNamaobat.text.toString()
            val merk = txtMerkobat.text.toString()
            val jml = txtJmlmasuk.text.toString()
            val unit = txtSatuan.text.toString()
            val masuk = tglmasukView.text.toString()
            val exp = tglexpView.text.toString()
            val describe = txtDeskripsi.text.toString()

            RClient.instances.createData(nobat,nama,merk,jml,unit,masuk,exp,describe).enqueue(object  : Callback<ResponCreateMasuk>{
                override fun onResponse(
                    call: Call<ResponCreateMasuk>,
                    response: Response<ResponCreateMasuk>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(applicationContext, "${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        txtKodeobat.setError(jsonObj.getString("message"))
                    }
                }

                override fun onFailure(call: Call<ResponCreateMasuk>, t: Throwable) {

                }

            })

        }
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