package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
@Entity
data class Doctor(
    @PrimaryKey
    val doctorId:Int,
    val name:String,
    val tel:String,
    val spec:String,
    val startAt:Int,
    val finishAt:Int,
    val bookingsByHour:Int,
    val ltd:Double,
    val lng:Double,
    val experience:Int,
    val image:String
) : Serializable {

}