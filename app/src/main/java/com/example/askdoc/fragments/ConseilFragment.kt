package com.example.askdoc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.bumptech.glide.Glide
import com.example.askdoc.R
import com.example.askdoc.models.Conseil
import com.example.askdoc.models.DoctorVm
import com.example.askdoc.services.RoomService
import com.example.askdoc.workers.ConseilWorker
import kotlinx.android.synthetic.main.fragment_conseil.*
import kotlinx.android.synthetic.main.fragment_detail.*

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
        btnEnvoyer.setOnClickListener {
            val vm= ViewModelProvider(requireActivity()).get(DoctorVm::class.java)
            val iddoc = vm.doctor.doctorId
            val c = Conseil(iddoc, 1, conseilText.text.toString())
            RoomService.context = requireActivity()
            RoomService.appDatabase.getConseilDao().addConseil(c)
            scheduleSync()
        }
    }
    private fun scheduleSync(){
        val constraints = Constraints.Builder().
        setRequiredNetworkType(NetworkType.CONNECTED).build()
        val req = OneTimeWorkRequest.Builder(ConseilWorker::class.java).
        setConstraints(constraints).addTag("id").build()
        val workManager = WorkManager.getInstance(requireActivity())
        workManager.enqueueUniqueWork("work", ExistingWorkPolicy.REPLACE, req)
    }
}