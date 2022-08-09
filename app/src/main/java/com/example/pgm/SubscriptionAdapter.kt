
package com.example.pgm

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView


class SubscriptionAdapter (private val context: Context, private val subscriptions : List<SCData>):
    RecyclerView.Adapter<SubscriptionAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var my_name = itemView.findViewById<TextView>(R.id.tx_name) as TextView
        var my_value = itemView.findViewById<TextView>(R.id.tx_value) as TextView
        var card_View = itemView.findViewById<CardView>(R.id.cardViewooo) as CardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.design2,parent, false)
        return ViewHolder(v)
    }



    override fun getItemCount(): Int {
        return subscriptions.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = subscriptions[position]
        holder.my_name.text = data.name
        holder.my_value.text = data.value
        holder.card_View.startAnimation(AnimationUtils.loadAnimation(holder.itemView.context,R.anim.main_anim))
        holder.itemView.setOnClickListener {
            val i = Intent(context,Subscription::class.java)
            i.putExtra("name",subscriptions[position].name)
            i.putExtra("value",subscriptions[position].value)
            i.putExtra("startDate",subscriptions[position].startDate)
            i.putExtra("endDate",subscriptions[position].endDate)
            context.startActivity(i)

        }    }
}