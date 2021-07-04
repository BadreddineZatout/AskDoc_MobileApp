package com.example.askdoc.endpoints

import com.example.askdoc.models.Auth
import com.example.askdoc.models.Booking
import com.example.askdoc.models.Patient
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @POST("patient/auth")
    fun AuthPatient(@Body auth:Auth):Call<Patient>

    @PUT("booking/")
    fun addBooking(@Body booking:Booking):Call<String>

    @GET("booking/findByDoctor/{id}/{date}")
    fun getBookingByDoctor(@Path("id") id:Int,@Path("date") date:String):Call<List<Int>>
}