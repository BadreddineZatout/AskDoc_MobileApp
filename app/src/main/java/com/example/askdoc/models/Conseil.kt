package com.example.askdoc.models

import androidx.room.Entity

@Entity(primaryKeys = arrayOf("iddoc", "idpat"))
data class Conseil (
    val iddoc:Int, val idpat:Int, val text:String, var isSyncronized:Int=0
){
}