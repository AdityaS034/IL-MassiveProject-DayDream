package com.coding.meet.medistockapp

import com.google.gson.annotations.SerializedName


data class ResponseDataKeluar(
    @SerializedName("status") val stt:String,
    val totaldata:Int,
    val data:List<ObatKeluarData>
)
