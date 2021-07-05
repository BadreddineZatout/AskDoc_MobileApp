package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Patient (
    @PrimaryKey
    val patientId:Int,
    val name:String,
    val tel:String,
    val pwd:String
): Serializable {
}