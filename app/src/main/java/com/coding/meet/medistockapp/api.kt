package com.coding.meet.medistockapp

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import java.net.ResponseCache

interface api {
    @GET("obatmasuk/{cari}")
    fun getData(@Path("cari") cari:String? = null):Call<ResponseDataMasuk>

    @FormUrlEncoded
    @POST("obatmasuk")
    fun createData(
        @Field("kodeobat") kodeobat:String?,
        @Field("namaobat") namaobat:String?,
        @Field("merkobat") merkobat:String?,
        @Field("jmlmasuk") jmlmasuk:String?,
        @Field("satuan") satuan:String?,
        @Field("tglmasuk") tglmasuk:String?,
        @Field("tglexp") tglexp:String?,
        @Field("deskripsi") deskripsi:String?,
    ):Call<ResponCreateMasuk>

    @DELETE("obatmasuk/{kodeobat}")
    fun deleteData(@Path("kodeobat") kodeobat: String?):Call<ResponCreateMasuk>

    @FormUrlEncoded
    @PUT("obatmasuk/{kodeobat}") fun updateData(
        @Path("kodeobat") kodeobat:String?,
        @Field("namaobat") namaobat:String?,
        @Field("merkobat") merkobat:String?,
        @Field("jmlmasuk") jmlmasuk:String?,
        @Field("satuan") satuan:String?,
        @Field("tglmasuk") tglmasuk:String?,
        @Field("tglexp") tglexp:String?,
        @Field("deskripsi") deskripsi:String?,
    ):Call<ResponCreateMasuk>
}