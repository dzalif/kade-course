package com.kucingselfie.kadesubmission.ui.match.standings

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.databinding.ItemStandingBinding
import com.kucingselfie.kadesubmission.model.Standing
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.DataBoundListAdapter

class StandingsAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickListener: ((Standing) -> Unit)?
) : DataBoundListAdapter<Standing, ItemStandingBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Standing>() {
        override fun areItemsTheSame(oldItem: Standing, newItem: Standing): Boolean {
            return oldItem.teamId == newItem.teamId
        }

        override fun areContentsTheSame(oldItem: Standing, newItem: Standing): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemStandingBinding {
        val binding = DataBindingUtil.inflate<ItemStandingBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_standing,
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

    override fun bind(binding: ItemStandingBinding, item: Standing) {
        binding.model = item
    }
}