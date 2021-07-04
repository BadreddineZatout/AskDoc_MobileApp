package com.example.askdoc.fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.askdoc.R
import kotlinx.android.synthetic.main.fragment_choose_date.*
import java.util.*


class ChooseDateFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_date, container, false)
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // Calendar
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        var date = ""
        bookingButton.setOnClickListener{
            val dpd = DatePickerDialog(chooseDateFragment.context,
                DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                    date = ""+mDay+"-"+ (mMonth.toInt()+1)+"-"+mYear
                    var bundle = bundleOf("date" to date)
                    requireActivity().findNavController(R.id.main_graph).navigate(R.id.action_chooseDateFragment3_to_chooseHourFragment2,bundle)
                },year,month,day)
            dpd.show()
        }
    }
}