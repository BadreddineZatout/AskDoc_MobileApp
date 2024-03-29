package com.example.askdoc.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.askdoc.R
import com.example.askdoc.models.PatientVM
import com.example.askdoc.models.Traitement
import com.example.askdoc.models.TreatmentVM
import com.example.askdoc.services.RoomService
import kotlinx.android.synthetic.main.fragment_choose_hour.*
import kotlinx.android.synthetic.main.fragment_treatments.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TreatmentsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TreatmentsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_treatments, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TreatmentsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TreatmentsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Treatments ViewModel
        val vm= ViewModelProvider(requireActivity()).get(TreatmentVM::class.java)
        treatmentsRecView.layoutManager = LinearLayoutManager(requireActivity())

        // Patient ViewModel to get id
        val vm_patient= ViewModelProvider(requireActivity()).get(PatientVM::class.java)
        val patientId = vm_patient.patient.id

        // Get Treatments
        val data = RoomService.appDatabase.getTraitementDao().getTreatments(patientId)

        // Set treatments RecyclerView adapter
        treatmentsRecView.adapter=TreatmentsAdapter(requireActivity(),data,vm)
    }

    class TreatmentsAdapter(val context: Context, var data: List<Traitement>, val vm: TreatmentVM): RecyclerView.Adapter<TreatmentViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TreatmentViewHolder {
            return TreatmentViewHolder(LayoutInflater.from(context).inflate(R.layout.treatment_item, parent, false))

        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: TreatmentViewHolder, position: Int) {
            holder.disease.text = data[position].disease
            holder.description.text = data[position].treatmentDescription
            holder.beginDate.text = data[position].treatmentBeginDate.toString()
            holder.endDate.text = data[position].treatmentEndDate.toString()
        }

    }
    class TreatmentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val disease = view.findViewById<TextView>(R.id.bookingItemDoctor) as TextView
        val description = view.findViewById<TextView>(R.id.treatmentDescription) as TextView
        val beginDate = view.findViewById<TextView>(R.id.treatmentBeginDate) as TextView
        val endDate = view.findViewById<TextView>(R.id.treatmentEndDate) as TextView
    }

}