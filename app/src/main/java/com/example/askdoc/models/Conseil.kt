package com.example.askdoc.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = arrayOf("iddoc", "idpat"))
data class Conseil (
    val iddoc:Int, val idpat:Int, val text:String, var isSyncronized:Int=0
)