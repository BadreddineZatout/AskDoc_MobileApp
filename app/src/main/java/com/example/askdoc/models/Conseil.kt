package com.example.askdoc.models

import androidx.room.Entity

@Entity
data class Conseil (
    val iddoc:Int, val idpat:Int, val text:String, var isSyncronized:Int=0
){
}