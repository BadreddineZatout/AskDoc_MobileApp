package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*
@Entity
data class Booking (
    @PrimaryKey
    val bookingId:Long,
    val bookingDate: Date?,
    val bookingTime: String?,
    val patientId: Int?,
    val patientName:String?,
    val doctorId:Long?
)
{
}