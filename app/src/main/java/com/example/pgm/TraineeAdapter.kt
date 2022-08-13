package com.example.pgm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class TraineeAdapter(private val context: Context, private val coaches: ArrayList<TraineeData>) :
    RecyclerView.Adapter<TraineeAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var my_name = itemView.findViewById<TextView>(R.id.tx_name) as TextView
        var my_type = itemView.findViewById<TextView>(R.id.tx_type) as TextView
        var my_image = itemView.findViewById<ImageView>(R.id.tx_image) as ImageView
        var card_View = itemView.findViewById<CardView>(R.id.cardView) as CardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.design, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = coaches[position]
        holder.my_name.text = data.name
        holder.my_type.text = data.type
        holder.my_image.setImageResource(R.drawable.logo)

        if (data.image != "null") {
            val imgurll = data.image.substringAfter("images")
            Glide.with(context).load("http://${Data.url}:8000/images${imgurll}")
                .into(holder.my_image)
        }

        holder.card_View.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.main_anim
            )
        )
        holder.itemView.setOnClickListener {

            val i = Intent(context, ShowTraineeInformation::class.java)
            i.putExtra("name", coaches[position].name)
            i.putExtra("type", coaches[position].type)
            i.putExtra("image", coaches[position].image)
            i.putExtra("phone", coaches[position].phone)
            i.putExtra("age", coaches[position].age)
            i.putExtra("height", coaches[position].height)
            i.putExtra("weight", coaches[position].weight)
            i.putExtra("id", coaches[position].id)

            context.startActivity(i)

        }
    }

    override fun getItemCount(): Int {
        return coaches.size
    }
}