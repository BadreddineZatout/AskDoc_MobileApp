package com.example.askdoc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.askdoc.R
import com.example.askdoc.models.DoctorVm
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(DoctorVm::class.java)
        nom.text="Nom: ${vm.doctor.name}"
        phone.text="Numéro de téléphone: ${vm.doctor.tel}"
        spec.text="Spécialité: ${vm.doctor.spec}"
        exp.text="Années d'éxpérience: ${vm.doctor.exp.toString()} ans"
        Glide.with(this).load("https://abae3311af59.eu.ngrok.io/"+vm.doctor.image).into(photoM)
    }
}