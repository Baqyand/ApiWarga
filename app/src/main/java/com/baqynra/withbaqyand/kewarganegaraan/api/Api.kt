package com.baqynra.withbaqyand.kewarganegaraan.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("no_hp") no_hp: String?,
        @Field("password") password: String?,
        @Field("id_device") id_device: String?
//        @Field("password") password: String?
    ):Call<ResponseBody>

    @GET("profile")
    fun profile_warga(
    ): Call<ResponseBody>

}