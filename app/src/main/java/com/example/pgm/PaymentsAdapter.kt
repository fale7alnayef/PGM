package com.example.pgm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class PaymentsAdapter (val context: Context, private var exercise: ArrayList<PaymentData>) :
    RecyclerView.Adapter<PaymentsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var my_name = itemView.findViewById(R.id.tx_name) as TextView
        var my_desc = itemView.findViewById(R.id.tx_value) as TextView
        var card_View = itemView.findViewById(R.id.cardViewooo) as CardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.design2, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = exercise[position]
        holder.my_name.text = data.pay
        holder.my_desc.text = data.date
        holder.card_View.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.main_anim
            )
        )

    }






    override fun getItemCount(): Int {
        return exercise.size
    }


}