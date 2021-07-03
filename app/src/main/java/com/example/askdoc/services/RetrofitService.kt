package com.example.askdoc.services

import com.example.askdoc.endpoints.Endpoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val endpoint :Endpoint by lazy {
        Retrofit.Builder().baseUrl("https://medico-rest.herokuapp.com/api/").
        addConverterFactory(GsonConverterFactory.create()).
        build().create(Endpoint::class.java)
    }
}