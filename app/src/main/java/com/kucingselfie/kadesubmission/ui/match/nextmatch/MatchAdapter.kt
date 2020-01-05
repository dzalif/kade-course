package com.kucingselfie.kadesubmission.ui.match.nextmatch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.databinding.ItemMatchBinding
import com.kucingselfie.kadesubmission.model.Match
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.DataBoundListAdapter

class MatchAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickListener: ((Match) -> Unit)?
) : DataBoundListAdapter<Match, ItemMatchBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Match>() {
        override fun areItemsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Match, newItem: Match): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemMatchBinding {
        val binding = DataBindingUtil.inflate<ItemMatchBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_match,
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

    override fun bind(binding: ItemMatchBinding, item: Match) {
        binding.model = item
    }
}