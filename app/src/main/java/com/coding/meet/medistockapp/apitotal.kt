package com.coding.meet.medistockapp

import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface apitotal {
    @GET("dataobat/{cari}")
    fun getData(@Path("cari") cari:String? = null):Call<ResponseTotalObat>

    @FormUrlEncoded
    @POST("dataobat")
    fun createData(
        @Field("kodeobat") kodeobat:String?,
        @Field("namaobat") namaobat:String?,
        @Field("satuan") merkobat:String?,
        @Field("stokawal") stokawal:String?,
        @Field("obatmasuk") obatmasuk:String?,
        @Field("obatkeluar") obatkeluar:String?,
        @Field("stokakhir") stokakhir:String?,
    ):Call<ResponCreateTotalObat>
//
    @DELETE("dataobat/{kodeobat}")
    fun deleteData(@Path("kodeobat") kodeobat: String?):Call<ResponCreateTotalObat>

    @FormUrlEncoded
    @PUT("dataobat/{kodeobat}") fun updateData(
        @Path("kodeobat") kodeobat:String?,
        @Field("namaobat") namaobat:String?,
        @Field("satuan") merkobat:String?,
        @Field("stokawal") stokawal:String?,
        @Field("obatmasuk") obatmasuk:String?,
        @Field("obatkeluar") obatkeluar:String?,
        @Field("stokakhir") stokakhir:String?,
    ):Call<ResponCreateTotalObat>
}