package com.example.askdoc.services

import com.example.askdoc.baseLocalUrl
import com.example.askdoc.baseUrl
import com.example.askdoc.endpoints.Endpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoint :Endpoint by lazy {
        Retrofit.Builder().baseUrl(baseLocalUrl).
        addConverterFactory(GsonConverterFactory.create()).
        build().create(Endpoint::class.java)
    }
}