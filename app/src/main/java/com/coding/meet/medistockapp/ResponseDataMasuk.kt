package com.coding.meet.medistockapp

import com.google.gson.annotations.SerializedName


data class ResponseDataMasuk(
    @SerializedName("status") val stt:String,
    val totaldata:Int,
    val data:List<ObatMasukData>
)
