package com.example.askdoc.models

import java.io.Serializable

data class BookingsByDoctorVM(
    val bookingHour:Int,
    val countBookings:String
)  : Serializable {

}
