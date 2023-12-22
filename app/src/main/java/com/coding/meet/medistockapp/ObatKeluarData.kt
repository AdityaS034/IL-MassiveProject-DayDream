package com.coding.meet.medistockapp

import com.google.gson.annotations.SerializedName

data class ObatKeluarData(
    @SerializedName("kodeobat") val nobat:String,
    @SerializedName("namaobat") val nama:String,
    @SerializedName("merkobat") val merk:String,
    @SerializedName("jmlkeluar") val jml:String,
    @SerializedName("satuan") val satuan:String,
    @SerializedName("tglkeluar") val keluar:String,
    @SerializedName("status") val status:String,
    @SerializedName("catatan") val describe:String,
)
