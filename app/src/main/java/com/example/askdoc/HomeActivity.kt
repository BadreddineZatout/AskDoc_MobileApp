package com.example.askdoc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        getSupportActionBar()?.setHomeAsUpIndicator(R.drawable.ic_baseline_login_24);// set drawable icon
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                val prefs = getSharedPreferences("Auth", Context.MODE_PRIVATE)
                val connected = prefs.getBoolean("connected", false)
                prefs.edit {
                    putBoolean("connected", false)
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}