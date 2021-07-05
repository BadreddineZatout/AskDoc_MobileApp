package com.example.askdoc.daos

import androidx.room.*
import com.example.askdoc.models.Traitement

@Dao
interface TraitementDao {

    @Query("select * from Traitement where patientId=:id")
    fun getTreatments(id:Int):List<Traitement>

    @Insert
    fun addTreatments(treatments:List<Traitement>)
}