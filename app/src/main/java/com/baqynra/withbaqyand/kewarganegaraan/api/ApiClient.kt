package com.baqynra.withbaqyand.kewarganegaraan.api

import android.content.Context
import retrofit2.create

class ApiClient {
    val BASE_API =  "https://api.simkug.com/api/warga/"

    fun getApiService(context: Context): Api?{
        return RetrofitClient.getClient(BASE_API,context)?.create(Api::class.java)
    }
}