package com.kucingselfie.kotlindicodingsubmission2.ui.listleague

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.model.DetailLeague
import org.jetbrains.anko.find

class ListLeagueAdapter(
    val context: Context,
    private var items: List<DetailLeague>,
    private val clickListener: (DetailLeague) -> Unit
) : RecyclerView.Adapter<ListLeagueAdapter.ViewHolder>() {
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_league, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, items[position], clickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.league_name)
        private val image = view.find<ImageView>(R.id.leagueLogo)
        fun bind(
            context: Context,
            league: DetailLeague,
            clickListener: (league: DetailLeague) -> Unit
        ) {
            name.text = league.leagueName
            Glide.with(context).load(league.logo).placeholder(R.drawable.trophy)
                .into(image)
            itemView.setOnClickListener {
                clickListener(league)
            }
        }
    }

    fun refreshData(items: List<DetailLeague>) {
        this.items = items
        notifyDataSetChanged()
    }

}