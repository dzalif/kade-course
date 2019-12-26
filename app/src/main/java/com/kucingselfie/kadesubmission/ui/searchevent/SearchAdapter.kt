package com.kucingselfie.kadesubmission.ui.searchevent

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.model.Search

class SearchAdapter(
    val context: Context,
    private var items: MutableList<Search>,
    private val clickListener: (Search) -> Unit
) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, items[position], clickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date = view.findViewById<TextView>(R.id.tvDateEvent)
        private val league = view.findViewById<TextView>(R.id.tvLeagueName)
        private val imageEvent = view.findViewById<ImageView>(R.id.imageEvent)
        private val matchScore = view.findViewById<TextView>(R.id.matchScore)
        private val homeTeam = view.findViewById<TextView>(R.id.homeTeam)
        private val awayTeam = view.findViewById<TextView>(R.id.awayTeam)

        @SuppressLint("SetTextI18n")
        fun bind(
            context: Context,
            model: Search,
            clickListener: (Search) -> Unit
        ) {
            Glide.with(context).load(model.imageEvent).placeholder(R.drawable.ic_placeholder_image)
                .into(imageEvent)
            date.text = model.dateEvent
            league.text = model.league
            matchScore.text = "${model.homeScore ?: "0"} : ${model.awayScore ?: "0"}"
            homeTeam.text = model.homeTeam
            awayTeam.text = model.awayteam

            itemView.setOnClickListener {
                clickListener(model)
            }
        }
    }

    fun refreshData(items: MutableList<Search>) {
        this.items = items
        notifyDataSetChanged()
    }

    fun clear() {
        this.items.clear()
        notifyDataSetChanged()
    }

}