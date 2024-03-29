package com.example.askdoc.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.example.askdoc.R
import com.example.askdoc.models.Conseil
import com.example.askdoc.models.DoctorVm
import com.example.askdoc.models.PatientVM
import com.example.askdoc.services.RetrofitService
import com.example.askdoc.services.RoomService
import com.example.askdoc.workers.ConseilWorker
import kotlinx.android.synthetic.main.fragment_conseil.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ConseilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_conseil, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        EnvoyerConseil.setOnClickListener {
            Toast.makeText(requireActivity(), "sending...", Toast.LENGTH_SHORT).show()
            val vm= ViewModelProvider(requireActivity()).get(DoctorVm::class.java)
            val iddoc = vm.doctor.doctorId
            val vmpat= ViewModelProvider(requireActivity()).get(PatientVM::class.java)
            val c = Conseil(iddoc, vmpat.patient.id, conseilText.text.toString())
            RoomService.context = requireActivity()
            RoomService.appDatabase.getConseilDao().addConseil(c)
            scheduleSync()
        }
    }
    private fun scheduleSync(){
        val constraints = Constraints.Builder().
        setRequiredNetworkType(NetworkType.CONNECTED).build()
        val req = OneTimeWorkRequest.Builder(ConseilWorker::class.java).
        setConstraints(constraints).addTag("conseil").build()
        val workManager = WorkManager.getInstance(requireActivity())
        workManager.enqueueUniqueWork("workConseil", ExistingWorkPolicy.REPLACE, req)
    }
}