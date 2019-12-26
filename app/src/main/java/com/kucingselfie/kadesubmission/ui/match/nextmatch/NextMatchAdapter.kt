package com.kucingselfie.kadesubmission.ui.match.nextmatch

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
import com.kucingselfie.kadesubmission.model.NextMatchFavorite

class NextMatchAdapter(private val context: Context, private var items: List<NextMatchFavorite>, private val clickListener: (NextMatchFavorite) -> Unit) : RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, items[position], clickListener)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_match, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.eventInfo)
        private val image = view.findViewById<ImageView>(R.id.eventImage)
        private val time = view.findViewById<TextView>(R.id.eventTime)
        @SuppressLint("SetTextI18n")
        fun bind(
            context: Context,
            match: NextMatchFavorite,
            clickListener: (league: NextMatchFavorite) -> Unit
        ) {
            name.text = match.matchName
            Glide.with(context).load(match.matchPicture).placeholder(R.drawable.ic_match_placeholder).into(image)
            time.text = match.matchTime

            itemView.setOnClickListener {
                clickListener(match)
            }
        }
    }
}