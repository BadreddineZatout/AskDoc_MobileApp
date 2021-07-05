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
import com.google.zxing.qrcode.encoder.QRCode
import com.journeyapps.barcodescanner.BarcodeEncoder
import kotlinx.android.synthetic.main.fragment_bookin_created.*
import kotlinx.android.synthetic.main.fragment_show_q_r_code.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "CodeQR"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowQRCodeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowQRCodeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var CodeQR: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            CodeQR = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_q_r_code, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param CodeQR Parameter 1.
         * @return A new instance of fragment ShowQRCodeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(CodeQR: String) =
            ShowQRCodeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, CodeQR)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        generateQRCode(this.CodeQR.toString())
    }

    private fun generateQRCode(text:String){
        val multiFormatWriter = MultiFormatWriter()
        try{
            val bitMatrix = multiFormatWriter.encode(text.toString(), BarcodeFormat.QR_CODE,500,500)
            val barcodeEncoder = BarcodeEncoder()
            val bitMap = barcodeEncoder.createBitmap(bitMatrix)
            QRImageShow.setImageBitmap(bitMap)
        }catch (e: WriterException){
            e.printStackTrace()
        }
    }
}