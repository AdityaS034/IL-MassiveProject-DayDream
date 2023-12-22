package com.coding.meet.medistockapp

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coding.meet.medistockapp.databinding.ActivityFormKeluarBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormKeluarActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormKeluarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormKeluarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddout.setOnClickListener{
            saveData()
        }

        binding.tglkeluarView.setOnClickListener{
            val datePicker = DatePickerDialog.OnDateSetListener{ view, year, month, dayOfMonth ->
                binding.tglkeluarView.text = dateToString(year, month, dayOfMonth)
            }
            dateDialog(this, datePicker).show()
        }
    }

    fun saveData(){
        with(binding){
            val nobat = txtKodeobat.text.toString()
            val nama = txtNamaobat.text.toString()
            val merk = txtMerkobat.text.toString()
            val jml = txtJmlkeluar.text.toString()
            val unit = txtSatuan.text.toString()
            val keluar = tglkeluarView.text.toString()
            val status = txtStatus.text.toString()
            val note = txtCatatan.text.toString()

            RClient.instancesklr.createData(nobat,nama,merk,jml,unit,keluar,status,note).enqueue(object  :
                Callback<ResponCreateKeluar> {
                override fun onResponse(
                    call: Call<ResponCreateKeluar>,
                    response: Response<ResponCreateKeluar>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(applicationContext, "${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        txtKodeobat.setError(jsonObj.getString("message"))
                    }
                }

                override fun onFailure(call: Call<ResponCreateKeluar>, t: Throwable) {

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