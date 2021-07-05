package com.example.askdoc.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.askdoc.R
import com.example.askdoc.baseUrl
import com.example.askdoc.models.DoctorVm
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*

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
        nom.text="Docteur: ${vm.doctor.name}"
        phone.text="Numéro de téléphone: ${vm.doctor.tel}"
        spec.text="Spécialité: ${vm.doctor.spec}"
        exp.text="${vm.doctor.exp.toString()} ans d'expérience"
        Glide.with(this).load("https://askdoc-restapi.herokuapp.com/public/"+vm.doctor.image).into(photoM)

        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var date = ""

        btnConseil.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_detailFragment_to_conseilFragment2)
        }
        btnrdv.setOnClickListener{
            val dpd = DatePickerDialog(requireActivity(),
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    date = ""+mDay+"-"+ (mMonth.toInt()+1)+"-"+mYear
                    var bundle = bundleOf("date" to date)
                    requireActivity().findNavController(R.id.nav_host).navigate(R.id.action_detailFragment_to_chooseHourFragment2,bundle)
                },year,month,day)
            dpd.show()
        }
    }
}