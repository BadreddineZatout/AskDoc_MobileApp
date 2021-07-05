package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = arrayOf("doctorId", "patientId"))
data class Conseil (
    val doctorId:Int, val patientId:Int, val text:String, var isSyncronized:Int=0
)