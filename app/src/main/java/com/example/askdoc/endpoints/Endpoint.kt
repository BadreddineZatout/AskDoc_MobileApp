package com.example.askdoc.endpoints

import com.example.askdoc.models.*
import retrofit2.Call
import retrofit2.http.*

interface Endpoint {
    @POST("patient/auth")
    fun AuthPatient(@Body auth:Auth):Call<Patient>

    @POST("booking/")
    fun addBooking(@Body booking:Booking):Call<Any>

    @GET("booking/findByDoctor/{id}/{date}")
    fun getBookingByDoctor(@Path("id") id:Int,@Path("date") date:String):Call<List<Int>>

    // Get bookings for patient with patientId : id
    @GET("booking/patient/{id}")
    fun getBookings(@Path("id") id:Int):Call<List<BookingWithDoctor>>

    @GET("doctor")
    fun getMedecins():Call<List<Doctor>>

    @POST("counsel")
    fun addConseil(@Body conseil: Conseil):Call<Any>

    @POST("counsel/createMany")
    fun addConseils(@Body conseils: List<Conseil>):Call<Any>

    @GET("treatment/offline/{id}")
    fun getAllTreaitementOffline(@Path ("id") id:Int):Call<List<Traitement>>

    @PUT("treatment/offline/{id}")
    fun updateOffline(@Path ("id") id:Int ):Call<String>
}