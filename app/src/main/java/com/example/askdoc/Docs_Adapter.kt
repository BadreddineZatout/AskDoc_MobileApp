package com.example.askdoc

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Docs_Adapter(val context: Context,var data:List<Doctor>):RecyclerView.Adapter<MyViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.docitem, parent, false))

    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load("http://59a9b18359fb.ngrok.io/" + data[position].image).into(holder.img)
        holder.name.text = data[position].name
        holder.tel.text = data[position].tel.toString()
        holder.spec.text = data[position].spec.toString()
        holder.tel.setOnClickListener { view->
            val uri = Uri.parse("tel:"+holder.tel.text)
            val intent = Intent(Intent.ACTION_DIAL, uri)
            context.startActivity(intent)
        }
        holder.map.setOnClickListener{view ->
            val lat = data[position].lat
            val lng = data[position].lng
            val geolocation = Uri.parse("geo:$lat,$lng")
            val intent = Intent(Intent.ACTION_VIEW, geolocation)
            context.startActivity(intent)
        }
       /* holder.el.setOnClickListener{view->
            if(isTwoPane()) {
                val activity = context as Activity
                Glide.with(context).load("http://59a9b18359fb.ngrok.io/" + data[position].image).into(holder.img)
                val name = activity.findViewById(R.id.nomprenom) as TextView
                name.text = data[position].name
                val spec = activity.findViewById(R.id.specialite) as TextView
                spec.text = data[position].spec
                val tel = activity.findViewById(R.id.phone) as TextView
                tel.text = data[position].tel
                val expr = activity.findViewById(R.id.exp) as TextView
                expr.text = data[position].exp.toString()
                val fb = activity.findViewById(R.id.fb) as TextView
                fb.text = data[position].fb

            }else{
                val intent = Intent(context, profil::class.java)
                intent.putExtra("doc", data[position])
                context.startActivity(intent)
            }
        }*/
    }
}

class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val name = view.findViewById<TextView>(R.id.name) as TextView
    val tel = view.findViewById<TextView>(R.id.tel) as TextView
    val spec = view.findViewById<TextView>(R.id.specialite1) as TextView
    val img = view.findViewById(R.id.image) as ImageView
    val map = view.findViewById(R.id.map) as ImageView
    val el = view.findViewById(R.id.element) as ConstraintLayout
}