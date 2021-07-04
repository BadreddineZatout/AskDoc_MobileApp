package com.example.askdoc.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.Toast
import com.example.askdoc.R
import com.example.askdoc.models.Booking
import com.example.askdoc.services.RetrofitService
import kotlinx.android.synthetic.main.fragment_choose_hour.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "bookingDate"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChooseHourFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChooseHourFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var bookingDate: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            bookingDate = it.getString(ARG_PARAM1)
            // param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_hour, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param bookingDate Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChooseHourFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(bookingDate: String) =
            ChooseHourFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, bookingDate)
                    // putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // getDoctorHours(1,this.bookingDate.toString())
        val booking = Booking(4,"15-12-2012",8,1,1,"")
        getDoctorHours(1,this.bookingDate.toString())
        /*val call = RetrofitService.endpoint.addBooking(booking)
        call.enqueue(object :Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                Toast.makeText(chooseHourFragment.context,"une erreur1 s'est produite",Toast.LENGTH_SHORT ).show()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                if(response.isSuccessful){
                    print(response.body())
                }
                else {
                    Toast.makeText(chooseHourFragment.context,"une erreur2 s'est produite",Toast.LENGTH_SHORT).show()
                }
            }

        })*/
    }

    private class hoursAdapter(val context : Context,var data:List<Int>) : BaseAdapter() {

        private val mContext:Context

        init {
            this.mContext = context
        }

        override fun getCount(): Int {
            return data.size
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            TODO("Not yet implemented")
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater : LayoutInflater = LayoutInflater.from(mContext)
            val view  : View = inflater.inflate(R.layout.choose_hour_list_item,null)

            val hourBtn : Button = view.findViewById(R.id.hourItemBtn)
            hourBtn.setText(data[position].toString())
            hourBtn.setOnClickListener {
                //
            }

            return view
        }
    }

    private fun getDoctorHours(doctorId:Int,date:String){
        val call= RetrofitService.endpoint.getBookingByDoctor(doctorId,date)
        call.enqueue(object :Callback<List<Int>>{
            override fun onFailure(call: Call<List<Int>>, t: Throwable) {
                Toast.makeText(chooseHourFragment.context,"une erreur1 s'est produite",Toast.LENGTH_SHORT ).show()
            }

            override fun onResponse(call: Call<List<Int>>, response: Response<List<Int>>) {
                if(response.isSuccessful){
                    val data=response.body()
                    if (data!=null){
                        try {
                            chooseHourList.adapter = hoursAdapter(chooseHourFragment.context,data)
                            // Toast.makeText(chooseHourFragment.context,data[0].bookingHour.toString(),Toast.LENGTH_SHORT).show()
                        }
                        catch (e:Exception){
                            //
                        }
                        // recyclerView.adapter = MyAdapter(this@MainActivity,data)
                    }
                }
                else {
                    Toast.makeText(chooseHourFragment.context,"une erreur2 s'est produite",Toast.LENGTH_SHORT).show()
                }
            }

        })
    }
}