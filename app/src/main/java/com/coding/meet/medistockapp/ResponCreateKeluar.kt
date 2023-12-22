package com.coding.meet.medistockapp

import com.google.gson.annotations.SerializedName

data class ResponCreateKeluar(

    @SerializedName("status") val stt:Int,
    @SerializedName("error") val e:Boolean,
    @SerializedName("message") val pesan:String,
)
