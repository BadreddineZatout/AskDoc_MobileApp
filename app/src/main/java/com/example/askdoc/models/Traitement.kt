package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.askdoc.models.Booking
import java.util.*

@Entity(foreignKeys = arrayOf(
    ForeignKey(entity = Booking::class, parentColumns = arrayOf("bookingId"),
        childColumns = arrayOf("bookingId"), onDelete = ForeignKey.CASCADE)
))
data class Traitement (
    @PrimaryKey
    val treatmentId:Long,
    val disease:String,
    val treatmentDescription:String,
    val treatmentBeginDate: Date?,
    val treatmentEndDate: Date?,
    val bookingId:Long)
{

}