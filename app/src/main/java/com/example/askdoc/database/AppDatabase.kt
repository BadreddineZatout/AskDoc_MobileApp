package com.example.askdoc.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.askdoc.daos.BookingDao
import com.example.askdoc.daos.ConseilDao
import com.example.askdoc.daos.TraitementDao
import com.example.askdoc.models.*

@Database(entities = arrayOf(Doctor::class, Patient::class, Booking::class, Traitement::class, Conseil::class), version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun getBookingDao(): BookingDao
    abstract fun getTraitementDao(): TraitementDao
    abstract fun getConseilDao(): ConseilDao
}