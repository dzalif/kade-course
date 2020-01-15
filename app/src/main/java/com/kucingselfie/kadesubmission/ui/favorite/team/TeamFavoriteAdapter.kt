package com.kucingselfie.kadesubmission.ui.favorite.team

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
import com.kucingselfie.kadesubmission.model.TeamFavorite
import com.kucingselfie.kadesubmission.ui.match.nextmatch.NextMatchAdapter

class TeamFavoriteAdapter(private val context: Context, private var items: List<TeamFavorite>, private val clickListener: (TeamFavorite) -> Unit) : RecyclerView.Adapter<TeamFavoriteAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_team, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, items[position], clickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name = view.findViewById<TextView>(R.id.teamName)
        private val image = view.findViewById<ImageView>(R.id.teamBadge)
        @SuppressLint("SetTextI18n")
        fun bind(
            context: Context,
            team: TeamFavorite,
            clickListener: (league: TeamFavorite) -> Unit
        ) {
            name.text = team.teamName
            Glide.with(context).load(team.teamBadge).placeholder(R.drawable.ic_match_placeholder).into(image)

            itemView.setOnClickListener {
                clickListener(team)
            }
        }
    }
}