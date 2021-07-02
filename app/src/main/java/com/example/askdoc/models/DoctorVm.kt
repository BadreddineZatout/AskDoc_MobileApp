package com.example.askdoc.models
import androidx.lifecycle.ViewModel

class DoctorVm:ViewModel() {
    var doctor=Doctor(
        0, "", "", "", 0.0000, 0.0000,-1, ""
    )
}