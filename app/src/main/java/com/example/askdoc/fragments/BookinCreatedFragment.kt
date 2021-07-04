package com.example.askdoc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askdoc.R
import kotlinx.android.synthetic.main.fragment_bookin_created.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "hour"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookinCreatedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookinCreatedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var hour: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hour = it.getString(ARG_PARAM1)
            /*param2 = it.getString(ARG_PARAM2)*/
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookin_created, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param hour Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookinCreatedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(hour: String/*, param2: String*/) =
            BookinCreatedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, hour)
                    /*putString(ARG_PARAM2, param2)*/
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookingCreated.setText(hour.toString())
        // getDoctorHours(1,this.bookingDate.toString())

    }
}