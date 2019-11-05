package com.kucingselfie.kotlindicodingsubmission2.ui.nextmatch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.model.Match

class MatchAdapter(private val context: Context, private var items: List<Match>, private val clickListener: (Match) -> Unit) : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, items[position], clickListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder( LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.eventInfo)
        private val image = view.findViewById<ImageView>(R.id.eventImage)
        fun bind(
            context: Context,
            match: Match,
            clickListener: (league: Match) -> Unit
        ) {
            name.text = match.event
            Glide.with(context).load(match.eventImage).placeholder(R.drawable.ic_placeholder_image).into(image)

            itemView.setOnClickListener {
                clickListener(match)
            }
        }
    }

    fun refreshData(items: List<Match>) {
        this.items = items
        notifyDataSetChanged()
    }
}