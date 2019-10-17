package com.kucingselfie.kotlindicodingsubmission2.ui.listleague


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

import com.kucingselfie.kotlindicodingsubmission2.R
import com.kucingselfie.kotlindicodingsubmission2.databinding.FragmentListLeagueBinding
import com.kucingselfie.kotlindicodingsubmission2.model.League

/**
 * A simple [Fragment] subclass.
 */
class ListLeagueFragment : Fragment() {

    private var items: MutableList<League> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentListLeagueBinding.inflate(inflater)
        binding.lifecycleOwner = this

        initData()

        binding.listLeague.adapter =
            ListLeagueAdapter(items) {
                findNavController().navigate(R.id.action_listLeagueFragment_to_detailLeagueFragment)
            }
        return binding.root
    }

    private fun initData() {val name = resources.getStringArray(R.array.league)
        items.clear()
        for (i in name.indices) {
            items.add(League(name[i]))
        }
    }
}
