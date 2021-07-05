package com.example.askdoc

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.askdoc.fragments.ChooseDateFragment
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.work.*
import com.example.askdoc.models.DoctorVm
import com.example.askdoc.models.Patient
import com.example.askdoc.models.PatientVM
import com.example.askdoc.workers.TreatmentWorker

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val patient = intent.getSerializableExtra("patient") as Patient
        val vm= ViewModelProvider(this).get(PatientVM::class.java)
        vm.patient = patient
        setContentView(R.layout.activity_home)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_login_24);// set drawable icon
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        scheduleSync()

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
    private fun scheduleSync(){
        val constraints = Constraints.Builder().
        setRequiredNetworkType(NetworkType.CONNECTED).build()
        val req = OneTimeWorkRequest.Builder(TreatmentWorker::class.java).
        setConstraints(constraints).addTag("id").build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE, req)
    }

}