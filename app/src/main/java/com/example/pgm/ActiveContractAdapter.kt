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

class ActiveContractAdapter(private val context: Context, private val contracts: List<SCData>) :
    RecyclerView.Adapter<ActiveContractAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var my_name = itemView.findViewById<TextView>(R.id.tx_name) as TextView
        var my_value = itemView.findViewById<TextView>(R.id.tx_value) as TextView
        var card_View = itemView.findViewById<CardView>(R.id.cardViewooo) as CardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.design2, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return contracts.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = contracts[position]
        holder.my_name.text = data.name
        holder.my_value.text = data.value
        holder.card_View.startAnimation(
            AnimationUtils.loadAnimation(
                holder.itemView.context,
                R.anim.main_anim
            )
        )
        holder.itemView.setOnClickListener {
            val i = Intent(context, ActiveContract::class.java)
            i.putExtra("name", contracts[position].name)
            i.putExtra("value", contracts[position].value)
            i.putExtra("startDate", contracts[position].startDate)
            i.putExtra("endDate", contracts[position].endDate)
            context.startActivity(i)

        }
    }
}