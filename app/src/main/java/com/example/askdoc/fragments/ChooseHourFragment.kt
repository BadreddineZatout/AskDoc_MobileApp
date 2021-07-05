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
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.findNavController
import com.example.askdoc.R
import com.example.askdoc.models.Booking
import com.example.askdoc.services.RetrofitService
import kotlinx.android.synthetic.main.choose_hour_list_item.*
import kotlinx.android.synthetic.main.choose_hour_list_item.view.*
import kotlinx.android.synthetic.main.fragment_choose_date.*
import kotlinx.android.synthetic.main.fragment_choose_hour.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChooseHourFragment : Fragment() {
    private var date = ""
    private var doctorId = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_hour, container, false)
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        this.date = arguments?.getString("date").toString()
        getDoctorHours(this.doctorId,this.date)
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

    private class hoursAdapter(val context : Context,var data:List<Int>,date:String ,doctorId: Int) : BaseAdapter() {

        private val mContext:Context
        private val mDate:String
        private val mDoctorId:Int

        init {
            this.mContext = context
            mDate = date
            mDoctorId =doctorId
        }

        fun getAdapterContext():Context{
            return this.mContext
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
            hourBtn.setText(data[position].toString()+" H")
            hourBtn.setOnClickListener {view->
                var hour = data[position].toString()
                var bundle = bundleOf("date" to mDate,"hour" to hour,"doctorId" to mDoctorId.toString())
                view.findNavController().navigate(R.id.action_chooseHourFragment2_to_bookinCreatedFragment,bundle)

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
                            chooseHourList.adapter = hoursAdapter(chooseHourFragment.context,data,date,doctorId)
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