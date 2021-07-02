package com.example.askdoc.endpoints

import com.example.askdoc.models.Auth
import com.example.askdoc.models.Patient
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @POST("patient/Auth")
    fun AuthPatient(@Body auth:Auth):Call<Patient>
}