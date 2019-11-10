package com.kucingselfie.kotlindicodingsubmission2.ui.match.previousmatch

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.model.LastMatchFavorite

class PreviousMatchAdapter(private val context: Context, private var items: List<LastMatchFavorite>, private val clickListener: (LastMatchFavorite) -> Unit) : RecyclerView.Adapter<PreviousMatchAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, items[position], clickListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.eventInfo)
        private val image = view.findViewById<ImageView>(R.id.eventImage)
        fun bind(
            context: Context,
            match: LastMatchFavorite,
            clickListener: (league: LastMatchFavorite) -> Unit
        ) {
            name.text = match.matchName
            Glide.with(context).load(match.matchPicture).placeholder(R.drawable.ic_placeholder_image).into(image)

            itemView.setOnClickListener {
                clickListener(match)
            }
        }
    }
}