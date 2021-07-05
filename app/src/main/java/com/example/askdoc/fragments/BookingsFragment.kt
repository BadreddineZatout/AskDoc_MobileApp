package com.example.askdoc.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.askdoc.R
import com.example.askdoc.models.*
import com.example.askdoc.services.RetrofitService
import kotlinx.android.synthetic.main.fragment_bookin_created.*
import kotlinx.android.synthetic.main.fragment_bookings.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookingsFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_bookings, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BookingsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // Patient ViewModel to get id
        val vm_patient= ViewModelProvider(requireActivity()).get(PatientVM::class.java)
        val patientId = vm_patient.patient.patientId

        // Get Treatments
        getBookings(patientId)

    }

    class BookingsAdapter(val context: Context, var data: List<BookingWithDoctor>, val vm: BookingVM): RecyclerView.Adapter<BookingViewHolder>()
    {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
            return BookingViewHolder(LayoutInflater.from(context).inflate(R.layout.booking_item, parent, false))

        }

        override fun getItemCount() = data.size

        override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
            holder.bookingDate.text = data[position].bookingDate.toString()
            holder.bookingHour.text = data[position].bookingHour.toString()
            holder.doctorName.text = data[position].name
            holder.card.setOnClickListener { view ->
            // showQRCodeFragment
                var CodeQR = data[position].CodeQR
                var bundle = bundleOf("CodeQR" to CodeQR)
                view.findNavController().navigate(R.id.showQRCodeFragment,bundle)
            }
        }

    }
    class BookingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookingDate = view.findViewById<TextView>(R.id.bookingItemDate) as TextView
        val bookingHour = view.findViewById<TextView>(R.id.bookingItemHour) as TextView
        val doctorName = view.findViewById<TextView>(R.id.bookingItemDoctor) as TextView
        val card = view.findViewById<CardView>(R.id.bookingItemCard) as CardView
    }

    fun getBookings(patientId:Int){
        val call = RetrofitService.endpoint.getBookings(patientId)
        call.enqueue(object : Callback<List<BookingWithDoctor>> {
            override fun onFailure(call: Call<List<BookingWithDoctor>>, t: Throwable) {
                Toast.makeText(bookingCreatedFragment.context,"une erreur1 s'est produite", Toast.LENGTH_SHORT ).show()
            }

            override fun onResponse(call: Call<List<BookingWithDoctor>>, response: Response<List<BookingWithDoctor>>) {
                if(response.isSuccessful){
                    val data=response.body()!!
                    if (data!=null){
                        // Booking ViewModel
                        val vm= ViewModelProvider(requireActivity()).get(BookingVM::class.java)
                        bookingsRecView.layoutManager = LinearLayoutManager(requireActivity())
                        bookingsRecView.adapter= BookingsFragment.BookingsAdapter(requireActivity(), data, vm)
                    }
                }
                else {
                    Toast.makeText(requireActivity(),"une erreur2 s'est produite",
                        Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}