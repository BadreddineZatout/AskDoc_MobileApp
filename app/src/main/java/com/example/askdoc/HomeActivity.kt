package com.example.askdoc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.askdoc.fragments.ChooseDateFragment

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

     /*   if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.homeActivity,ChooseDateFragment.newInstance())
                .commitNow()
        }*/
    }

}