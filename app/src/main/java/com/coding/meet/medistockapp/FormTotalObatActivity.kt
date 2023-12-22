package com.coding.meet.medistockapp

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.coding.meet.medistockapp.databinding.ActivityFormTotalObatBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FormTotalObatActivity : AppCompatActivity() {
    private lateinit var binding : ActivityFormTotalObatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormTotalObatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddttl.setOnClickListener{
            saveData()
        }

    }

    fun saveData(){
        with(binding){
            val nomor = txtKodeobat.text.toString()
            val nama = txtNamaobat.text.toString()
            val satuan = txtSatuan.text.toString()
            val stokawal = txtStokawal.text.toString()
            val obatmasuk = txtObatmasuk.text.toString()
            val obatkeluar = txtObatkeluar.text.toString()
            val stokakhir = txtStokakhir.text.toString()

            RClient.instancesttl.createData(nomor, nama, satuan, stokawal, obatmasuk, obatkeluar, stokakhir).enqueue(object  :
                Callback<ResponCreateTotalObat> {
                override fun onResponse(
                    call: Call<ResponCreateTotalObat>,
                    response: Response<ResponCreateTotalObat>
                ) {
                    if(response.isSuccessful){
                        Toast.makeText(applicationContext, "${response.body()?.pesan}", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        val jsonObj = JSONObject(response.errorBody()!!.charStream().readText())
                        txtKodeobat.setError(jsonObj.getString("message"))
                    }
                }

                override fun onFailure(call: Call<ResponCreateTotalObat>, t: Throwable) {

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