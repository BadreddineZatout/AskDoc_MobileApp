package com.example.askdoc.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.askdoc.R
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_bookin_created.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "date"
private const val ARG_PARAM2 = "hour"
private const val ARG_PARAM3 = "doctorId"

/**
 * A simple [Fragment] subclass.
 * Use the [BookinCreatedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookinCreatedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var date: String? = null
    private var hour: String? = null
    private var doctorId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            date = it.getString(ARG_PARAM1)
            hour = it.getString(ARG_PARAM2)
            doctorId = it.getString(ARG_PARAM3)
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
        fun newInstance(date: String, hour: String,doctorId: String) =
            BookinCreatedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, date)
                    putString(ARG_PARAM2, hour)
                    putString(ARG_PARAM3, doctorId)
                    /*putString(ARG_PARAM2, param2)*/
                }
            }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookingCreated.setText(" Date : "+date+" Hour : "+hour.toString()+" Doctor ID :"+doctorId)
        generateQRCode(date+hour.toString()+doctorId.toString()+'1')
        confirmCreateBooking.setOnClickListener { view ->

        }
        // getDoctorHours(1,this.bookingDate.toString())

    }

    private fun generateQRCode(text:String){
        val multiFormatWriter = MultiFormatWriter()
        try{
            val bitMatrix = multiFormatWriter.encode(text.toString(),BarcodeFormat.QR_CODE,500,500)
            val barcodeEncoder = BarcodeEncoder()
            val bitMap = barcodeEncoder.createBitmap(bitMatrix)
            QRImage.setImageBitmap(bitMap)
        }catch (e: WriterException){
            e.printStackTrace()
        }
    }
}