package com.example.askdoc.models

data class BookingWithDoctor(
    val bookingDate: String?,
    val bookingHour:Int,
    val CodeQR:String,
    val name:String
)
