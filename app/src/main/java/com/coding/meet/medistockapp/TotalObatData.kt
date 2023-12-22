package com.coding.meet.medistockapp

import com.google.gson.annotations.SerializedName

data class TotalObatData(
    @SerializedName("kodeobat") val nobat:String,
    @SerializedName("namaobat") val nama:String,
    @SerializedName("satuan") val satuan:String,
    @SerializedName("stokawal") val stokawal:String,
    @SerializedName("obatmasuk") val obatmasuk:String,
    @SerializedName("obatkeluar") val obatkeluar:String,
    @SerializedName("stokakhir") val stokakhir:String,
)
