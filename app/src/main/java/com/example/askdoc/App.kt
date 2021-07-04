package com.example.askdoc

import android.app.Application
import com.example.askdoc.services.RoomService

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}