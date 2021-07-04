package com.example.askdoc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.askdoc.models.Auth
import com.example.askdoc.models.Patient
import com.example.askdoc.services.RetrofitService
import kotlinx.android.synthetic.main.fragment_auth.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide();
        super.onCreate(savedInstanceState)
        val pref = getSharedPreferences("Auth", Context.MODE_PRIVATE)
        val connected = pref.getBoolean("connected", false)
        if(connected){
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            setContentView(R.layout.activity_main)
            login.setOnClickListener {
                val credentials = Auth(telephone.text.toString(), pdw.text.toString())
                val call = RetrofitService.endpoint.AuthPatient(credentials)
                call.enqueue(object : Callback<Patient> {
                    @SuppressLint("RestrictedApi")
                    override fun onResponse(call: Call<Patient>, response: Response<Patient>) {
                        if (response?.isSuccessful) {
                            val data = response.body()!!
                            pref.edit().putBoolean("connected", true).apply()
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            intent.putExtra("patient", data)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Wrong credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    @SuppressLint("RestrictedApi")
                    override fun onFailure(call: Call<Patient>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }
    }
}