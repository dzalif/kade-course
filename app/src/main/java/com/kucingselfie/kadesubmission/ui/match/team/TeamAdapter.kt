package com.kucingselfie.kadesubmission.ui.match.team

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.kucingselfie.kadesubmission.R
import com.kucingselfie.kadesubmission.databinding.ItemStandingBinding
import com.kucingselfie.kadesubmission.databinding.ItemTeamBinding
import com.kucingselfie.kadesubmission.model.Team
import com.kucingselfie.kadesubmission.util.AppExecutors
import com.kucingselfie.kadesubmission.util.DataBoundListAdapter

class TeamAdapter(
    private val dataBindingComponent: DataBindingComponent,
    appExecutors: AppExecutors,
    private val clickListener: ((Team) -> Unit)?
) : DataBoundListAdapter<Team, ItemTeamBinding>(
    appExecutors = appExecutors,
    diffCallback = object : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun createBinding(parent: ViewGroup): ItemTeamBinding {
        val binding = DataBindingUtil.inflate<ItemTeamBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_team,
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

    override fun bind(binding: ItemTeamBinding, item: Team) {
        binding.model = item
    }

}