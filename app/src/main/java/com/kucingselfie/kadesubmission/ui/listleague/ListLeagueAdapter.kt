package com.kucingselfie.kadesubmission.ui.listleague

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.databinding.ItemLeagueBinding
import com.kucingselfie.kadesubmission.model.League
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.DataBoundListAdapter

class ListLeagueAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickListener: ((League) -> Unit)?
) : DataBoundListAdapter<League, ItemLeagueBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<League>() {
        override fun areItemsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: League, newItem: League): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemLeagueBinding {
        val binding = DataBindingUtil.inflate<ItemLeagueBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_league,
            parent,
            false,
            dataBindingComponent
        )
        binding.root.setOnClickListener {
            binding.model?.let {
                clickListener?.invoke(it)
            }
        }
        return binding
    }

    override fun bind(binding: ItemLeagueBinding, item: League) {
        binding.model = item
    }
}