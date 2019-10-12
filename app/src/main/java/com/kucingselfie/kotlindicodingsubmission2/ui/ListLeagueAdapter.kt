package com.kucingselfie.kotlindicodingsubmission2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.model.League

class ListLeagueAdapter(private val items: List<League>, private val clickListener: (League) -> Unit) : RecyclerView.Adapter<ListLeagueAdapter.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.league_name)
        fun bind(
            league: League,
            clickListener: (league: League) -> Unit
        ) {
            name.text = league.leagueName
            itemView.setOnClickListener { clickListener(league) }
        }
    }

}