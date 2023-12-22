package com.coding.meet.medistockapp

import com.google.gson.annotations.SerializedName

data class ObatMasukData(
    @SerializedName("kodeobat") val nobat:String,
    @SerializedName("namaobat") val nama:String,
    @SerializedName("merkobat") val merk:String,
    @SerializedName("jmlmasuk") val jml:String,
    @SerializedName("satuan") val satuan:String,
    @SerializedName("tglmasuk") val masuk:String,
    @SerializedName("tglexp") val exp:String,
    @SerializedName("deskripsi") val describe:String,
)
