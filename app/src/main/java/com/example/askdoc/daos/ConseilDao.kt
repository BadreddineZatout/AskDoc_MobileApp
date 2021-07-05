package com.example.askdoc.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.askdoc.models.Conseil

@Dao
interface ConseilDao {
    @Query("select * from conseil where isSyncronized=0")
    fun getConsilsToSynchronize():List<Conseil>

    @Insert
    fun addConseil(conseil: Conseil)

    @Update
    fun updateConseil(conseils: Conseil)

}