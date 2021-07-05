package com.example.askdoc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.askdoc.R
import com.example.askdoc.models.Doctor
import com.example.askdoc.models.DoctorVm
import com.example.askdoc.services.RetrofitService
import com.example.movieexample.MyAdapter
import com.synnapps.carouselview.ImageListener
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val vm= ViewModelProvider(requireActivity()).get(DoctorVm::class.java)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        getMedecins(vm)
        showTreatmentsBtn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_listFragment_to_treatmentsFragment)
        }
        showBookingsBtn.setOnClickListener { view ->
            view.findNavController().navigate(R.id.action_listFragment_to_bookingsFragment)
        }
    }
    private fun getMedecins(vm:DoctorVm){
        val call= RetrofitService.endpoint.getMedecins()
        call.enqueue(object : Callback<List<Doctor>> {
            override fun onFailure(call: Call<List<Doctor>>, t: Throwable) {
                Toast.makeText(requireActivity(),"une erreur1 s'est produite", Toast.LENGTH_SHORT ).show()
            }

            override fun onResponse(call: Call<List<Doctor>>, response: Response<List<Doctor>>) {
                if(response.isSuccessful){
                    val data=response.body()
                    print(response.body())
                    if (data!=null){
                        recyclerView.adapter = MyAdapter(requireActivity(),data,vm)
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur2 s'est produite", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}