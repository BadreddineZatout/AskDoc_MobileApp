package com.example.movieexample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.askdoc.R
import com.example.askdoc.models.Doctor
import com.example.askdoc.models.DoctorVm

class MyAdapter(val context: Context, var data: List<Doctor>, val vm: DoctorVm):RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.docitem, parent, false))

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.nom.text = data[position].name
        holder.phone.text = data[position].tel
        holder.specialite.text = data[position].spec
        Glide.with(context).load("https://askdoc-restapi.herokuapp.com/public/"+data[position].image).into(holder.photo)
        holder.phone.setOnClickListener { view ->
            val uri = Uri.parse("tel:${holder.phone.text}")
            val intent = Intent(Intent.ACTION_CALL, uri)
            context.startActivity(intent)
        }
        holder.localisation.setOnClickListener { view ->
            val latitude = data[position].lat
            val longitude = data[position].lng
            val gmmIntentUri = Uri.parse("google.navigation:q=$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            context.startActivity(intent)

        }
        holder.itemView.setOnClickListener { view->
            vm.doctor=data[position]
            view?.findNavController()?.navigate(R.id.action_listFragment_to_detailFragment)
        }
    }

}
class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val nom = view.findViewById<TextView>(R.id.textNom) as TextView
    val phone = view.findViewById<TextView>(R.id.textPhone) as TextView
    val specialite = view.findViewById<TextView>(R.id.textSpecialite) as TextView
    val photo = view.findViewById<ImageView>(R.id.image) as ImageView
    val localisation= view.findViewById<ImageView>(R.id.direction) as ImageView
}

