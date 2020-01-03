package com.kucingselfie.kadesubmission.ui.listleague

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.databinding.ItemSearchBinding
import com.kucingselfie.kadesubmission.model.Search
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.DataBoundListAdapter

class SearchAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickListener: ((Search) -> Unit)?
) : DataBoundListAdapter<Search, ItemSearchBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem.idLeague == newItem.idLeague
        }

        override fun areContentsTheSame(oldItem: Search, newItem: Search): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemSearchBinding {
        val binding = DataBindingUtil.inflate<ItemSearchBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_search,
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

    override fun bind(binding: ItemSearchBinding, item: Search) {
        binding.model = item
    }
}