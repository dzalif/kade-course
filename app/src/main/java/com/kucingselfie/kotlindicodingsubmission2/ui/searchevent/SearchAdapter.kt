package com.kucingselfie.kotlindicodingsubmission2.ui.searchevent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.model.Search

class SearchAdapter(private var items: MutableList<Search>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val date = view.findViewById<TextView>(R.id.tvDateEvent)
        private val event = view.findViewById<TextView>(R.id.tvEvent)

        fun bind(
            model: Search
        ) {
            date.text = model.dateEvent
            event.text = model.strEvent
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