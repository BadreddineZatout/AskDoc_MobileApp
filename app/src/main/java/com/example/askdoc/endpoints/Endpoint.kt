package com.example.askdoc.endpoints

import com.example.askdoc.models.Auth
import com.example.askdoc.models.Conseil
import com.example.askdoc.models.Doctor
import com.example.askdoc.models.Patient
import com.example.askdoc.models.Traitement
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @POST("patient/auth")
    fun AuthPatient(@Body auth:Auth):Call<Patient>

    @GET("doctor")
    fun getMedecins():Call<List<Doctor>>
  
    @POST("conseil")
    fun addConseil(@Body conseil: Conseil):Call<String>

    @GET("traitement/offline")
    fun getAllTreaitementOffline():Call<List<Traitement>>

    @PUT("traitement/offline")
    fun updateOffline():Call<String>

}